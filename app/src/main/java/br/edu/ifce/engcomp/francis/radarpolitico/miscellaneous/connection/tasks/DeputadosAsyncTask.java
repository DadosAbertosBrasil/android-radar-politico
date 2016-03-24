package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.interfaces.OnLoadDeputadosHasFinished;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services.DeputadoService;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 *
 * Created by francisco on 15/03/16.
 *
 */
public class DeputadosAsyncTask extends AsyncTask<String, Void, ArrayList<Deputado>> {
    private Context context;
    private ProgressDialog progressDialog;
    OnLoadDeputadosHasFinished delegate;

    public DeputadosAsyncTask(Context context, OnLoadDeputadosHasFinished delegate) {
        this.context = context;
        this.delegate = delegate;

        this.progressDialog = new ProgressDialog(this.context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.progressDialog.setMessage("Baixando lista de Deputados...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    @Override
    protected ArrayList<Deputado> doInBackground(String... params) {
       return DeputadoService.listAllDeputados();
    }

    @Override
    protected void onPostExecute(ArrayList<Deputado> deputados) {
        super.onPostExecute(deputados);
        this.progressDialog.dismiss();

        this.delegate.onDeputadosFinishedLoading(deputados);
    }
}
