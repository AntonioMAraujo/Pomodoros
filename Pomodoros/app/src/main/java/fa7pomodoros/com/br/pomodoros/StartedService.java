package fa7pomodoros.com.br.pomodoros;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import fa7pomodoros.com.br.pomodoros.adapter.Adapter;

/**
 * Created by antonio on 18/11/2015.
 */
public class StartedService extends Service {

    private Context contexto;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.i("APP",intent.getStringExtra("valorClicado"));
        Cronometro c = new Cronometro(getApplicationContext(),20*1000,1000);
        c.start();
        return START_NOT_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

