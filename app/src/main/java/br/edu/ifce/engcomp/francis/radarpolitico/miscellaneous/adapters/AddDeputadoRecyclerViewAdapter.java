package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by Joamila on 16/03/2016.
 */
public class AddDeputadoRecyclerViewAdapter extends RecyclerView.Adapter<AddDeputadoRecyclerViewAdapter.ViewHolder>  {
    private ArrayList<Deputado> dataSource;
    private Context context;

    public AddDeputadoRecyclerViewAdapter(ArrayList<Deputado> dataSource, Context context) {
        this.dataSource = dataSource;
        this.context = context;
    }

    public Deputado getItem(int position) {
        return this.dataSource.get(position);
    }

    @Override
    public AddDeputadoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.adapter_add_politico, parent, false);

        return new AddDeputadoRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddDeputadoRecyclerViewAdapter.ViewHolder holder, int position) {
        Deputado deputado = this.dataSource.get(position);

        holder.nomePoliticoTextView.setText(deputado.getNome());
        holder.partidoPoliticoTextView.setText(deputado.getPartido() + " - " + deputado.getUf());
    }

    @Override
    public int getItemCount() {
        return this.dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nomePoliticoTextView;
        private TextView partidoPoliticoTextView;
        private ImageView fotoPoliticoImageView;


        public ViewHolder(View itemView){
            super(itemView);

            this.nomePoliticoTextView    = (TextView) itemView.findViewById(R.id.add_politico_nome_text_view);
            this.partidoPoliticoTextView = (TextView) itemView.findViewById(R.id.add_politico_partido_text_view);
            this.fotoPoliticoImageView   = (ImageView) itemView.findViewById(R.id.add_politico_image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Cliked", Toast.LENGTH_SHORT).show();
        }
    }
}
