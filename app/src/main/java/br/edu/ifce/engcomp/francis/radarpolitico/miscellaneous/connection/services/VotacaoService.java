package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.CDUrlFormatter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;

/**
 * Created by francisco on 17/03/16.
 */
public class VotacaoService {
    public static Votacao obterVotacaoDeProposicao(String sigla, String proposicaoNumero, String ano){
        Votacao votacao = null;

        try{

            URL url = new URL(CDUrlFormatter.obterVotacaoProposicao(sigla, proposicaoNumero, ano));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream stream = connection.getInputStream();

                votacao = VotacaoParser.parseVotacaoFromXML(stream);

                stream.close();
                connection.disconnect();
            }

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        return votacao;
    }
}
