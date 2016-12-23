package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    int correct=1;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    @Override
    public void onClick(View v)
    {
        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
    }

    protected void hint()
    {
        String advice="Prueba consejo";
        WebView web=new WebView(this);
        //web.loadUrl(advise);
        web.loadData(advice,"text/html",null);
        web.setBackgroundColor(Color.TRANSPARENT);
        web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
        //layout.addView(web);

    }
    protected void getData()
    {
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        RadioButton radio= new RadioButton(this);
        radio.setText("Respuesta 1");
        radio.setOnClickListener(this);
        group.addView(radio);
        radio.setText("Respuesta 2");
        radio.setOnClickListener(this);
        group.addView(radio);;
        radio.setText("Respuesta 3");
        radio.setOnClickListener(this);
        group.addView(radio);
        radio.setText("Respuesta 4");
        radio.setOnClickListener(this);
        group.addView(radio);
       /* Test test=data.getTest();
        TextView textWording=(TextView)findViewById(R.id.test_enunciado);
        textWording.setText(test.getWording());
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        int i=0;
        for(Test.Choice choice : test.getChoices())
        {
            RadioButton radio= new RadioButton(this);
            radio.setText(choice.getWording());
            radio.setOnClickListener(this);
            group.addView(radio);
            if(choice.isCorrect())
            {
                correct= i;
            }
            i++;
        }*/

    }

    protected void send()
    {

       /* RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        int choices=group.getChildCount();
        for(int i=0; i<choices;i++)
            group.getChildAt(i).setEnabled(false);
        layout.removeView(findViewById(R.id.button_send_test));

        group.getChildAt(correct).setBackgroundColor(Color.GREEN);
        if(selected != correct)
        {
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),R.string.toast_fail, Toast.LENGTH_SHORT);
            if(advise != null && !advise.isEmpty())
            {
                findViewById(R.id.button_hint_test).setVisibility(View.VISIBLE);
            }

        }else
        {
            Toast.makeText(getApplicationContext(),R.string.toast_success,Toast.LENGTH_SHORT);
        }*/

    }
}
