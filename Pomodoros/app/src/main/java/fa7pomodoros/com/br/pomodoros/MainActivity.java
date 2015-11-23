package fa7pomodoros.com.br.pomodoros;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import fa7pomodoros.com.br.pomodoros.adapter.Adapter;
import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;
import fa7pomodoros.com.br.pomodoros.service.PomodoroService;
import fa7pomodoros.com.br.pomodoros.serviceImpl.PomodoroServiceImpl;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton btnCriarTarefa;
    private static TextView cronometro;
    private RecyclerView listRecyclerView;
    private PomodoroService pomodoroService = new PomodoroServiceImpl(this);
    private Button limparBtn;
    private Toolbar toolbar;
    String valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        List<Pomodoro> pomodoros = pomodoroService.findAll();

        Adapter adapter = new Adapter(pomodoros, this);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listRecyclerView.setAdapter(adapter);


 /*       limparBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                pomodoroService.limpar();

            }
        });*/

    }

    public void setText(String valor){
        this.valor = valor;
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Pomodoro> pomodoros = pomodoroService.findAll();

        Adapter adapter = new Adapter(pomodoros, this);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listRecyclerView.setAdapter(adapter);
    }

    private void init() {
        btnCriarTarefa = (ImageButton) findViewById(R.id.button);
        listRecyclerView = (RecyclerView) findViewById(R.id.listaRecycler);
        cronometro = (TextView) findViewById(R.id.cron);
        btnCriarTarefa.setOnClickListener(this);
       // limparBtn = (Button) findViewById(R.id.limpar);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);

    }

    public void carregaItens(){
        List<Pomodoro> pomodoros = pomodoroService.findAll();

        Adapter adapter = new Adapter(pomodoros, this);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, PomodoroActivity.class);
        startActivity(i);
    }

    public static TextView getCronometro() {
        return cronometro;
    }

}
