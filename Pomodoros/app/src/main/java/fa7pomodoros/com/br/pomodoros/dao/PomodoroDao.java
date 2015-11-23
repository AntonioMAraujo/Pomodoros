package fa7pomodoros.com.br.pomodoros.dao;

import java.util.List;

import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;

/**
 * Created by antonio on 16/11/2015.
 */
public interface PomodoroDao {
    Pomodoro inserir(Pomodoro pomodoro);

    List<Pomodoro> findAll();

    Pomodoro findById(int id);

    Pomodoro alter(Pomodoro pomodoro);

    void limpar();

    public void deletaPomodoro(int id);
}
