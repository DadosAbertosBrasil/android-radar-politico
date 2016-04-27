package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * Created by francisco on 27/04/16.
 */
object DeputadoParser {

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseDeputadoFromXML(xmlInputStream: InputStream): Deputado {
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val parser = xmlPullParserFactory.newPullParser()

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(xmlInputStream, null)

        var event = parser.eventType
        var tagAtual: String? = null

        val deputados = ArrayList<Deputado>()
        var deputado: Deputado? = null

        while (event != XmlPullParser.END_DOCUMENT) {
            when (event) {
                XmlPullParser.START_TAG -> {

                    tagAtual = parser.name

                    if (tagAtual == "Deputado") {
                        deputado = Deputado()
                    }
                }

                XmlPullParser.TEXT -> if (!parser.text.contains("\n") && deputado != null) {
                    when (tagAtual) {

                        "email" -> deputado.email = parser.text

                        "ufRepresentacaoAtual" -> deputado.uf = parser.text

                        "sigla" -> deputado.partido = parser.text

                        "nomeParlamentarAtual" -> deputado.nomeParlamentar = parser.text

                        "ideCadastro" -> deputado.idCadastro = parser.text

                        "numLegislatura" -> {
                            val legislatura = parser.text
                            deputado.numeroLegislatura = Integer.valueOf(legislatura)
                        }
                    }
                }

                XmlPullParser.END_TAG -> {

                    tagAtual = parser.name
                    if (tagAtual == "Deputado") {
                        if (deputado != null) {
                            deputados.add(deputado)
                            deputado = null
                        }
                    }
                }
            }

            event = parser.next()
        }

        Collections.sort(deputados, Comparator<br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado> { lhs, rhs ->
            if (lhs.numeroLegislatura > rhs.numeroLegislatura) {
                return@Comparator 1
            }

            if (lhs.numeroLegislatura < rhs.numeroLegislatura) {
                return@Comparator -1
            }

            0
        })

        return deputados[0]
    }
}
