package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.User;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms.ProgressTask;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms.RestClient;

public class MainActivity extends AppCompatActivity {
    String pathBase;
    User usuario=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pathBase=getString(R.string.path_base);
    }
    public void login(View view)
    {
        final Intent intent=new Intent(this,MenuActivity.class);
        final String login = ((EditText)findViewById(R.id.login)).getText().toString();
        final String passwd = ((EditText)findViewById(R.id.password)).getText().toString();
        final String path=pathBase;

        new ProgressTask<User>(this)
        {

            @Override
            protected User work() throws Exception {
                User user=null;
                RestClient rest = new RestClient(path);//se genera
                //rest.setProperty();
                rest.setHttpBasicAuth(login, passwd);
                JSONObject jsonObject = rest.getJson(String.format("getStatus?dni=%s", login));
                user = new User(jsonObject.getInt("id"), jsonObject.getString("user"), jsonObject.getInt("lessonNumber"), jsonObject.getString("lessonTitle"), jsonObject.getInt("nextTest"), jsonObject.getInt("nextExercise"));

                return user;
            }

            @Override
            protected void onFinish(User result) {
                usuario=result;
                //Toast.makeText(context,"Bienvenido "+result.getUser(),Toast.LENGTH_SHORT);
                intent.putExtra(MenuActivity.EXTRA_LESSON,result.getLessonTitle());
                    intent.putExtra(MenuActivity.EXTRA_LOGIN, result.getUser());
                intent.putExtra(MenuActivity.EXTRA_N_LESSON,result.getLessonNumber());
                    startActivity(intent);




            }
        }.execute();


        /*if(authenticate(login,passwd)) {
            intent.putExtra(MenuActivity.EXTRA_LOGIN, login);
            startActivity(intent);

        }*/
    }
    protected boolean authenticate(String login, String passwd)
    {
        boolean validez=false;
        String userTest="prueba";
        String passTest="1234";



        if(login.equals(userTest) && passwd.equals(passTest))
        {
            validez=true;
            Toast.makeText(getApplicationContext(),"El usuario ha entrado correctamente",Toast.LENGTH_SHORT);
        }

        return validez;
    }
}
