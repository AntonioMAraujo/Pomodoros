package fa7pomodoros.com.br.pomodoros.daoImp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fa7pomodoros.com.br.pomodoros.conf.BancoConf;
import fa7pomodoros.com.br.pomodoros.dao.PomodoroDao;
import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;

/**
 * Created by antonio on 16/11/2015.
 */
public class PomodoroDaoImp implements PomodoroDao{

    private Context contexto;

    private SQLiteDatabase db;

    private BancoConf bancoConf;

    public PomodoroDaoImp(Context contexto) {
        this.contexto = contexto;
        bancoConf = new BancoConf(this.contexto);
    }

    @Override
    public Pomodoro inserir(Pomodoro pomodoro) {
        ContentValues valores;

        long resultado;

        db = bancoConf.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoConf.TITULO, pomodoro.getTitulo());
        valores.put(BancoConf.DESCRICAO, pomodoro.getDescricao());
        valores.put(BancoConf.QTDPOMODOROS, pomodoro.getQtdPomodoro());
        resultado = db.insert(BancoConf.TABELA, null, valores);
        db.close();
        if (resultado ==-1)
            Log.i("APP","Erro ao inserir registro");
        else
            Log.i("APP","Registro Inserido com sucesso");

        return pomodoro;
    }

    @Override
    public List<Pomodoro> findAll() {
        Pomodoro pomodoro;
        List<Pomodoro> pomodoros = new ArrayList<>();

        Cursor cursor;
        String[] campos = {BancoConf.ID,BancoConf.TITULO,BancoConf.DESCRICAO,BancoConf.QTDPOMODOROS};
        db = bancoConf.getReadableDatabase();
        cursor = db.query(BancoConf.TABELA, campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();

        while(cursor.moveToNext()){
            pomodoro = new Pomodoro();
            pomodoro.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BancoConf.ID)));
            pomodoro.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(BancoConf.DESCRICAO)));
            pomodoro.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(BancoConf.TITULO)));
            pomodoro.setQtdPomodoro(cursor.getInt(cursor.getColumnIndexOrThrow(BancoConf.QTDPOMODOROS)));
            pomodoros.add(pomodoro);

        }
        return pomodoros;

    }

    public Pomodoro findById(int id){
        Cursor cursor;
        String[] campos = {BancoConf.ID,BancoConf.TITULO,BancoConf.DESCRICAO,BancoConf.QTDPOMODOROS};
        String where = BancoConf.ID + "=" + id;
        db = bancoConf.getReadableDatabase();
        cursor = db.query(BancoConf.TABELA,campos,where, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        Pomodoro pomodoro = null;

        while(cursor.moveToFirst()){
            pomodoro = new Pomodoro();
            pomodoro.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BancoConf.ID)));
            pomodoro.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(BancoConf.DESCRICAO)));
            pomodoro.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(BancoConf.TITULO)));
            pomodoro.setQtdPomodoro(cursor.getInt(cursor.getColumnIndexOrThrow(BancoConf.QTDPOMODOROS)));
            return pomodoro;
        }

        return pomodoro;
    }

    @Override
    public Pomodoro alter(Pomodoro pomodoro) {
        ContentValues valores;
        String where;
        db = bancoConf.getWritableDatabase();
        where = BancoConf.ID + "=" + pomodoro.getId();
        valores = new ContentValues();

        valores.put(BancoConf.TITULO, pomodoro.getTitulo());
        valores.put(BancoConf.DESCRICAO, pomodoro.getDescricao());
        valores.put(BancoConf.QTDPOMODOROS, pomodoro.getQtdPomodoro());
        db.update(BancoConf.TABELA,valores,where,null);
        db.close();

        return pomodoro;
    }

    @Override
    public void limpar() {
        ContentValues valores;
        String where;
        db = bancoConf.getWritableDatabase();
        db.delete(BancoConf.TABELA, BancoConf.ID+ " IS NOT NULL ",null);
    }


    public void deletaPomodoro(int id){
        String where = BancoConf.ID + "=" + id;
        db = bancoConf.getReadableDatabase();
        db.delete(BancoConf.TABELA,where,null);
        db.close();
    }

}
