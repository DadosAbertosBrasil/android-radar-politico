package br.edu.ifce.engcomp.francis.radarpolitico.models

import java.util.*
import android.os.Parcel
import android.os.Parcelable

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

        var dataVotacao: Date? = null,
        var ultimaVotacao: Votacao? = null) : Parcelable {

    constructor(source: Parcel): this(
            source.readString(),
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as Date?,
            source.readParcelable<Votacao?>(Votacao::class.java.classLoader))

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeSerializable(nome)
        dest?.writeSerializable(sigla)
        dest?.writeSerializable(numero)
        dest?.writeSerializable(ano)
        dest?.writeSerializable(tipoProposicao)
        dest?.writeSerializable(tema)
        dest?.writeSerializable(ementa)
        dest?.writeSerializable(explicacaoEmenta)
        dest?.writeSerializable(indexacao)
        dest?.writeSerializable(regimeTramitacao)
        dest?.writeSerializable(situacao)
        dest?.writeSerializable(urlInteiroTeor)
        dest?.writeSerializable(quantidadeAutores)
        dest?.writeSerializable(idAutor)
        dest?.writeSerializable(nomeAutor)
        dest?.writeSerializable(dataVotacao)
        dest?.writeParcelable(ultimaVotacao, 0)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<Proposicao> = object : Parcelable.Creator<Proposicao> {
            override fun createFromParcel(source: Parcel): Proposicao {
                return Proposicao(source)
            }

            override fun newArray(size: Int): Array<Proposicao?> {
                return arrayOfNulls(size)
            }
        }
    }

    fun merge(p: Proposicao) {
        this.nome = if (p.nome == null) this.nome else p.nome;
        this.numero = if (p.numero == null) this.numero else p.numero;
        this.sigla = if (p.sigla == null) this.sigla else p.sigla;
        this.ano = if (p.ano == null) this.ano else p.ano;
        this.tipoProposicao = if (p.tipoProposicao == null) this.tipoProposicao else p.tipoProposicao;
        this.tema = if (p.tema == null) this.tema else p.tema;
        this.ementa = if (p.ementa == null) this.ementa else p.ementa;
        this.explicacaoEmenta = if (p.explicacaoEmenta == null) this.explicacaoEmenta else p.explicacaoEmenta;
        this.indexacao = if (p.indexacao == null) this.indexacao else p.indexacao;
        this.regimeTramitacao = if (p.regimeTramitacao == null) this.regimeTramitacao else p.regimeTramitacao;
        this.situacao = if (p.situacao == null) this.situacao else p.situacao;
        this.urlInteiroTeor = if (p.urlInteiroTeor == null) this.urlInteiroTeor else p.urlInteiroTeor;
        this.quantidadeAutores = if (p.quantidadeAutores == null) this.quantidadeAutores else p.quantidadeAutores;
        this.idAutor = if (p.idAutor == null) this.idAutor else p.idAutor;
        this.nomeAutor = if (p.nomeAutor == null) this.nomeAutor else p.nomeAutor;
        this.ultimaVotacao = if (p.ultimaVotacao == null) this.ultimaVotacao else p.ultimaVotacao;
    }
}
