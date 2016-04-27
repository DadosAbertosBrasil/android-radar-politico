package br.edu.ifce.engcomp.francis.radarpolitico.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by francisco on 12/03/16.
 */
data class Votacao(
    var resumo: String? = "",

    var data: String? = "",
    var hora: String? = "",

    var objetoVotacao: String? = "",
    var codigoSessao:  String? = "",

    var totalVotosSim: String = "",
    var totalVotosNao: String = "",
    var totalVotosAbstencao: String = "",
    var totalVotosObstrucao: String = "",
    var totalVotosSessao: String =  "",

    var votos: ArrayList<Voto> = ArrayList<Voto>()) : Parcelable {

    constructor(source: Parcel): this(
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readSerializable() as String?,
            source.readString(), source.readString(),
            source.readString(), source.readString(),
            source.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeSerializable(resumo)
        dest?.writeSerializable(data)
        dest?.writeSerializable(hora)
        dest?.writeSerializable(objetoVotacao)
        dest?.writeSerializable(codigoSessao)
        dest?.writeString(totalVotosSim)
        dest?.writeString(totalVotosNao)
        dest?.writeString(totalVotosAbstencao)
        dest?.writeString(totalVotosObstrucao)
        dest?.writeString(totalVotosSessao)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<Votacao> = object : Parcelable.Creator<Votacao> {
            override fun createFromParcel(source: Parcel): Votacao {
                return Votacao(source)
            }

            override fun newArray(size: Int): Array<Votacao?> {
                return arrayOfNulls(size)
            }
        }
    }

    fun merge(vt: Votacao) {
        this.resumo = if (vt.resumo == null) this.resumo else vt.resumo

        this.data = if (vt.data == null) this.data else vt.data
        this.hora = if (vt.hora == null) this.hora else vt.hora

        this.objetoVotacao = if (vt.objetoVotacao == null) this.objetoVotacao else vt.objetoVotacao
        this.codigoSessao = if (vt.codigoSessao == null) this.codigoSessao else vt.codigoSessao
        this.totalVotosSim = if (vt.totalVotosSim == null) this.totalVotosSim else vt.totalVotosSim
        this.totalVotosNao = if (vt.totalVotosNao == null) this.totalVotosNao else vt.totalVotosNao

        this.totalVotosAbstencao = if (vt.totalVotosAbstencao == null) this.totalVotosAbstencao else vt.totalVotosAbstencao
        this.totalVotosObstrucao = if (vt.totalVotosObstrucao == null) this.totalVotosObstrucao else vt.totalVotosObstrucao
        this.totalVotosSessao = if (vt.totalVotosSessao == null) this.totalVotosSessao else vt.totalVotosSessao

    }
}
