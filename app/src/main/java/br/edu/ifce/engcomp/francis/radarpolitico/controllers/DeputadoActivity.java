package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoFaltasRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Falta;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

public class DeputadoActivity extends AppCompatActivity {
    Toolbar toolbar;
    Voto deputadoAtual;
    String deputado;
    RecyclerView recyclerViewFaltas;
    ArrayList<Falta> datasourceFaltas;

    public DeputadoActivity(){
        this.datasourceFaltas = generateDataSourceFaltasMock();
    }

    public ArrayList<Falta> generateDataSourceFaltasMock(){
        ArrayList<Falta> faltas = new ArrayList<>();

        Falta teste1 = new Falta("01/02/2016", "Justificada");
        Falta teste2 = new Falta("05/03/2016", "Justificada");
        Falta teste3 = new Falta("15/03/2016", "Não-Justificada");

        faltas.add(teste1);
        faltas.add(teste2);
        faltas.add(teste3);

        return faltas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputado);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init();
        this.recyclerViewFaltas   = (RecyclerView) findViewById(R.id.deputado_faltas_recyler_view);
        initRecyclerViewFaltas();
    }

    public void init(){
        Intent currentIntent = getIntent();
        //deputadoAtual = (Voto) currentIntent.getSerializableExtra("DEPUTADO_INFOS");
        deputado = currentIntent.getStringExtra("DEPUTADO_INFOS");

        toolbar.setTitle(deputado);

        ImageView fotoPoliticoImageView = (ImageView) findViewById(R.id.foto_deputado_image_view);
        TextView nomeDeputadoTextView = (TextView) findViewById(R.id.nome_deputado_text_view);
        TextView partidoDeputadoTextView = (TextView) findViewById(R.id.partido_deputado_text_view);
        TextView profissaoDeputadoTextView = (TextView) findViewById(R.id.profissao_deputado_text_view);
        TextView dataNascDeputadoTextView = (TextView) findViewById(R.id.data_nasc_deputado_text_view);
        TextView telefoneDeputadoTextView = (TextView) findViewById(R.id.telefone_deputado_text_view);

        nomeDeputadoTextView.setText(deputado);
        //partidoDeputadoTextView.setText(deputadoAtual.getPartido() + "/" + deputadoAtual.getUf());
        //profissaoDeputadoTextView.setText(deputadoAtual.getNomeProfissao());
        //dataNascDeputadoTextView.setText(deputadoAtual.getDataNascimento());
        //telefoneDeputadoTextView.setText(deputadoAtual.getFone());
    }

    public void initRecyclerViewFaltas(){
        LinearLayoutManager layoutManager   = new LinearLayoutManager(this);
        DeputadoFaltasRecyclerViewAdapter adapter = new DeputadoFaltasRecyclerViewAdapter(this.datasourceFaltas, this);

        this.recyclerViewFaltas.setHasFixedSize(false);
        this.recyclerViewFaltas.setLayoutManager(layoutManager);
        this.recyclerViewFaltas.setAdapter(adapter);
        this.recyclerViewFaltas.setItemAnimator(new DefaultItemAnimator());
    }
}
