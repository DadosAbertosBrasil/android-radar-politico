package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.AddDeputadoRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

public class AddPoliticiansActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Deputado> datasource;

    public AddPoliticiansActivity(){
        datasource = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_politicians);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recyclerView   = (RecyclerView) findViewById(R.id.add_politicos_recyler_view);
        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager   = new LinearLayoutManager(this);
        AddDeputadoRecyclerViewAdapter adapter = new AddDeputadoRecyclerViewAdapter(this.datasource, this);

        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
