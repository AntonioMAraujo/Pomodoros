package fa7pomodoros.com.br.pomodoros;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

import fa7pomodoros.com.br.pomodoros.adapter.Adapter;
import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;
import fa7pomodoros.com.br.pomodoros.service.PomodoroService;
import fa7pomodoros.com.br.pomodoros.serviceImpl.PomodoroServiceImpl;

/**
 * Created by antonio on 19/11/2015.
 */
public class Cronometro extends CountDownTimer {

    private static Context context;
    private TextView cronometro;
    private long millisInFuture;
    private PomodoroService service;
    private long countDownInterval;
    private String valor;

    public Cronometro(Context context,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
        service = new PomodoroServiceImpl(context);
    }

    @Override
    public void onTick(long l) {
        millisInFuture = l;
        MainActivity.getCronometro().setText(getDataCorrente(true, l) + " : " + getDataCorrente(false, l));
    }

    @Override
    public void onFinish() {
        millisInFuture -= 1000;
    }

    private String getDataCorrente(boolean isMinute, long millisInFuture){
        String aux;
        int constant = isMinute ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisInFuture);
        aux = c.get(constant) < 10 ? "0" + c.get(constant): "" + c.get(constant);
        return aux;
    }
}
