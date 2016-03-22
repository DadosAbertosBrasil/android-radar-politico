package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers.IndexPath;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

/**
 * Created by francisco on 12/03/16.
 */
public class VotacoesRecyclerViewAdapter extends SectionedRecyclerViewAdapter<VotacoesRecyclerViewAdapter.ViewHolder> {

    private HashMap<Votacao, ArrayList<Voto>> datasource;

    public VotacoesRecyclerViewAdapter(HashMap<Votacao, ArrayList<Voto>> datasource) {
        this.datasource = datasource;
    }

    @Override
    public int getSectionCount() {
        return this.datasource.keySet().size();
    }

    @Override
    public int getItemCount(int section) {
        Votacao key = (Votacao) this.datasource.keySet().toArray()[section];
        ArrayList<Voto> votos = this.datasource.get(key);

        return votos.size();
    }

    @Override
    public void onBindHeaderViewHolder(VotacoesRecyclerViewAdapter.ViewHolder holder, int section) {
        Votacao key = (Votacao) this.datasource.keySet().toArray()[section];

        holder.title.setText(key.getResumo());
        holder.subtitle.setText(key.getData());

        holder.indexPath.setSection(section);
        holder.indexPath.setRow(-1);
    }

    @Override
    public void onBindViewHolder(VotacoesRecyclerViewAdapter.ViewHolder holder, int section, int relativePosition, int absolutePosition) {
        Votacao key = (Votacao) this.datasource.keySet().toArray()[section];
        ArrayList<Voto> votos = this.datasource.get(key);
        Voto voto = votos.get(relativePosition);

        holder.title.setText(voto.getNome());
        holder.subtitle.setText(voto.getVoto().toUpperCase());
        holder.setBackgroundColor(voto);

        Log.i("SECTION", String.valueOf(section));

        holder.indexPath.setSection(section);
        holder.indexPath.setRow(relativePosition);
    }

    @Override
    public VotacoesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resource = viewType == VIEW_TYPE_HEADER? R.layout.list_item_header : R.layout.list_item;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = (View) inflater.inflate(resource, parent, false);

        ViewHolder holder = new ViewHolder(itemView);
        holder.viewType = viewType;

        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int viewType;
        private View itemView;
        private IndexPath indexPath;

        private TextView title;
        private TextView subtitle;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            this.title      = (TextView) itemView.findViewById(R.id.title);
            this.subtitle   = (TextView) itemView.findViewById(R.id.subtitle);
            this.icon       = (ImageView) itemView.findViewById(R.id.icon);
            this.itemView   = itemView;

            this.itemView.setOnClickListener(this);
            this.indexPath = new IndexPath();
        }

        @Override
        public void onClick(View v) {
            if (this.viewType == VIEW_TYPE_HEADER) {
                Toast.makeText(v.getContext(), "Header Click", Toast.LENGTH_SHORT).show();
            }
            else if(indexPath != null){
                Toast.makeText(v.getContext(), String.format("Item cliked at: [%d, %d]", indexPath.getSection(), indexPath.getRow()), Toast.LENGTH_SHORT).show();
            }
        }

        public void setBackgroundColor(Voto v) {
            int color = this.itemView.getResources().getColor(R.color.colorBackground);

            if(v.getVoto().toLowerCase().equals("sim")) {
                color = this.itemView.getResources().getColor(R.color.colorVoteYes);
            }
            else if (v.getVoto().toLowerCase().equals("não")) {
                color = this.itemView.getResources().getColor(R.color.colorVoteNo);
            }

            this.itemView.setBackgroundColor(color);

        }
    }

}
