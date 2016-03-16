package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.CDUrlFormatter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.ProposicoesParser;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;

/**
 * Created by francisco on 16/03/16.
 */
public class ProposicoesService {

    public static ArrayList<Proposicao> loadProposicoesVotadasNoAno(String ano){
        ArrayList<Proposicao> proposicoes = new ArrayList<>();

        try{
            URL url = new URL(CDUrlFormatter.listarProposicoesVotadasEmPlenario(ano, ""));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int response_code = connection.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK){
                InputStream stream = connection.getInputStream();
                proposicoes = ProposicoesParser.parseProposicoesFromXML(stream);
            }

            return proposicoes;

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

}
