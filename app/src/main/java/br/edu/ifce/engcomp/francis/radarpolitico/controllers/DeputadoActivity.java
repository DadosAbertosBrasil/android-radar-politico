package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

public class DeputadoActivity extends AppCompatActivity {
    Toolbar toolbar;
    Voto deputadoAtual;
    String deputado;

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
}
