package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Dia;

/**
 * Created by francisco on 24/03/16.
 */
public class FrequenciaParser {
    public static ArrayList<Dia> parseFrequenciaFromXML(InputStream xmlInputStream) throws IOException, XmlPullParserException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();

        ArrayList<Dia> dias = new ArrayList<>();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(xmlInputStream, null);

        int xmlEvent = parser.getEventType();
        Dia diaAtual = null;
        String tagAtual = "";
        int numeroSessoes = 0;

        while (xmlEvent != XmlPullParser.END_DOCUMENT) {

            switch (xmlEvent) {
                case XmlPullParser.START_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("dia")) {
                        diaAtual = new Dia();
                    }
                    else if (tagAtual.equals("sessao")){
                        numeroSessoes += 1;
                    }

                    break;

                case XmlPullParser.TEXT:

                    if (!parser.getText().contains("\n")) {

                        if (tagAtual.equals("data")) {
                            diaAtual.setData(parser.getText());
                        }
                        else if (tagAtual.equals("frequencianoDia")) {
                            diaAtual.setFrequencia(parser.getText());
                        }
                    }

                    break;

                case XmlPullParser.END_TAG:
                    tagAtual = parser.getName();

                    if (tagAtual.equals("dia")) {
                        diaAtual.setNumeroSessoes(numeroSessoes);
                        dias.add(diaAtual);

                        diaAtual = null;
                        numeroSessoes = 0;
                    }

                    break;
            }

            xmlEvent = parser.next();

        }

        return dias;
    }
}