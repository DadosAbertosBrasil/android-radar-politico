package br.edu.ifce.engcomp.francis.radarpolitico.models

import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao

/**
 * Created by francisco on 14/03/16.
 */
data class Proposicao (
    var id: String? = "",
    var nome: String? = "",

    var sigla: String? = "",
    var numero: String? = "",
    var ano: String? = "",

    var tipoProposicao: String? = "",
    var tema: String? = "",
    var ementa: String? = "",
    var explicacaoEmenta: String? = "",
    var indexacao: String? = "",

    var regimeTramitacao: String? = "",
    var situacao: String? = "",

    var urlInteiroTeor: String? = "",

    var quantidadeAutores: String? = "",

    var idAutor: String? = "",
    var nomeAutor: String? = "",

    var ultimaVotacao: Votacao? = null
)
