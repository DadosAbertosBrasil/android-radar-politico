package br.edu.ifce.engcomp.francis.radarpolitico.models

data class Deputado(
    var idCadastro: String? = "",
    var matricula: String? = "",
    var idParlamentar: String? = "",

    var condicao: String? = "",
    var nome: String? = "",
    var nomeParlamentar: String? = "",
    var urlFoto: String? = "",

    var partido: String? = "",

    var gabinete: String? = "",
    var anexo: String? = "",

    var uf: String? = "",
    var fone: String? = "",
    var email: String? = "",

    var dataNascimento: String? = "",
    var situacaoLegislaturaAtual: String? = "",
    var ufRepresentacaoAtual: String? = "",
    var nomeProfissao: String? = "",

    var frequencia: Frequencia? = null)
