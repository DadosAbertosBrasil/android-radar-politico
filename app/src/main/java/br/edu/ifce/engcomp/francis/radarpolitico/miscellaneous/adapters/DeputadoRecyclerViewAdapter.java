package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by francisco on 11/03/16.
 */
public class DeputadoRecyclerViewAdapter extends RecyclerView.Adapter<DeputadoRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Deputado> dataSource;
    private Context context;

    public DeputadoRecyclerViewAdapter(ArrayList<Deputado> dataSource, Context context) {
        this.dataSource = dataSource;
        this.context = context;
    }

    public Deputado getItem(int position) {
        return this.dataSource.get(position);
    }

    @Override
    public DeputadoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.adapter_politico, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeputadoRecyclerViewAdapter.ViewHolder holder, int position) {
        Deputado deputado = this.dataSource.get(position);

        holder.nomePoliticoTextView.setText(deputado.getNome());
        holder.partidoPoliticoTextView.setText(deputado.getPartido());

        //TODO implemente aqui a atribuição dos demais valores...
    }

    @Override
    public int getItemCount() {
        return this.dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nomePoliticoTextView;
        private TextView partidoPoliticoTextView;
        private ImageView fotoPoliticoImageView;

        private TextView numeroVotacoesTextView;
        private TextView frasePresencasTextView;
        private ProgressBar presencaMensalProgressBar;

        public ViewHolder(View itemView){
            super(itemView);

            this.nomePoliticoTextView    = (TextView) itemView.findViewById(R.id.politico_nome_text_view);
            this.partidoPoliticoTextView = (TextView) itemView.findViewById(R.id.politico_part_text_view);
            this.fotoPoliticoImageView   = (ImageView) itemView.findViewById(R.id.politico_image_view);

            this.numeroVotacoesTextView  = (TextView) itemView.findViewById(R.id.total_votacoes_text_view);
            this.frasePresencasTextView  = (TextView) itemView.findViewById(R.id.politico_percentual_presenca_text_view);
            this.presencaMensalProgressBar = (ProgressBar) itemView.findViewById(R.id.politico_presenca_progressbar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Cliked", Toast.LENGTH_SHORT).show();
        }
    }
}
