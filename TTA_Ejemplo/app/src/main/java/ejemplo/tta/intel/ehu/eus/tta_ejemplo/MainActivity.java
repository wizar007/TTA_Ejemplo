package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View view)
    {
        Intent intent=new Intent(this,MenuActivity.class);
        String login = ((EditText)findViewById(R.id.login)).getText().toString();
        String passwd = ((EditText)findViewById(R.id.password)).getText().toString();
        if(authenticate(login,passwd)) {
            intent.putExtra(MenuActivity.EXTRA_LOGIN, login);
            startActivity(intent);

        }
    }
    protected boolean authenticate(String login, String passwd)
    {
        boolean validez=false;
        String userTest="prueba";
        String passTest="1234";

        if(login.equals(userTest) && passwd.equals(passTest))
        {
            validez=true;
        }
        return validez;
    }
}
