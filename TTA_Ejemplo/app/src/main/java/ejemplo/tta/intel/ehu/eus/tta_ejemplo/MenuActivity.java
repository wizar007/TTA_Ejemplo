package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
public final static String EXTRA_LOGIN="es.tta.ejemplo31.login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        TextView textLogin = (TextView)findViewById(R.id.login);
      //  textLogin.setText(intent.getStringExtra(EXTRA_LOGIN)); 
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
