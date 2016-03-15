package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by francisco on 15/03/16.
 */
public class DeputadoParser {
    public static ArrayList<Deputado> parseDeputadosFromXML(InputStream xmlInputStream) throws XmlPullParserException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();
        ArrayList<Deputado> deputados = new ArrayList<>();
        int xmlEvent;

        parser.setInput(xmlInputStream, null);

        while((xmlEvent = parser.getEventType()) != XmlPullParser.END_DOCUMENT) {
            String eventName = parser.getName();

            if(xmlEvent == XmlPullParser.END_TAG){
                if (eventName.equals("deputado")) {
                    Deputado deputado = new Deputado();

                    deputado.setIdCadastro(parser.getAttributeValue(null, "ideCadastro"));
                    deputado.setCondicao(parser.getAttributeValue(null, "condicao"));
                    deputado.setMatricula(parser.getAttributeValue(null, "matricula"));
                    deputado.setIdParlamentar(parser.getAttributeValue(null, "idParlamentar"));
                    deputado.setNome(parser.getAttributeValue(null, "nome"));
                    deputado.setNomeParlamentar(parser.getAttributeValue(null, "nomeParlamentar"));
                    deputado.setUrlFoto(parser.getAttributeValue(null, "urlFoto"));
                    deputado.setUf(parser.getAttributeValue(null, "uf"));
                    deputado.setPartido(parser.getAttributeValue(null, "partido"));
                    deputado.setGabinete(parser.getAttributeValue(null, "gabinete"));
                    deputado.setAnexo(parser.getAttributeValue(null, "anexo"));
                    deputado.setFone(parser.getAttributeValue(null, "fone"));
                    deputado.setEmail(parser.getAttributeValue(null, "email"));

                    Log.i("XML PARSER", deputado.toString());

                    deputados.add(deputado);
                }
            }
        }

        return deputados;
    }
}
