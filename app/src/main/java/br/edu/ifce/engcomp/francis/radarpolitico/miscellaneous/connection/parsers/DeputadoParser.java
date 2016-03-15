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
public class DeputadoParser {
    public static ArrayList<Deputado> parseDeputadosFromXML(InputStream xmlInputStream) throws XmlPullParserException, IOException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();

        ArrayList<Deputado> deputados = new ArrayList<>();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(xmlInputStream, null);

        int event = parser.getEventType();
        Deputado deputadoAtual = null;
        String tagAtual = null;

        while (event != XmlPullParser.END_DOCUMENT) {

            switch (event){
                case XmlPullParser.START_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("deputado")){
                        deputadoAtual = new Deputado();
                    }

                    break;

                case XmlPullParser.TEXT:

                    if (!parser.getText().contains("\n")) {
                        if (tagAtual.equals("ideCadastro")){
                            deputadoAtual.setIdCadastro(parser.getText());
                        }
                        else if (tagAtual.equals("condicao")){
                            deputadoAtual.setCondicao(parser.getText());
                        }
                        else if (tagAtual.equals("matricula")){
                            deputadoAtual.setMatricula(parser.getText());
                        }
                        else if (tagAtual.equals("idParlamentar")){
                            deputadoAtual.setIdParlamentar(parser.getText());
                        }
                        else if (tagAtual.equals("nome")){
                            deputadoAtual.setNome(parser.getText());
                        }
                        else if (tagAtual.equals("nomeParlamentar")){
                            deputadoAtual.setNomeParlamentar(parser.getText());
                        }
                        else if (tagAtual.equals("urlFoto")){
                            deputadoAtual.setUrlFoto(parser.getText());
                        }
                        else if (tagAtual.equals("uf")){
                            deputadoAtual.setUf(parser.getText());
                        }
                        else if (tagAtual.equals("partido")){
                            deputadoAtual.setPartido(parser.getText());
                        }
                        else if (tagAtual.equals("gabinete")){
                            deputadoAtual.setPartido(parser.getText());
                        }
                        else if (tagAtual.equals("anexo")){
                            deputadoAtual.setAnexo(parser.getText());
                        }
                        else if (tagAtual.equals("fone")){
                            deputadoAtual.setFone(parser.getText());
                        }
                        else if (tagAtual.equals("email")){
                            deputadoAtual.setEmail(parser.getText());
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
