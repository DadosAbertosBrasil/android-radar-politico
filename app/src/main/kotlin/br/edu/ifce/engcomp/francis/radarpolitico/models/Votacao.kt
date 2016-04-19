package br.edu.ifce.engcomp.francis.radarpolitico.models

import java.util.ArrayList

import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto

/**
 * Created by francisco on 12/03/16.
 */
data class Votacao(
    var resumo: String = "",

    var data: String = "",
    var hora: String = "",

    var objetoVotacao: String = "",
    var codigoSessao: String = "",

    var totalVotosSim: Int = 0,
    var totalVotosNao: Int = 0,
    var totalVotosAbstencao: Int = 0,
    var totalVotosObstrucao: Int = 0,
    var totalVotosSessao: Int = 0,

    var votos: ArrayList<Voto>? = null
)
