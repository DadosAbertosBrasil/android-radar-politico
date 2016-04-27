package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers

import android.util.Log
import br.edu.ifce.engcomp.francis.radarpolitico.models.*
import org.apache.commons.lang3.StringUtils
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Created by francisco on 27/04/16.
 */
object CDXmlParser {
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

                    if (tagAtual.equals("deputado", ignoreCase = true)) {
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

                        "numLegislatura" -> deputado.numeroLegislatura = parser.text.toInt()
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

        deputados.sortByDescending { it.numeroLegislatura }

        return deputados.first()
    }

    fun parseDeputadosFromXML(xmlInputStream: InputStream): ArrayList<Deputado> {
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val parser = xmlPullParserFactory.newPullParser()
        val deputados = ArrayList<Deputado>()

        var event: Int = 0
        var tagAtual: String? = null
        var deputado: Deputado? = null

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(xmlInputStream, null)

        event = parser.eventType

        while(event != XmlPullParser.END_DOCUMENT) {
            when (event) {
                XmlPullParser.START_TAG -> {
                    tagAtual = parser.name

                    if(tagAtual.equals("deputado", ignoreCase = true)) {
                        deputado = Deputado()
                    }
                }

                XmlPullParser.TEXT -> {
                    if (!parser.text.contains("\n") && deputado != null) {

                        when (tagAtual) {
                            "ideCadastro" -> deputado.idCadastro = parser.text

                            "condicao" -> deputado.condicao = parser.text

                            "matricula" -> deputado.matricula = parser.text

                            "idParlamentar" -> deputado.idParlamentar = parser.text

                            "nome" -> deputado.nome = parser.text

                            "nomeParlamentar" -> deputado.nomeParlamentar = parser.text

                            "urlFoto" -> deputado.urlFoto = parser.text

                            "uf" -> deputado.uf = parser.text

                            "partido" -> deputado.partido = parser.text

                            "gabinete" -> deputado.gabinete = parser.text

                            "anexo" -> deputado.anexo = parser.text

                            "fone" -> deputado.fone = parser.text

                            "email" -> deputado.email = parser.text
                        }

                    }
                }

                XmlPullParser.END_TAG -> {
                    tagAtual = parser.name

                    if (tagAtual.equals("deputado", ignoreCase = true) && deputado != null) {
                        deputados.add(deputado)
                        deputado = null
                    }
                }
            }

            event = parser.next()
        }

        //deputados.sortBy { it.nomeParlamentar }

        return deputados

    }

    fun parseFrequenciaFromXML(xmlInputStream: InputStream): ArrayList<Dia> {
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val parser = xmlPullParserFactory.newPullParser()
        val dias = ArrayList<Dia>()

        var event: Int = 0
        var tagAtual: String? = null
        var dia: Dia? = null

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(xmlInputStream, null)

        event = parser.eventType


        while (event != XmlPullParser.END_DOCUMENT) {

            when(event) {
                XmlPullParser.START_TAG -> {
                    tagAtual = parser.name

                    if(tagAtual.equals("dia", ignoreCase = true)) {
                        dia = Dia()
                    }
                }

                XmlPullParser.TEXT -> {
                    if (!parser.text.contains("\n") && dia != null) {

                        when(tagAtual) {
                            "data" -> dia.data = parser.text
                            "frequencianoDia" -> dia.frequencia = parser.text
                        }
                    }
                }

                XmlPullParser.END_TAG -> {
                    tagAtual = parser.name

                    if (tagAtual.equals("dia", ignoreCase = true) && dia != null) {
                        dias.add(dia)
                        dia = null
                    }
                }
            }

            event = parser.next()

        }

        return dias
    }

    fun parseProposicaoFromXML(xmlInputStream: InputStream): Proposicao {
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val parser = xmlPullParserFactory.newPullParser()

        var proposicao = Proposicao()
        var event: Int = 0
        var tagAtual: String? = null

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(xmlInputStream, null)

        event = parser.eventType

        while(event != XmlPullParser.END_DOCUMENT) {

            when (event) {
                XmlPullParser.START_TAG -> {

                    tagAtual = parser.name

                    if (tagAtual.equals("proposicao", ignoreCase = true)) {
                        proposicao.sigla  = parser.getAttributeValue(null, "tipo")?.replace("\\s+".toRegex(), "")
                        proposicao.numero = parser.getAttributeValue(null, "numero")?.replace("\\s+".toRegex(), "")
                        proposicao.ano    = parser.getAttributeValue(null, "ano")?.replace("\\s+".toRegex(), "")
                    }
                }

                XmlPullParser.TEXT -> {
                    if (!parser.text.contains("\n")) {
                        when (tagAtual) {
                            "nomeProposicao" -> proposicao.nome = parser.text

                            "idProposicao" -> proposicao.id = parser.text

                            "tipoProposicao" -> proposicao.tipoProposicao = parser.text

                            "tema" -> proposicao.tema = parser.text

                            "Ementa" -> proposicao.ementa = parser.text

                            "Autor" -> proposicao.nomeAutor = parser.text

                            "ideCadastro" -> proposicao.idAutor = parser.text

                            "RegimeTramitacao" -> proposicao.regimeTramitacao = parser.text

                            "Situacao" -> proposicao.situacao = parser.text

                            "LinkInteiroTeor" -> proposicao.urlInteiroTeor = parser.text
                        }
                    }
                }
            }

            event = parser.next()
        }

        return proposicao
    }

    fun parseProposicoesFromXML(xmlInputStream: InputStream): ArrayList<Proposicao> {
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val parser = xmlPullParserFactory.newPullParser()
        val proposicoes = ArrayList<Proposicao>()
        val formatter = SimpleDateFormat("dd/MM/yyyy")

        var proposicao:Proposicao? = null
        var event: Int = 0
        var tagAtual: String? = null

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(xmlInputStream, null)

        event = parser.eventType


        while(event != XmlPullParser.END_DOCUMENT) {

            when(event) {
                XmlPullParser.START_TAG -> {
                    tagAtual = parser.name

                    if (tagAtual.equals("proposicao", ignoreCase = true)) {
                        proposicao = Proposicao()
                    }
                }

                XmlPullParser.TEXT -> {

                    if (!parser.text.contains("\n")) {

                        when (tagAtual) {
                            "codProposicao" -> {
                                proposicao?.id = parser.text
                            }

                            "dataVotacao" -> {
                                proposicao?.dataVotacao = formatter.parse(parser.text)
                            }
                        }
                    }
                }

                XmlPullParser.END_TAG -> {
                    tagAtual = parser.name

                    if (tagAtual.equals("proposicao", ignoreCase = true) && proposicao != null) {
                        proposicoes.add(proposicao)
                        proposicao = null
                    }
                }
            }

            event = parser.next()
        }

        return proposicoes
    }

    fun parseVotacaoFromXML(xmlInputStream: InputStream): Votacao {
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val parser = xmlPullParserFactory.newPullParser()
        val votacoes = ArrayList<Votacao>()

        var votacao:Votacao? = null
        var voto:Voto? = null

        var event: Int = 0
        var tagAtual: String? = null

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(xmlInputStream, null)

        event = parser.eventType

        while(event != XmlPullParser.END_DOCUMENT){

            when(event){
                XmlPullParser.START_TAG -> {
                    tagAtual = parser.name

                    if (tagAtual.equals("votacao", ignoreCase = true)) {
                        val resumo = parser.getAttributeValue(null, "Resumo")

                        val simPattern = Pattern.compile("[Ss]im:\\s?([0-9]*);")
                        val naoPattern = Pattern.compile("[Nn]ão:\\s?([0-9]*);")
                        val abstencoesPattern = Pattern.compile("[Aa]bstenção:\\s?([0-9]*);")
                        val abstencaoPattern = Pattern.compile("[Aa]bstenções:\\s?([0-9]*);")
                        val totalPattern = Pattern.compile("[Tt]otal:\\s?([0-9]*);?.?")

                        val simMatcher = simPattern.matcher(resumo)
                        val naoMatcher = naoPattern.matcher(resumo)
                        val abstencaoMatcher = abstencaoPattern.matcher(resumo)
                        val abstencoesMatcher = abstencoesPattern.matcher(resumo)
                        val totalMatcher = totalPattern.matcher(resumo)

                        var simVotos = "0"
                        var naoVotos = "0"
                        var abstencaoVotos = "0"
                        var totalVotos = "0"

                        while(simMatcher.find()){
                            simVotos = simMatcher.group(1)
                        }

                        while(naoMatcher.find()){
                            naoVotos = naoMatcher.group(1)
                        }

                        while(abstencaoMatcher.find()){
                           abstencaoVotos = abstencaoMatcher.group(1)
                        }

                        while(abstencoesMatcher.find()){
                            abstencaoVotos = abstencoesMatcher.group(1)
                        }

                        while(totalMatcher.find()){
                            totalVotos = totalMatcher.group(1)
                        }

                        votacao = Votacao()
                        votacao.resumo = resumo
                        votacao.totalVotosSim = simVotos
                        votacao.totalVotosNao = naoVotos
                        votacao.totalVotosAbstencao = abstencaoVotos
                        votacao.totalVotosSessao = totalVotos

                        votacao.data = parser.getAttributeValue(null, "Data")?.replace("\\s".toRegex(), "")
                        votacao.hora = parser.getAttributeValue(null, "Hora")?.replace("\\s".toRegex(), "")

                        votacao.objetoVotacao = parser.getAttributeValue(null, "ObjVotacao")?.replace("\\s".toRegex(), "")
                        votacao.codigoSessao  = parser.getAttributeValue(null, "codSessao")?.replace("\\s".toRegex(), "")

                    }

                    else if (tagAtual.equals("deputado", ignoreCase = true)) {
                        voto = Voto()
                        voto.nome = parser.getAttributeValue(null, "Nome")?.replace("\\s".toRegex(), "")
                        voto.idCadastro = parser.getAttributeValue(null, "ideCadastro")?.replace("\\s".toRegex(), "")
                        voto.partido = parser.getAttributeValue(null, "Partido")?.replace("\\s".toRegex(), "")
                        voto.uf = parser.getAttributeValue(null, "UF")?.replace("\\s".toRegex(), "")
                        voto.voto = parser.getAttributeValue(null, "Voto")?.replace("\\s".toRegex(), "")
                    }
                }

                XmlPullParser.END_TAG -> {
                    tagAtual = parser.name

                    if(tagAtual.equals("votacao", ignoreCase = true)){
                        votacoes.add(votacao!!)
                        votacao = null
                    }
                    else if (votacao != null && tagAtual.equals("deputado", true)){
                        votacao.votos.add(voto!!)
                        voto = null
                    }
                }
            }

            event = parser.next()
        }

        return votacoes.last()
    }
}