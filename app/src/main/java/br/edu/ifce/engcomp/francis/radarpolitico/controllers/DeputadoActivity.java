package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoFaltasRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoVotacoesRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Falta;
import br.edu.ifce.engcomp.francis.radarpolitico.models.VotacaoDeputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

public class DeputadoActivity extends AppCompatActivity {
    Voto deputadoAtual;
    String deputado;
    RecyclerView recyclerViewFaltas;
    ArrayList<Falta> datasourceFaltas;
    RecyclerView recyclerViewVotacoes;
    ArrayList<VotacaoDeputado> datasourceVotacoes;
    FloatingActionButton addDeputadoButton;


    public DeputadoActivity(){
        this.datasourceFaltas = generateDataSourceFaltasMock();
        this.datasourceVotacoes = generateDataSourceVotacoesMock();
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

    public ArrayList<VotacaoDeputado> generateDataSourceVotacoesMock(){
        ArrayList<VotacaoDeputado> votacoes = new ArrayList<>();

        VotacaoDeputado teste1 = new VotacaoDeputado("PEC00/1969", "02/02/2016", "SIM");
        VotacaoDeputado teste2 = new VotacaoDeputado("PL99/1932", "15/02/2016", "SIM");
        VotacaoDeputado teste3 = new VotacaoDeputado("ADV24/1944", "12/03/2016", "NÃO");

        votacoes.add(teste1);
        votacoes.add(teste2);
        votacoes.add(teste3);

        return votacoes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputado);

        Intent currentIntent = getIntent();
        deputado = currentIntent.getStringExtra("DEPUTADO_INFOS");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(deputado);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init();
        initAddDeputadoButton();
        initRecyclerViewFaltas();
        initRecyclerViewVotacoes();

        Log.i("TOOLBAR", (String) toolbar.getTitle());
    }

    public void init(){
        this.addDeputadoButton = (FloatingActionButton) findViewById(R.id.fab_add_deputado);

        ImageView fotoPoliticoImageView = (ImageView) findViewById(R.id.foto_deputado_image_view);
        TextView nomeDeputadoTextView = (TextView) findViewById(R.id.nome_deputado_text_view);
        TextView partidoDeputadoTextView = (TextView) findViewById(R.id.partido_deputado_text_view);
        TextView profissaoDeputadoTextView = (TextView) findViewById(R.id.profissao_deputado_text_view);
        TextView dataNascDeputadoTextView = (TextView) findViewById(R.id.data_nasc_deputado_text_view);
        TextView telefoneDeputadoTextView = (TextView) findViewById(R.id.telefone_deputado_text_view);

        this.recyclerViewFaltas   = (RecyclerView) findViewById(R.id.deputado_faltas_recyler_view);
        this.recyclerViewVotacoes   = (RecyclerView) findViewById(R.id.deputado_votacoes_recyler_view);

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

    public void initRecyclerViewVotacoes(){
        LinearLayoutManager layoutManager   = new LinearLayoutManager(this);
        DeputadoVotacoesRecyclerViewAdapter adapter = new DeputadoVotacoesRecyclerViewAdapter(this.datasourceVotacoes, this);

        this.recyclerViewVotacoes.setHasFixedSize(false);
        this.recyclerViewVotacoes.setLayoutManager(layoutManager);
        this.recyclerViewVotacoes.setAdapter(adapter);
        this.recyclerViewVotacoes.setItemAnimator(new DefaultItemAnimator());
    }

    private void initAddDeputadoButton() {
        this.addDeputadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Seguindo deputado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
