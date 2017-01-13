package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.comms;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;


import ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo.User;

/**
 * Created by mikel on 13/01/17.
 */
public class UserTask<T> extends AsyncTask<String,Void,T> {
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
    protected T doInBackground(String... params)
    {
        T user=null;

        return user;
    }
}
