package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.interfaces;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by francisco on 22/03/16.
 */
public interface OnLoadDeputadosHasFinished {
    void onDeputadosFinishedLoading(ArrayList <Deputado> deputados);
}
