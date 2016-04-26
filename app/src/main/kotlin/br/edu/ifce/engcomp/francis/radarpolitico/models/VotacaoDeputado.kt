package br.edu.ifce.engcomp.francis.radarpolitico.models

/**
 * Created by Joamila on 24/03/2016.
 */
data class VotacaoDeputado(var codSessao: String = "",
                           var data: String = "",
                           var voto: String = "") {

    var idDeputado: String? = null
}
