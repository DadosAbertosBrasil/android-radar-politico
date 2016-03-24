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
import br.edu.ifce.engcomp.francis.radarpolitico.models.Falta;

/**
 * Created by Joamila on 24/03/2016.
 */
public class DeputadoFaltasRecyclerViewAdapter extends RecyclerView.Adapter<DeputadoFaltasRecyclerViewAdapter.ViewHolder>{
    private ArrayList<Falta> dataSource;
    private Context context;

    public DeputadoFaltasRecyclerViewAdapter(ArrayList<Falta> dataSource, Context context) {
        this.dataSource = dataSource;
        this.context = context;
    }

    public Falta getItem(int position) {
        return this.dataSource.get(position);
    }

    @Override
    public DeputadoFaltasRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.adapter_deputado_faltas, parent, false);

        return new DeputadoFaltasRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeputadoFaltasRecyclerViewAdapter.ViewHolder holder, int position) {
        Falta falta = this.dataSource.get(position);

        holder.dataFaltaTextView.setText(falta.getData());
        holder.statusFaltaTextView.setText(falta.getStatus());
    }

    @Override
    public int getItemCount() {
        return this.dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView dataFaltaTextView;
        private TextView statusFaltaTextView;


        public ViewHolder(View itemView){
            super(itemView);

            this.dataFaltaTextView    = (TextView) itemView.findViewById(R.id.data_falta_text_view);
            this.statusFaltaTextView = (TextView) itemView.findViewById(R.id.status_falta_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Cliked", Toast.LENGTH_SHORT).show();
        }
    }
}
