package br.edu.ifce.engcomp.francis.radarpolitico.models

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado

/**
 * Created by Joamila on 24/03/2016.
 */
data class Falta(var data: String = "", var status: String = ""){
    var idDeputado: String? = null
}

