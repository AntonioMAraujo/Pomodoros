package fa7pomodoros.com.br.pomodoros;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by antonio on 21/11/2015.
 */
public class GuardaUltimoHorario {

    public static final String MyPREFERENCES = "horario" ;
    private Context context;


    public GuardaUltimoHorario(Context c){
        context = c;
    }

    public void salvar(String valor){
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("horario", valor);
        editor.commit();
        Toast.makeText(context.getApplicationContext(),"Thanks", Toast.LENGTH_LONG).show();
    }

    public String recuperarValor(){
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getString("horario", "horario");
    }
}
