package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.ProposicoesParser;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.ProposicaoService;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.ProposicoesService;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.VotacaoService;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;

/**
 *
 * Created by francisco on 17/03/16.
 *
 */


public class ProposicoesVotadasAsyncTask extends AsyncTask<String, Void, ArrayList<Proposicao>>{
    private Context context;
    private ProgressDialog progressDialog;

    public ProposicoesVotadasAsyncTask(Context context) {
        this.context = context;
        this.progressDialog = new ProgressDialog(this.context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.progressDialog.setMessage("Baixando lista de Proposições...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    @Override
    protected ArrayList<Proposicao> doInBackground(String... params) {

        ArrayList<Proposicao> proposicoes = ProposicoesService.loadProposicoesVotadasNoAno(params[0]);

        for (Proposicao proposicao : proposicoes) {
            proposicao = ProposicaoService.obterProposicaoPorID(proposicao.getId());
            proposicao.setUltimaVotacao(VotacaoService.obterVotacaoDeProposicao(proposicao.getSigla(), proposicao.getId(), proposicao.getAno()));
        }

        return proposicoes;
    }

    @Override
    protected void onPostExecute(ArrayList<Proposicao> proposicoes) {
        super.onPostExecute(proposicoes);

        this.progressDialog.dismiss();
    }
}
