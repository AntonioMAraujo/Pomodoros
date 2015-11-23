package fa7pomodoros.com.br.pomodoros.entidades;

import java.io.Serializable;

/**
 * Created by antonio on 16/11/2015.
 */
public class Pomodoro implements Serializable{
    private int id;
    private String titulo;
    private String descricao;
    private int qtdPomodoro;

    public Pomodoro() {
    }

    public Pomodoro(int id, String titulo, String descricao, int qtdPomodoro) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtdPomodoro = qtdPomodoro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdPomodoro() {
        return qtdPomodoro;
    }

    public void setQtdPomodoro(int qtdPomodoro) {
        this.qtdPomodoro = qtdPomodoro;
    }
}
