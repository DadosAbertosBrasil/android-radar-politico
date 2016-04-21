package br.edu.ifce.engcomp.francis.radarpolitico.models

import java.io.Serializable

/**
 * Created by francisco on 14/03/16.
 */
data class Proposicao (
    var id: String = "",
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

    var dataVotacao: String = "",
    var ultimaVotacao: Votacao? = null) : Serializable{

    fun merge(p: Proposicao){
        this.nome               = if (p.nome == null) this.nome else p.nome;
        this.numero             = if (p.numero == null) this.numero else p.numero;
        this.sigla              = if (p.sigla == null) this.sigla else p.sigla;
        this.ano                = if (p.ano == null) this.ano else p.ano;
        this.tipoProposicao     = if (p.tipoProposicao == null) this.tipoProposicao else p.tipoProposicao;
        this.tema               = if (p.tema == null) this.tema else p.tema;
        this.ementa             = if (p.ementa == null) this.ementa else p.ementa;
        this.explicacaoEmenta   = if (p.explicacaoEmenta == null) this.explicacaoEmenta else p.explicacaoEmenta;
        this.indexacao          = if (p.indexacao == null) this.indexacao else p.indexacao;
        this.regimeTramitacao   = if (p.regimeTramitacao == null) this.regimeTramitacao else p.regimeTramitacao;
        this.situacao           = if (p.situacao == null) this.situacao else p.situacao;
        this.urlInteiroTeor     = if (p.urlInteiroTeor == null) this.urlInteiroTeor else p.urlInteiroTeor;
        this.quantidadeAutores  = if (p.quantidadeAutores == null) this.quantidadeAutores else p.quantidadeAutores;
        this.idAutor            = if (p.idAutor == null) this.idAutor else p.idAutor;
        this.nomeAutor          = if (p.nomeAutor == null) this.nomeAutor else p.nomeAutor;
        this.ultimaVotacao      = if (p.ultimaVotacao == null) this.ultimaVotacao else p.ultimaVotacao;
    }
}
