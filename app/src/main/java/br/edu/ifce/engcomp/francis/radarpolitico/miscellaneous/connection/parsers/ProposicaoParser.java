package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;

/**
 * Created by francisco on 16/03/16.
 */
public class ProposicaoParser {

    public static Proposicao parserProposicaoFromXML(InputStream xmlInputStream) throws XmlPullParserException, IOException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(xmlInputStream, null);

        Proposicao proposicao = new Proposicao();

        int xmlEvent = parser.getEventType();
        String tagAtual = "";

        while(xmlEvent != XmlPullParser.END_DOCUMENT){

            switch (xmlEvent){
                case XmlPullParser.START_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("proposicao")) {
                        proposicao.setSigla(parser.getAttributeValue(null, "tipo"));
                        proposicao.setNumero(parser.getAttributeValue(null, "numero"));
                        proposicao.setAno(parser.getAttributeValue(null, "ano"));
                    }

                    break;

                case XmlPullParser.TEXT:

                    if (!parser.getText().contains("\n")) {
                        switch (tagAtual){
                            case "nomeProposicao":
                                proposicao.setNome(parser.getText());
                                break;

                            case "idProposicao":
                                proposicao.setId(parser.getText());
                                break;

                            case "tipoProposicao":
                                proposicao.setTipoProposicao(parser.getText());
                                break;

                            case "tema":
                                proposicao.setTema(parser.getText());
                                break;

                            case "Ementa":
                                proposicao.setEmenta(parser.getText());
                                break;

                            case "Autor":
                                proposicao.setNomeAutor(parser.getText());
                                break;

                            case "ideCadastro":
                                proposicao.setIdAutor(parser.getText());
                                break;

                            case "RegimeTramitacao":
                                proposicao.setRegimeTramitacao(parser.getText());
                                break;

                            case "Situacao":
                                proposicao.setSituacao(parser.getText());
                                break;

                            case "LinkInteiroTeor":
                                proposicao.setUrlInteiroTeor(parser.getText());
                                break;
                        }
                    }

                    break;

                case XmlPullParser.END_TAG:
                    break;
            }

            xmlEvent = parser.next();
        }

        return proposicao;
    }
}
