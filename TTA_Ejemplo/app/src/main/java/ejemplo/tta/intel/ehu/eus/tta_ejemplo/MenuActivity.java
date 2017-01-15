package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
public final static String EXTRA_LOGIN="es.tta.ejemplo31.login";
    public final static String EXTRA_LESSON="es.tta.ejemplo31.lesson";
    public final static String EXTRA_N_LESSON="es.tta.ejemplo31.nlesson";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();

        TextView textLogin = (TextView)findViewById(R.id.menu_login);
        TextView textLesson = (TextView)findViewById(R.id.menu_lesson);
        String username=intent.getStringExtra(EXTRA_LOGIN);
        String lesson=intent.getStringExtra(EXTRA_LESSON);
        int nlesson=intent.getIntExtra(EXTRA_N_LESSON,0);
        textLogin.setText("Bienvenido "+username);
        textLesson.setText("Leccion "+nlesson+": "+lesson);


    }
    public void test(View view)
    {
        Intent intent=new Intent(this,TestActivity.class);
        startActivity(intent);
    }
    public void exercise(View view)
    {
        Intent intent=new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }
}
