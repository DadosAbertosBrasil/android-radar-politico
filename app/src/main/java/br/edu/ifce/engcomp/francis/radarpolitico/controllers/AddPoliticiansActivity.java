package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.AddDeputadoRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

public class AddPoliticiansActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Deputado> datasource;

    public AddPoliticiansActivity(){
        datasource = generateDataSourceMock();
    }

    
    public ArrayList<Deputado> generateDataSourceMock(){
        ArrayList<Deputado> deputados = new ArrayList<>();

        Deputado teste1 = new Deputado("Joamila Brito", "PVDC", "CE");
        Deputado teste2 = new Deputado("Francisco Souza", "PVDC", "CE");
        Deputado teste3 = new Deputado("Ulysses Rocha", "PVDC", "RJ");

        deputados.add(teste1);
        deputados.add(teste2);
        deputados.add(teste3);

        return deputados;
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
