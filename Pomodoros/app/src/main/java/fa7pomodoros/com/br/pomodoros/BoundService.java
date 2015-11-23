package fa7pomodoros.com.br.pomodoros;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

import fa7pomodoros.com.br.pomodoros.adapter.Adapter;
import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;
import fa7pomodoros.com.br.pomodoros.service.PomodoroService;
import fa7pomodoros.com.br.pomodoros.serviceImpl.PomodoroServiceImpl;

/**
 * Created by antonio on 21/11/2015.
 */
public class BoundService extends Service {

    private IBinder binder;
    private boolean stop;
    private String valorRelogio;


    public BoundService() {
        this.binder = new LocalBinder();
        this.stop = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void start() {
        Cronometro c = new Cronometro(getApplicationContext(),10*1000,1000);
        c.start();
    }

    public void stop() {
    }

    public String getValorRelogio() {
        return valorRelogio;
    }

    public void setValorRelogio(String valorRelogio) {
        this.valorRelogio = valorRelogio;
    }

    public class LocalBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }

    private class Cronometro  extends CountDownTimer {

        private Context context;
        private TextView cronometro;
        private long millisInFuture;
        private PomodoroService service;
        private long countDownInterval;
        private String valor;

        public Cronometro(Context context, /*TextView cronometro,*/ long millisInFuture, long countDownInterval/*, String valor*/) {
            super(millisInFuture, countDownInterval);
            this.context = context;
            this.cronometro = cronometro;
            this.millisInFuture = millisInFuture;
            this.countDownInterval = countDownInterval;
            service = new PomodoroServiceImpl(context);
            this.valor = valor;
        }

        @Override
        public void onTick(long l) {
            millisInFuture = l;
            setValorRelogio(getDataCorrente(true, l) + " : " + getDataCorrente(false, l));
            Log.i("APP", getDataCorrente(true, l) + " : " + getDataCorrente(false, l));
        }

        @Override
        public void onFinish() {
            millisInFuture -= 1000;
            setValorRelogio(getDataCorrente(true, millisInFuture) + ":" + getDataCorrente(false, millisInFuture));

        }

        private String getDataCorrente(boolean isMinute, long millisInFuture) {
            String aux;
            int constant = isMinute ? Calendar.MINUTE : Calendar.SECOND;
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(millisInFuture);
            aux = c.get(constant) < 10 ? "0" + c.get(constant) : "" + c.get(constant);
            return aux;
        }


    }
}