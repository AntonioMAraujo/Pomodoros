package fa7pomodoros.com.br.pomodoros.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import fa7pomodoros.com.br.pomodoros.MainActivity;
import fa7pomodoros.com.br.pomodoros.PomodoroEdit;
import fa7pomodoros.com.br.pomodoros.StartedService;
import fa7pomodoros.com.br.pomodoros.R;
import fa7pomodoros.com.br.pomodoros.entidades.Pomodoro;
import fa7pomodoros.com.br.pomodoros.service.PomodoroService;
import fa7pomodoros.com.br.pomodoros.serviceImpl.PomodoroServiceImpl;

/**
 * Created by antonio on 16/11/2015.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private List<Pomodoro> pomodoros;
    private Context context;
    public static TextView cronometro;
    public PomodoroService service;

    static int i = 0;

    public Context getContext() {
        return context;
    }

    public Adapter(List<Pomodoro> pomodoros, Context context) {
        this.pomodoros = pomodoros;
        this.context = context;
        service = new PomodoroServiceImpl(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.descricao.setText(pomodoros.get(position).getDescricao());
        holder.titulo.setText(pomodoros.get(position).getTitulo());
        holder.qtdPomodoros.setText("" + pomodoros.get(position).getQtdPomodoro());
        holder.idInvisible.setText("" + pomodoros.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return pomodoros.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private int imagem;
        private TextView titulo;
        private TextView descricao;
        private TextView qtdPomodoros;
        private Button btnIniciar;
        private Button btnParar;
        public TextView idInvisible;
        private Button editar;
        private ImageButton excluir;

        public Holder(final View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titulopom);
            descricao = (TextView) itemView.findViewById(R.id.descricaoPom);
            qtdPomodoros = (TextView) itemView.findViewById(R.id.qtdPom);
            idInvisible = (TextView) itemView.findViewById(R.id.id);
            btnIniciar = (Button) itemView.findViewById(R.id.iniciar);
            btnParar = (Button) itemView.findViewById(R.id.parar);
            editar = (Button) itemView.findViewById(R.id.editar);
            excluir = (ImageButton) itemView.findViewById(R.id.buttonExcluir);


            btnIniciar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), StartedService.class);
                    i.putExtra("funcionar", true);
                    getContext().startService(i);
                }
            });

            btnParar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), StartedService.class);
                    i.putExtra("funcionar", false);
                    getContext().startService(i);
                }
            });

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, PomodoroEdit.class);
                    i.putExtra("idItemList",idInvisible.getText().toString());
                    context.startActivity(i);
                }
            });

            excluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    service.deletaPomodoro(Integer.parseInt(idInvisible.getText().toString().trim()));
                    pomodoros = service.findAll();
                    ((MainActivity)context).carregaItens();
                    Log.i("APP","Excluido");
                }
            });
        }

    }

}
