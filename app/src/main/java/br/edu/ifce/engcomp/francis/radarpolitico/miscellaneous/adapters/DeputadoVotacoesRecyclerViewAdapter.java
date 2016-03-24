package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.models.VotacaoDeputado;

/**
 * Created by Joamila on 24/03/2016.
 */
public class DeputadoVotacoesRecyclerViewAdapter extends RecyclerView.Adapter<DeputadoVotacoesRecyclerViewAdapter.ViewHolder>{
    private ArrayList<VotacaoDeputado> dataSource;
    private Context context;

    public DeputadoVotacoesRecyclerViewAdapter(ArrayList<VotacaoDeputado> dataSource, Context context) {
        this.dataSource = dataSource;
        this.context = context;
    }

    public VotacaoDeputado getItem(int position) {
        return this.dataSource.get(position);
    }

    @Override
    public DeputadoVotacoesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.adapter_deputado_votacoes, parent, false);

        return new DeputadoVotacoesRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeputadoVotacoesRecyclerViewAdapter.ViewHolder holder, int position) {
        VotacaoDeputado votacao = this.dataSource.get(position);

        holder.dataVotacaoTextView.setText(votacao.getData());
        holder.nomeVotacaoTextView.setText(votacao.getCodSessao());
        holder.votoTextView.setText(votacao.getVoto());
    }

    @Override
    public int getItemCount() {
        return this.dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView dataVotacaoTextView;
        private TextView nomeVotacaoTextView;
        private TextView votoTextView;


        public ViewHolder(View itemView){
            super(itemView);

            this.dataVotacaoTextView    = (TextView) itemView.findViewById(R.id.data_deputado_votacao_text_view);
            this.nomeVotacaoTextView = (TextView) itemView.findViewById(R.id.nome_votacao_deputado_text_view);
            this.votoTextView = (TextView) itemView.findViewById(R.id.posicao_deputado_voto_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Cliked", Toast.LENGTH_SHORT).show();
        }
    }
}
