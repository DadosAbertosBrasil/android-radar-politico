package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.CDUrlFormatter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.DeputadosParser;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by francisco on 15/03/16.
 */
public class DeputadoService {

    public static ArrayList<Deputado> listAllDeputados(){
        ArrayList<Deputado> deputados = new ArrayList<>();

        try {

            URL url = new URL(CDUrlFormatter.obterDeputados());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                deputados = DeputadosParser.parseDeputadosFromXML(stream);

                stream.close();
                connection.disconnect();
            }

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        Log.i("DEPUTADOS", String.valueOf(deputados.size()));

        return deputados;
    }
}
