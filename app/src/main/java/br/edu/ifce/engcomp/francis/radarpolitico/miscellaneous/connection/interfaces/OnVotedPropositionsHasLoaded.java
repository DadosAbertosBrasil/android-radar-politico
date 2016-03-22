package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.interfaces;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;

/**
 * Created by francisco on 17/03/16.
 */
public interface OnVotedPropositionsHasLoaded {
    void onPrepositionsLoaded(ArrayList<Proposicao> proposicoes);
}
