package br.edu.ifce.engcomp.francis.radarpolitico.models

import java.io.Serializable

/**
 * Created by francisco on 24/03/16.
 */
data class Dia(
    var data: String? = "",
    var frequencia: String? = "",
    var numeroSessoes: Int = 0
) : Serializable
