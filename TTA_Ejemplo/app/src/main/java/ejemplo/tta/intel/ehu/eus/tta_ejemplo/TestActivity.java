package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.Choice;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.Test;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.User;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms.ProgressTask;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms.RestClient;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.views.AudioPlayer;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    int correct=1;
    private Test test;
    private RestClient rest;
    //Layout layout=findViewById(R.id.test_layout);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        final String path=getString(R.string.path_base);
        SharedPreferences myprefs=getSharedPreferences("user",0);
        final String login=myprefs.getString("login",null);
        final String passwd=myprefs.getString("passwd",null);
        final int Id=1;

        new ProgressTask<Test>(this)
        {

            @Override
            protected Test work() throws Exception {
                Test test=new Test();
                RestClient rest = new RestClient(path);//se genera
                //rest.setProperty();
                rest.setHttpBasicAuth(login,passwd);
                JSONObject jsonObject = rest.getJson(String.format("getTest?id=%d", Id));
                String prueba=jsonObject.getString("wording");
                test.setWording(prueba);
                test.setWording(prueba/*jsonObject.getString("wording")*/);
                JSONArray lista=jsonObject.getJSONArray("choices");
                for(int i =0;i<lista.length();i++){
                    JSONObject temp=lista.getJSONObject(i);
                    Choice choice=new Choice(temp.getInt("id"),temp.getString("advise"),temp.getString("answer"),temp.getBoolean("correct"));
                    String inter=temp.getString("resourceType");

                    if(!inter.equals("null"))
                    {

                        choice.setAdviseType(temp.getJSONObject("resourceType").optString("mime",null));
                    }
                    else
                    {
                        choice.setAdviseType("none");
                    }
                    test.addChoice(choice);




                }

                return test;
            }

            @Override
            protected void onFinish(Test result) {
                test=result;
                getData();

            }
        }.execute();


    }

   /* protected  void setWording()
    {
        TextView textView=(TextView) findViewById(R.id.test_enunciado);
        textView.setText(test.getWording());

    }*/
    @Override
    public void onClick(View v)
    {

        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
    }

    protected void hint(View view)
    {
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        View selected=group.findViewById(group.getCheckedRadioButtonId());
        int selectednum=group.indexOfChild(selected);
        String adviseType=test.choices.get(selectednum).getAdviseType();
        String advise=test.choices.get(selectednum).getAdvise();



        switch(adviseType) {
            case "text/html":
                //Si la respuesta fallida es la 0 se muestra una pagina web auto generada
                LinearLayout layout=(LinearLayout) this.findViewById(R.id.test_layout);
                //String advice = "<html><body><h5>Prueba consejo</h5></body></html>";
                WebView web = new WebView(this);
                //web.loadUrl(advise);
                if(!advise.substring(0,10).contains("://")) {
                    web.loadData(advise, "text/html", null);
                    web.setBackgroundColor(Color.TRANSPARENT);
                    web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
                    layout.addView(web);
                }
                else
                {
                    Uri uri=Uri.parse("https://es.wikipedia.org/wiki/Examen_(evaluaci%C3%B3n_estudiantil)");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                break;
            case "none":
                //este caso no se utiliza
                break;
            case "video/mp4":
                //se reproduce el video
                //showVideo("http://techslides.com/demos/sample-videos/small.mp4");
                showVideo("http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4");
                break;
            case "audio/mpeg":
                final Thread thread = new Thread(new Runnable() {
                    public void run() {
                                            }});
                AudioPlayer audioPlay = new AudioPlayer(findViewById(R.id.test_layout),thread);
                try {
                    audioPlay.setAudioUri(Uri.parse("http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    protected void getData()
    {
       /* RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        RadioButton radio= new RadioButton(this);
        radio.setText("Respuesta 1");
        radio.setOnClickListener(this);
        group.addView(radio);
        RadioButton radio2= new RadioButton(this);
        radio2.setText("Respuesta 2");
        radio2.setOnClickListener(this);
        group.addView(radio2);
        RadioButton radio3= new RadioButton(this);
        radio3.setText("Respuesta 3");
        radio3.setOnClickListener(this);
        group.addView(radio3);
        RadioButton radio4= new RadioButton(this);
        radio4.setText("Respuesta 4");
        radio4.setOnClickListener(this);
        group.addView(radio4);
        RadioButton radio5= new RadioButton(this);
        radio5.setText("Respuesta 5");
        radio5.setOnClickListener(this);
        group.addView(radio5);*/
        TextView textWording=(TextView)findViewById(R.id.test_enunciado);
        textWording.setText(test.getWording());
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        int i=0;
        for(Choice choice : test.getChoice())
        {
            RadioButton radio= new RadioButton(this);
            radio.setText(choice.getEnunciado());
            radio.setOnClickListener(this);
            group.addView(radio);
            if(choice.isCorrect())
            {
                correct= i;
            }
            i++;
        }

    }

    protected void send(View view)
    {
        String advise="Algo diferente";
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        int choices=group.getChildCount();
        for(int i=0; i<choices;i++)
            group.getChildAt(i).setEnabled(false);
        View selected=group.findViewById(group.getCheckedRadioButtonId());
        final int selectednum=group.indexOfChild(selected);
        SharedPreferences myprefs=getSharedPreferences("user",0);
        final String login=myprefs.getString("login",null);
        final String passwd=myprefs.getString("passwd",null);
        final String path=getString(R.string.path_base);

        new ProgressTask<Test>(this)
        {

            @Override
            protected Test work() throws Exception {
                Test test=new Test();
                RestClient rest=new RestClient(path);
                rest.setHttpBasicAuth(login,passwd);
                int respuesta=uploadResult(selectednum);
                test.setWording("Ha devuelto: "+respuesta);

                return test;
            }

            @Override
            protected void onFinish(Test result) {
                Toast.makeText(getApplicationContext(),result.getWording(), Toast.LENGTH_SHORT).show();

            }
        }.execute();
/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                RestClient rest=new RestClient(path);
                rest.setHttpBasicAuth(login,passwd);
                uploadResult(selectednum);

            }
        }).start();*/




        group.getChildAt(correct).setBackgroundColor(Color.GREEN);
        //Toast.makeText(getApplicationContext(),"Has dicho algo",Toast.LENGTH_SHORT);
        if(selectednum != correct)
        {
            findViewById(group.getCheckedRadioButtonId()).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),R.string.toast_fail, Toast.LENGTH_SHORT).show();
            if(advise != null && !advise.isEmpty())
            {
                findViewById(R.id.button_hint_test).setVisibility(View.VISIBLE);
            }

        }else
        {
            Toast.makeText(getApplicationContext(),R.string.toast_success,Toast.LENGTH_SHORT).show();
        }
        findViewById(R.id.button_send_test).setVisibility(View.INVISIBLE);

    }


    public int uploadResult(int choiceId) throws Exception{
            JSONObject json = new JSONObject();
            json.put("userId", 1);
            json.put("choiceId",choiceId);
            return rest.postJson(json,"postChoice");


    }
    private void showVideo(String advise) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.test_layout);
        VideoView video = new VideoView(this);
        video.setVideoURI(Uri.parse(advise));

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(params);

        MediaController controller = new MediaController(this){

            @Override
            public void hide(){

            }
            @Override
            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode()==KeyEvent.KEYCODE_BACK) finish();
                return super.dispatchKeyEvent(event);
            }

        };
        controller.setAnchorView(video);
        video.setMediaController(controller);
        layout.addView(video);

    }

}
