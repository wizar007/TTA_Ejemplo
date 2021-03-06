package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.User;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms.ProgressTask;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
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
        String path=getString(R.string.path_base)+getString(R.string.Userstatus);
        new ProgressTask<User>(this)
        {

            @Override
            protected User work() throws Exception {
                return null;
            }

            @Override
            protected void onFinish(User result) {

            }
        }.execute();


        if(login.equals(userTest) && passwd.equals(passTest))
        {
            validez=true;
        }
        return validez;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    protected boolean checkConnection(){
        boolean con;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
        {
            Toast.makeText(getApplicationContext(),"Conectado a Internet",Toast.LENGTH_SHORT).show();
            con=true;//fetch data
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_SHORT).show();
            con=false;
        }
        return con;

    }


}

