package br.ifce.engcomp.francis.radarpolitico.models

import br.edu.ifce.engcomp.francis.radarpolitico.models.Frequencia

/**
 * Created by francisco on 11/03/16.
 */

data class Deputado(
    var idCadastro: String? = null,
    var matricula: String? = null,
    var idParlamentar: String? = null,

    var condicao: String? = null,
    var nome: String? = null,
    var nomeParlamentar: String? = null,
    var urlFoto: String? = null,

    var partido: String? = null,

    var gabinete: String? = null,
    var anexo: String? = null,

    var uf: String? = null,
    var fone: String? = null,
    var email: String? = null,

    var dataNascimento: String? = null,
    var situacaoLegislaturaAtual: String? = null,
    var ufRepresentacaoAtual: String? = null,
    var nomeProfissao: String? = null,

    var frequencia: Frequencia? = null
)
