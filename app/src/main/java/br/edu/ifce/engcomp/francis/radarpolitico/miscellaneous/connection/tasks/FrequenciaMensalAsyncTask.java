package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.interfaces.OnLoadFrequenciasHasFinished;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.FrequenciaMensalService;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Frequencia;

/**
 * Created by francisco on 24/03/16.
 */
public class FrequenciaMensalAsyncTask extends AsyncTask<ArrayList<Deputado>, Void, Void> {

    private ProgressDialog progressDialog;
    private OnLoadFrequenciasHasFinished delegate;

    public FrequenciaMensalAsyncTask(Context context, OnLoadFrequenciasHasFinished delegate) {
        this.progressDialog = new ProgressDialog(context);
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.progressDialog.setTitle("Baixando frequencia dos deputados...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    @Override
    protected Void doInBackground(ArrayList<Deputado>... params) {

        for (Deputado d : params[0]) {
            Frequencia frequencia = FrequenciaMensalService.obterFrequenciaParaDeputado(d.getMatricula());
            d.setFrequencia(frequencia);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        this.progressDialog.dismiss();
        delegate.onLoadFrequenciasHasFinished();
    }
}
