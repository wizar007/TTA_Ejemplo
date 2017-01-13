package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;


import ejemplo.tta.intel.ehu.eus.tta_ejemplo.TestActivity;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.User;

/**
 * Created by mikel on 13/01/17.
 */
public class UserTask extends AsyncTask<String,Void,User> {
    protected final Context context;
    private final ProgressDialog dialog;
    private Exception e;

    public UserTask(Context context)
    {
        this.context=context;
        dialog=new ProgressDialog(context);
        dialog.setMessage("Comprobando usuario");
    }
    @Override
    protected void onPreExecute()
    {
        dialog.show();
    }

    @Override
    protected User doInBackground(String... params)
    {
        User user=null;
        String path=params[0];
        String dni=params[1];
        String passwd=params[2];

        path=path+"?dni="+dni;

        RestClient rest=new RestClient(path);
        rest.setHttpBasicAuth(dni,passwd);
        try
        {
            rest.getJson(path);
        }catch(Exception e)
        {
            

        }



        return user;
    }
}
