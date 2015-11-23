package fa7pomodoros.com.br.pomodoros.serviceImpl;

import android.content.Context;

import java.util.List;

import fa7pomodoros.com.br.pomodoros.dao.PomodoroDao;
import fa7pomodoros.com.br.pomodoros.daoImp.PomodoroDaoImp;
import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;
import fa7pomodoros.com.br.pomodoros.service.PomodoroService;

/**
 * Created by antonio on 16/11/2015.
 */
public class PomodoroServiceImpl implements PomodoroService {

    private Context contexto;

    private PomodoroDao pomodoroDao;

    public PomodoroServiceImpl(Context contexto) {
        this.contexto = contexto;
        pomodoroDao = new PomodoroDaoImp(contexto);
    }

    @Override
    public Pomodoro inserir(Pomodoro pomodoro) {
        return pomodoroDao.inserir(pomodoro);
    }

    @Override
    public List<Pomodoro> findAll() {
        return pomodoroDao.findAll();
    }

    public Pomodoro findById(int id){
        return pomodoroDao.findById(id);
    }

    @Override
    public Pomodoro alter(Pomodoro pomodoro) {
        return pomodoroDao.alter(pomodoro);
    }

    @Override
    public void limpar() {
        pomodoroDao.limpar();
    }

    @Override
    public void deletaPomodoro(int id) {
        pomodoroDao.deletaPomodoro(id);
    }
}
