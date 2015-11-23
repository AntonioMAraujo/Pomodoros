package fa7pomodoros.com.br.pomodoros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;
import fa7pomodoros.com.br.pomodoros.service.PomodoroService;
import fa7pomodoros.com.br.pomodoros.serviceImpl.PomodoroServiceImpl;

/**
 * Created by antonio on 16/11/2015.
 */
public class PomodoroActivity extends Activity {

    private TextView titulo;
    private TextView descricao;
    private TextView qtdPomodoros;
    private Button btnSalvar;
    PomodoroService pomodoroService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pomodoro);
        init();
        pomodoroService = new PomodoroServiceImpl(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validaPreenchimento()) {
                    pomodoroService.inserir(capturaDados());

                    Log.i("APP", "Quantidade de registros encontrados: " + pomodoroService.findAll().size());

                    finish();
                }
            }
        });
    }


    private Pomodoro capturaDados(){
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setDescricao(descricao.getText().toString());
        pomodoro.setQtdPomodoro(Integer.parseInt(qtdPomodoros.getText().toString()));
        pomodoro.setTitulo(titulo.getText().toString());
        return pomodoro;
    }

    private void init(){
        titulo = (TextView) findViewById(R.id.tituloPomodoro);
        descricao = (TextView) findViewById(R.id.descricaoPomodoro);
        qtdPomodoros = (TextView) findViewById(R.id.qutPomodoro);
        btnSalvar = (Button) findViewById(R.id.btnSalvarPomodoro);

    }

    private boolean validaPreenchimento(){
        if(titulo.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this,"Preencha o Titulo",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(descricao.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this,"Preencha a descricao",Toast.LENGTH_SHORT).show();
            return false;

        }

        if(qtdPomodoros.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this,"Preencha a quantidade",Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
    }


}
