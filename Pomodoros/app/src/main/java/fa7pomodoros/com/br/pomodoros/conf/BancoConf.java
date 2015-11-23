package fa7pomodoros.com.br.pomodoros.conf;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antonio on 16/11/2015.
 */
public class BancoConf extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "pomodoro.db";
    public static final String TABELA = "tarefas";
    public static final String ID = "_id";
    public static final String TITULO = "titulo";
    public static final String DESCRICAO = "descricao";
    public static final String QTDPOMODOROS = "qtdPomodoro";
    public static final int VERSAO = 1;
    public Context contexto;


    public BancoConf(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE "
                +TABELA+" ( "
                + ID + " integer primary key autoincrement, "
                + TITULO + " text, "
                + DESCRICAO + " text, "
                + QTDPOMODOROS + " integer " +" ) ";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);

    }

}
