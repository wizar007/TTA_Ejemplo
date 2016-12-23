package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    int correct=1;
    //Layout layout=findViewById(R.id.test_layout);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        getData();
    }
    @Override
    public void onClick(View v)
    {

        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
    }

    protected void hint(View view)
    {
        LinearLayout layout=(LinearLayout) this.findViewById(R.id.test_layout);
        String advice="<html><body><h5>Prueba consejo</h5></body></html>";
        WebView web=new WebView(this);
        //web.loadUrl(advise);
        web.loadData(advice,"text/html",null);
        web.setBackgroundColor(Color.TRANSPARENT);
        web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
        layout.addView(web);

    }
    protected void getData()
    {
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
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

    protected void send(View view)
    {
        String advise="Algo diferente";
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        int choices=group.getChildCount();
        for(int i=0; i<choices;i++)
            group.getChildAt(i).setEnabled(false);
       // layout.removeView(findViewById(R.id.button_send_test));

        int selected=group.getCheckedRadioButtonId()-1;
        group.getChildAt(correct).setBackgroundColor(Color.GREEN);
        if(selected != correct)
        {
            findViewById(group.getCheckedRadioButtonId()).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),R.string.toast_fail, Toast.LENGTH_SHORT);
            if(advise != null && !advise.isEmpty())
            {
                findViewById(R.id.button_hint_test).setVisibility(View.VISIBLE);
            }

        }else
        {
            Toast.makeText(getApplicationContext(),R.string.toast_success,Toast.LENGTH_SHORT);
        }
        findViewById(R.id.button_send_test).setVisibility(View.INVISIBLE);

    }
}
