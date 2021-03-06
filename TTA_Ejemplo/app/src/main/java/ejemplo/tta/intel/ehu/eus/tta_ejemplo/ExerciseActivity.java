package ejemplo.tta.intel.ehu.eus.tta_ejemplo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.Exercise;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.LessonBean;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms.ProgressTask;
import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms.RestClient;

public class ExerciseActivity extends AppCompatActivity {
    public final static int AUDIO_REQUEST_CODE=3;
    public final static int VIDEO_REQUEST_CODE=2;
    public static final int PICTURE_REQUEST_CODE = 1;
    private Exercise exercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        final String path=getString(R.string.path_base);
        SharedPreferences myprefs=getSharedPreferences("user",0);
        final String login=myprefs.getString("login",null);
        final String passwd=myprefs.getString("passwd",null);
        final int Id=1;

        new ProgressTask<Exercise>(this)
        {

            @Override
            protected Exercise work() throws Exception {
                Exercise exercise=null;
                RestClient rest = new RestClient(path);//se genera
                rest.setHttpBasicAuth(login,passwd);
                JSONObject jsonObject = rest.getJson(String.format("getExercise?id=%d", Id));
                //exercise.setWording(jsonObject.getString("wording"));
                //exercise.setId(jsonObject.getInt("id"));
                LessonBean temp=new LessonBean(jsonObject.getJSONObject("lessonBean").getInt("id"),jsonObject.getJSONObject("lessonBean").getInt("number"),jsonObject.getJSONObject("lessonBean").getString("title"));
                exercise=new Exercise(jsonObject.getInt("id"),jsonObject.getString("wording"),temp);




               return  exercise;
            }

            @Override
            protected void onFinish(Exercise result) {
                exercise=result;
                serWording();


            }
        }.execute();

    }

    private void serWording()
    {
        TextView textView=(TextView)findViewById(R.id.exercise_wording);
        textView.setText(exercise.getWording());
    }

    public void recordAudio(View view)
    {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
        {
            Toast.makeText(this, R.string.no_micro, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
            else
                Toast.makeText(this,R.string.no_app,Toast.LENGTH_SHORT).show();
        }

    }
    public void takePhoto(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,R.string.no_camera, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!= null){
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("Tta_app",".jpg", dir);
                    Uri photoUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(this,R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void recordVideo(View view)
    {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent, VIDEO_REQUEST_CODE);
            else
                Toast.makeText(this,R.string.no_app,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        if(resultCode != Activity.RESULT_OK)
            return;
        /*switch (requestCode)
        {

        }*/
    }

}
