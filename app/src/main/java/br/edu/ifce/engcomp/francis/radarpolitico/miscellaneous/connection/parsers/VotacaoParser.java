package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

/**
 * Created by francisco on 17/03/16.
 */
public class VotacaoParser {

    public static Votacao parseVotacaoFromXML(InputStream xmlInputStream) throws XmlPullParserException, IOException {

        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(xmlInputStream, null);

        ArrayList<Votacao> votacoes = new ArrayList<>();
        Votacao votacao = null;
        Voto voto = null;

        int xmlEvent = parser.getEventType();
        String tagAtual = "";

        while(xmlEvent != XmlPullParser.END_DOCUMENT){

            switch (xmlEvent){
                case XmlPullParser.START_TAG:
                    tagAtual = parser.getName();

                    if(tagAtual.equals("Votacao")){
                        votacao = new Votacao();

                        votacao.setResumo(parser.getAttributeValue(null, "Resumo"));

                        Log.i("PARSER", votacao.getResumo());

                        String totalVotosSim = StringUtils.substringBetween(votacao.getResumo(), "Sim:", ";");
                        String totalVotosNao = StringUtils.substringBetween(votacao.getResumo(), "não:", ";");
                        String totalVotosAbstencoes = StringUtils.substringBetween(votacao.getResumo(), "abstenções:", ";");

                        if(totalVotosAbstencoes == null){
                            totalVotosAbstencoes = StringUtils.substringBetween(votacao.getResumo(), "abstenção:", ";");
                        }

                        String totalVotos = StringUtils.substringBetween(votacao.getResumo(), "total: ", ".");

                        if (totalVotosSim != null) {
                            votacao.setTotalVotosSim(totalVotosSim);
                        }
                        else {
                            votacao.setTotalVotosSessao("0");
                        }

                        if (totalVotosNao != null) {
                            votacao.setTotalVotosNao(totalVotosNao);
                        }
                        else {
                            votacao.setTotalVotosNao("0");
                        }

                        if (totalVotosAbstencoes != null) {
                            votacao.setTotalVotosAbstencao(totalVotosAbstencoes);
                        }
                        else {
                            votacao.setTotalVotosAbstencao("0");
                        }

                        if (totalVotos != null) {
                            votacao.setTotalVotosSessao(totalVotos);
                        }
                        else {
                            votacao.setTotalVotosSessao("0");
                        }

                        votacao.setData(parser.getAttributeValue(null, "Data").replaceAll("\\s", ""));
                        votacao.setHora(parser.getAttributeValue(null, "Hora").replaceAll("\\s", ""));
                        votacao.setObjetoVotacao(parser.getAttributeValue(null, "ObjVotacao"));
                        votacao.setCodigoSessao(parser.getAttributeValue(null, "codSessao"));
                    }
                    else if (tagAtual.equals("Deputado")){
                        voto = new Voto();

                        voto.setNome(parser.getAttributeValue(null, "Nome"));
                        voto.setIdCadastro(parser.getAttributeValue(null, "ideCadastro").replaceAll("\\s", ""));
                        voto.setPartido(parser.getAttributeValue(null, "Partido").replaceAll("\\s", ""));
                        voto.setUf(parser.getAttributeValue(null, "UF").replaceAll("\\s", ""));
                        voto.setVoto(parser.getAttributeValue(null, "Voto").replaceAll("\\s", ""));
                    }

                    break;

                case XmlPullParser.TEXT:

                    break;

                case XmlPullParser.END_TAG:
                    tagAtual = parser.getName();

                    if(tagAtual.equals("Votacao")){
                        votacoes.add(votacao);
                        votacao = null;
                    }
                    else if(votacao != null && tagAtual.equals("Deputado")){
                        votacao.getVotos().add(voto);
                        voto = null;
                    }

                    break;
            }

            xmlEvent = parser.next();

        }

        return votacoes.get(votacoes.size() - 1);
    }

}