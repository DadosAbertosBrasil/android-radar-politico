package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.AddDeputadoRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.interfaces.OnLoadDeputadosHasFinished;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.tasks.DeputadosAsyncTask;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

public class AddPoliticiansActivity extends AppCompatActivity implements OnLoadDeputadosHasFinished {
    RecyclerView recyclerView;
    ArrayList<Deputado> datasource;
    AddDeputadoRecyclerViewAdapter adapter;


    public AddPoliticiansActivity(){
        this.datasource = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_politicians);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        this.recyclerView   = (RecyclerView) findViewById(R.id.add_politicos_recyler_view);
        initRecyclerView();

        DeputadosAsyncTask task = new DeputadosAsyncTask(this, this);
        task.execute();
    }

    @Override
    public void onDeputadosFinishedLoading(ArrayList<Deputado> deputados) {
        this.datasource.clear();
        this.datasource.addAll(deputados);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("DEPUTADO_ADD", adapter.deputadoFoiAdicioando);

        setResult(RESULT_OK, resultIntent);
    }


    private void initRecyclerView() {
        LinearLayoutManager layoutManager   = new LinearLayoutManager(this);
        this.adapter = new AddDeputadoRecyclerViewAdapter(this.datasource, this);

        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
