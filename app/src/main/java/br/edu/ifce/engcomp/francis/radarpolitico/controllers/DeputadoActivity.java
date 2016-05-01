package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoFaltasRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoVotacoesRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Dia;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Falta;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Frequencia;
import br.edu.ifce.engcomp.francis.radarpolitico.models.VotacaoDeputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

public class DeputadoActivity extends AppCompatActivity {
    Deputado deputado;
    RecyclerView recyclerViewFaltas;
    ArrayList<Falta> datasourceFaltas;
    RecyclerView recyclerViewVotacoes;
    ArrayList<VotacaoDeputado> datasourceVotacoes;

    public DeputadoActivity(){
        this.datasourceVotacoes = generateDataSourceVotacoesMock();
    }

    public ArrayList<Falta> generateDataSourceFaltasMock(){
        Intent currentIntent = getIntent();
        ArrayList<Dia> dias = (ArrayList<Dia>) currentIntent.getSerializableExtra("DEPUTADO_FREQUENCIA");

        ArrayList<Falta> faltas = new ArrayList<>();

        if(dias!=null){
            Log.i("TESTE-FALTAS", "Cheguei aqui");
            for(int i = 0; i<dias.size(); i++){
                if (!dias.get(i).getFrequencia().equals("Presença")){
                    faltas.add(new Falta(dias.get(i).getData(), dias.get(i).getFrequencia()));
                }
            }
        }

        return faltas;

        /*Falta teste1 = new Falta("01/02/2016", "Justificada");
        Falta teste2 = new Falta("05/03/2016", "Justificada");
        Falta teste3 = new Falta("15/03/2016", "Não-Justificada");

        faltas.add(teste1);
        faltas.add(teste2);
        faltas.add(teste3);*/

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
        deputado = (Deputado) currentIntent.getSerializableExtra("DEPUTADO_INFOS");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(deputado.getNomeParlamentar());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init();
        this.datasourceFaltas = generateDataSourceFaltasMock();
        initRecyclerViewFaltas();
        initRecyclerViewVotacoes();
    }


    public void init(){

        ImageView fotoPoliticoImageView = (ImageView) findViewById(R.id.foto_deputado_image_view);
        TextView nomeDeputadoTextView = (TextView) findViewById(R.id.nome_deputado_text_view);
        TextView partidoDeputadoTextView = (TextView) findViewById(R.id.partido_deputado_text_view);
        TextView profissaoDeputadoTextView = (TextView) findViewById(R.id.profissao_deputado_text_view);
        TextView dataNascDeputadoTextView = (TextView) findViewById(R.id.data_nasc_deputado_text_view);
        TextView telefoneDeputadoTextView = (TextView) findViewById(R.id.telefone_deputado_text_view);
        TextView emailDeputadoTextView = (TextView) findViewById(R.id.email_deputado_text_view);

        this.recyclerViewFaltas   = (RecyclerView) findViewById(R.id.deputado_faltas_recyler_view);
        this.recyclerViewVotacoes   = (RecyclerView) findViewById(R.id.deputado_votacoes_recyler_view);

        nomeDeputadoTextView.setText(deputado.getNomeParlamentar());
        partidoDeputadoTextView.setText(deputado.getPartido() + "/" + deputado.getUf());
        profissaoDeputadoTextView.setText(deputado.getNomeProfissao());
        dataNascDeputadoTextView.setText(deputado.getDataNascimento());
        emailDeputadoTextView.setText(deputado.getEmail());
        telefoneDeputadoTextView.setText(deputado.getFone());

        if(deputado.getUrlFoto()!=null){
            Picasso.with(this).load(deputado.getUrlFoto()).into(fotoPoliticoImageView);
        }
        else {
            fotoPoliticoImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_smile));
        }
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
}
