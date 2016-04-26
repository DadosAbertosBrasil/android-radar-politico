package br.edu.ifce.engcomp.francis.radarpolitico.models

/**
 * Created by francisco on 12/03/16.
 */
data class Voto(
    var voto: String? = "",

    var nome: String? = "",
    var partido: String? = "",
    var idCadastro: String? = "",
    var uf: String? = ""
)
