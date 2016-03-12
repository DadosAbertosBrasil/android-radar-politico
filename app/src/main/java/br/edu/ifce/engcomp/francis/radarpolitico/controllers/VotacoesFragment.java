package br.edu.ifce.engcomp.francis.radarpolitico.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.dummy.VotacoesMock;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.VotacoesRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

public class VotacoesFragment extends Fragment {
    RecyclerView recyclerView;
    HashMap<Votacao, ArrayList<Voto>> datasource;

    
    public VotacoesFragment() {
        datasource = VotacoesMock.makeVotacao();

//        datasource = new HashMap<>();
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
        VotacoesRecyclerViewAdapter adapter = new VotacoesRecyclerViewAdapter(this.datasource);

        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
