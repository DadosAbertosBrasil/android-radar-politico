package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.interfaces.OnVotedPropositionsHasLoaded;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.ProposicaoService;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.ProposicoesService;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.VotacaoService;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;

/**
 *
 * Created by francisco on 17/03/16.
 *
 */


public class ProposicoesVotadasAsyncTask extends AsyncTask<String, Void, ArrayList<Proposicao>>{
    private Context context;
    private OnVotedPropositionsHasLoaded propositionsHasLoadedDelegate;
    private ProgressDialog progressDialog;

    public ProposicoesVotadasAsyncTask(Context context, OnVotedPropositionsHasLoaded propositionsHasLoadedDelegate) {
        this.context = context;
        this.propositionsHasLoadedDelegate = propositionsHasLoadedDelegate;
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
            Proposicao p = ProposicaoService.obterProposicaoPorID(proposicao.getId());
            p.setUltimaVotacao(VotacaoService.obterVotacaoDeProposicao(p.getSigla(), p.getNumero(), p.getAno()));

            proposicao.merge(p);

        }

        return proposicoes;
    }

    @Override
    protected void onPostExecute(ArrayList<Proposicao> proposicoes) {
        super.onPostExecute(proposicoes);

        this.propositionsHasLoadedDelegate.onPrepositionsLoaded(proposicoes);
        this.progressDialog.dismiss();
    }
}
