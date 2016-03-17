package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by francisco on 15/03/16.
 */
public class DeputadosParser {
    public static ArrayList<Deputado> parseDeputadosFromXML(InputStream xmlInputStream) throws IOException, XmlPullParserException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();

        ArrayList<Deputado> deputados = new ArrayList<>();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(xmlInputStream, null);

        int event = parser.getEventType();
        Deputado deputadoAtual = null;
        String tagAtual = null;

        while (event != XmlPullParser.END_DOCUMENT) {

            switch (event) {
                case XmlPullParser.START_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("deputado")) {
                        deputadoAtual = new Deputado();
                    }

                    break;

                case XmlPullParser.TEXT:

                    if (!parser.getText().contains("\n") && deputadoAtual != null) {
                        switch (tagAtual) {
                            case "ideCadastro":
                                deputadoAtual.setIdCadastro(parser.getText());
                                break;
                            case "condicao":
                                deputadoAtual.setCondicao(parser.getText());
                                break;
                            case "matricula":
                                deputadoAtual.setMatricula(parser.getText());
                                break;
                            case "idParlamentar":
                                deputadoAtual.setIdParlamentar(parser.getText());
                                break;
                            case "nome":
                                deputadoAtual.setNome(parser.getText());
                                break;
                            case "nomeParlamentar":
                                deputadoAtual.setNomeParlamentar(parser.getText());
                                break;
                            case "urlFoto":
                                deputadoAtual.setUrlFoto(parser.getText());
                                break;
                            case "uf":
                                deputadoAtual.setUf(parser.getText());
                                break;
                            case "partido":
                                deputadoAtual.setPartido(parser.getText());
                                break;
                            case "gabinete":
                                deputadoAtual.setPartido(parser.getText());
                                break;
                            case "anexo":
                                deputadoAtual.setAnexo(parser.getText());
                                break;
                            case "fone":
                                deputadoAtual.setFone(parser.getText());
                                break;
                            case "email":
                                deputadoAtual.setEmail(parser.getText());
                                break;
                        }
                    }

                    break;

                case XmlPullParser.END_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("deputado") && deputadoAtual != null) {
                        deputados.add(deputadoAtual);
                        deputadoAtual = null;
                    }

                    break;
            }

            event = parser.next();
        }

        return deputados;
    }
}