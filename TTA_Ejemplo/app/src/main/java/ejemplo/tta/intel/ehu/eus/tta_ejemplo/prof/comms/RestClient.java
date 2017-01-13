package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.comms;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by mikel on 13/01/17.
 */
public class RestClient {
    private final static String AUTH="Authorization";
    private final String baseUrl;
    private final Map<String,String> properties =new HashMap<>();

    public RestClient(String baseUrl)
    {
        this.baseUrl=baseUrl;
    }
    public void setHttpBasicAuth(String user, String passwd)
    {

    }
    public String getAuthorization()
    {
        return properties.get(AUTH);

    }
    public void setAuthorization(String auth)
    {
        this.properties.put(AUTH,auth);
    }
    public void setProperty(String name,String value)
    {
        properties.put(name,value);
    }
    private HttpURLConnection getConnection(String path) throws IOException
    {
        URL url=new URL(String.format("%s/%s",baseUrl,path));
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        for(Map.Entry<String,String> property :properties.entrySet())
        {
            conn.setRequestProperty(property.getKey(),property.getValue());
        }
        conn.setUseCaches(false);//No mantener caches
        //conn.setRequestProperty("Connection","Keep-Alive");
        return conn;

    }

    public String getString(String path) throws IOException
    {
        HttpURLConnection conn=null;
        try {
            conn = getConnection(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return br.readLine();

        } finally {
                if(conn!=null)
                {
                    conn.disconnect();
                }
            }

    }

    public int postJson(final JSONObject json,String path) throws IOException{
        HttpURLConnection conn=null;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            PrintWriter pw = new PrintWriter(conn.getOutputStream())
            pw.print(json.toString());
            return conn.getResponseCode();

        }finally {
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
    }

}
