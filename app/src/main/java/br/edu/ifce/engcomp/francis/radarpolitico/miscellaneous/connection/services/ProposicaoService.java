package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.CDUrlFormatter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.ProposicaoParser;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao;

/**
 * Created by francisco on 16/03/16.
 */
public class ProposicaoService {
    public static Proposicao obterProposicaoPorID(String idProposicao){
        Proposicao proposicao = new Proposicao();

        try{

            URL url = new URL(CDUrlFormatter.obterProposicao(idProposicao));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream stream = connection.getInputStream();
                proposicao = ProposicaoParser.parserProposicaoFromXML(stream);

                stream.close();
                connection.disconnect();

            }


        }catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        return proposicao;
    }
}
