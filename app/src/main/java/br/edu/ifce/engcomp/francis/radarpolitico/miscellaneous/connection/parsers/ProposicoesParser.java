package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;

/**
 * Created by francisco on 16/03/16.
 */
public class ProposicoesParser {

    public static ArrayList<Proposicao> parseProposicoesFromXML(InputStream xmlInputStream)  throws XmlPullParserException, IOException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Proposicao> proposicoes = new ArrayList<>();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(xmlInputStream, null);

        int xmlEvent = parser.getEventType();
        Proposicao proposicao = new Proposicao();
        String tagAtual = "";

        while(xmlEvent != XmlPullParser.END_DOCUMENT){

            switch (xmlEvent){
                case XmlPullParser.START_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("proposicao")){
                        proposicao = new Proposicao();
                    }

                    break;

                case XmlPullParser.TEXT:
                    if (!parser.getText().contains("\n")){
                        if (tagAtual.equals("codProposicao")){
                            proposicao.setId(parser.getText());
                        }
                        else if (tagAtual.equals("dataVotacao")){
                            try {
                                proposicao.setDataVotacao(formatter.parse(parser.getText()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    break;

                case XmlPullParser.END_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("proposicao")){
                        proposicoes.add(proposicao);
                        proposicao = null;
                    }

                    break;
            }

            xmlEvent = parser.next();
        }

        return proposicoes;
    }
}
