package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoFaltasRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoVotacoesRecyclerViewAdapter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.FrequenciaParser;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.ProposicaoParser;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.ProposicoesParser;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Dia;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Falta;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Frequencia;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.VotacaoDeputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

public class DeputadoActivity extends AppCompatActivity {
    Deputado deputado;
    RecyclerView recyclerViewFaltas;
    ArrayList<Falta> datasourceFaltas;
    RecyclerView recyclerViewVotacoes;
    ArrayList<VotacaoDeputado> datasourceVotacoes;
    ArrayList<Falta> faltas = new ArrayList<>();
    ProgressDialog progressDialog;
    DeputadoFaltasRecyclerViewAdapter adapterFaltas;
    DeputadoVotacoesRecyclerViewAdapter adapterVotacoes;
    ArrayList<Proposicao> proposicoes = new ArrayList<>();

    public DeputadoActivity(){

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
    }


    public void init(){

        ImageView fotoPoliticoImageView = (ImageView) findViewById(R.id.foto_deputado_image_view);
        TextView nomeDeputadoTextView = (TextView) findViewById(R.id.nome_deputado_text_view);
        TextView partidoDeputadoTextView = (TextView) findViewById(R.id.partido_deputado_text_view);
        TextView condicaoDeputadoTextView = (TextView) findViewById(R.id.condicao_deputado_text_view);
        TextView gabineteDeputadoTextView = (TextView) findViewById(R.id.gabinete_deputado_text_view);
        TextView telefoneDeputadoTextView = (TextView) findViewById(R.id.telefone_deputado_text_view);
        TextView emailDeputadoTextView = (TextView) findViewById(R.id.email_deputado_text_view);


        nomeDeputadoTextView.setText(deputado.getNome());
        condicaoDeputadoTextView.setText(deputado.getCondicao());
        partidoDeputadoTextView.setText(deputado.getPartido() + "/" + deputado.getUf());
        gabineteDeputadoTextView.setText("Gabinete: " + deputado.getGabinete() + " Anexo: " + deputado.getAnexo());

        emailDeputadoTextView.setText(deputado.getEmail());
        telefoneDeputadoTextView.setText("(61) " + deputado.getFone());

        if(deputado.getUrlFoto()!=null){
            Picasso.with(this).load(deputado.getUrlFoto()).into(fotoPoliticoImageView);
        }
        else {
            fotoPoliticoImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_smile));
        }
    }
}
