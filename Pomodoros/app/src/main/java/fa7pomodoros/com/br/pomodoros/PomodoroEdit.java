package fa7pomodoros.com.br.pomodoros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;
import fa7pomodoros.com.br.pomodoros.service.PomodoroService;
import fa7pomodoros.com.br.pomodoros.serviceImpl.PomodoroServiceImpl;

/**
 * Created by antonio on 22/11/2015.
 */
public class PomodoroEdit extends Activity{

    private PomodoroService service = new PomodoroServiceImpl(this);
    private TextView titulo;
    private TextView descricao;
    private TextView qtdPomodoro;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_pomodoro);
        init();
        Intent i = getIntent();
        retornaTelaComValores(i.getStringExtra("idItemList"));

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaPreenchimento()) {
                    Pomodoro pomodoro = recuparValorTela();

                    service.alter(pomodoro);

                    finish();
                }
            }
        });
    }

    private void init(){
        titulo = (TextView) findViewById(R.id.tituloPomodoroEdit);
        descricao = (TextView) findViewById(R.id.descricaoPomodoroEdit);
        qtdPomodoro = (TextView) findViewById(R.id.qutPomodoroEdit);
        btnEditar = (Button) findViewById(R.id.btnEditarPomodoro);
    }

    private void retornaTelaComValores(String id){
        Log.i("App","Valor Recebido: " + id);
        int idPom = Integer.parseInt(id.toString().trim());
        Pomodoro pomodoro = service.findById(idPom);
        titulo.setText(pomodoro.getTitulo());
        descricao.setText(pomodoro.getDescricao());
        qtdPomodoro.setText(""+pomodoro.getQtdPomodoro());
    }

    private Pomodoro recuparValorTela(){
        Pomodoro pomodoro = new Pomodoro();
        Intent i = getIntent();

        pomodoro.setTitulo(titulo.getText().toString());
        pomodoro.setDescricao(descricao.getText().toString());
        pomodoro.setQtdPomodoro(Integer.parseInt(qtdPomodoro.getText().toString()));
        pomodoro.setId(Integer.parseInt(i.getStringExtra("idItemList")));

        return pomodoro;
    }


    private boolean validaPreenchimento(){
        if(titulo.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Preencha o Titulo", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(descricao.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this,"Preencha a descricao",Toast.LENGTH_SHORT).show();
            return false;

        }

        if(qtdPomodoro.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this,"Preencha a quantidade",Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
    }

}
