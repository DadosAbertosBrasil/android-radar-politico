package br.edu.ifce.engcomp.francis.radarpolitico.models

import java.util.ArrayList

import br.edu.ifce.engcomp.francis.radarpolitico.models.Dia

/**
 * Created by francisco on 24/03/16.
 */
class Frequencia {
    var dataInicial: String? = null
    var dataFinal: String? = null

    var dias: ArrayList<Dia>? = null

    init {
        this.dias = ArrayList<Dia>()
    }

    val percentualPresenca: Double
        get() {
            var numeroDePresencas = 0

            for (d in dias!!) {

                if (d.frequencia == "Presença") {
                    numeroDePresencas += 1
                }
            }

            return (100 * numeroDePresencas / dias!!.size).toDouble()
        }

    val numeroSessoes: Int
        get() {
            var numeroSessoes = 0

            for (d in dias!!) {
                numeroSessoes += d.numeroSessoes
            }

            return numeroSessoes
        }
}
