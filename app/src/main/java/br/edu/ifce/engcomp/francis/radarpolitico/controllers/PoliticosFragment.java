package br.edu.ifce.engcomp.francis.radarpolitico.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoliticosFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Deputado> datasource;
    FloatingActionButton addDeputadoFAB;

    public PoliticosFragment() {
        datasource = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateDataSource();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_politicos, container, false);

        this.recyclerView   = (RecyclerView) rootView.findViewById(R.id.politicos_recyler_view);
        this.addDeputadoFAB = (FloatingActionButton) rootView.findViewById(R.id.fab_add_politico);

        initRecyclerView();
        initAddDeputadoFAB();

        return rootView;
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager   = new LinearLayoutManager(getActivity());
        DeputadoRecyclerViewAdapter adapter = new DeputadoRecyclerViewAdapter(this.datasource, getActivity());

        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initAddDeputadoFAB() {
        this.addDeputadoFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddPoliticosActivity = new Intent(getActivity(), AddPoliticiansActivity.class);
                startActivity(intentAddPoliticosActivity);
            }
        });
    }

    private void populateDataSource() {
        DeputadoDAO deputadoDAO = new DeputadoDAO(getActivity());
        ArrayList<Deputado> deputados = deputadoDAO.listAll();

        this.datasource.addAll(deputados);
    }
}
