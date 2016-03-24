package br.edu.ifce.engcomp.francis.radarpolitico.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.VotacoesRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.interfaces.OnVotedPropositionsHasLoaded;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.tasks.ProposicoesVotadasAsyncTask;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

public class VotacoesFragment extends Fragment implements OnVotedPropositionsHasLoaded{
    RecyclerView recyclerView;
    VotacoesRecyclerViewAdapter adapter;

    HashMap<Votacao, ArrayList<Voto>> datasource;

    
    public VotacoesFragment() {
        datasource = new HashMap<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProposicoesVotadasAsyncTask task = new ProposicoesVotadasAsyncTask(getActivity(), this);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        //task.execute(String.valueOf(year));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_votacoes, container, false);

        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.votacoes_recycler_view);
        this.initRecyclerView();

        return rootView;
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager   = new LinearLayoutManager(getActivity());

        this.adapter = new VotacoesRecyclerViewAdapter(this.datasource);

        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onPrepositionsLoaded(ArrayList<Proposicao> proposicoes) {
        for (Proposicao p : proposicoes){

            try{
                datasource.put(p.getUltimaVotacao(), p.getUltimaVotacao().getVotos());
            }
            catch (NullPointerException e){
            }
        }

        this.adapter.notifyDataSetChanged();

        Log.i("DATASOURCE-SIZE", String.valueOf(datasource.keySet().size()));
    }
}
