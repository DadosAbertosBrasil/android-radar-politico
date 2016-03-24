package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.services;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.CDUrlFormatter;
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.FrequenciaParser;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Dia;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Frequencia;

/**
 * Created by francisco on 24/03/16.
 */
public class FrequenciaMensalService {

    public static Frequencia obterFrequenciaParaDeputado(String matricula) {
        Frequencia frequencia = new Frequencia();
        frequencia.setDataInicial(getInitialDateForCurrentMonth());
        frequencia.setDataFinal(getLastDateForTheCurrentMonth());

        try{
            URL url = new URL(CDUrlFormatter.listarPresencasParlamentar(frequencia.getDataInicial(), frequencia.getDataFinal(), matricula));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream stream = connection.getInputStream();
                ArrayList<Dia> dias = FrequenciaParser.parseFrequenciaFromXML(stream);

                frequencia.setDias(dias);

                stream.close();
                connection.disconnect();
            }
        }
        catch (IOException | XmlPullParserException e){
            e.printStackTrace();
        }

        return frequencia;
    }

    private static String getInitialDateForCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear  = calendar.get(Calendar.YEAR);

        calendar.set(currentYear, currentMonth, 1);

        Date initialDate = calendar.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.format(initialDate);
    }

    private static String getLastDateForTheCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear  = calendar.get(Calendar.YEAR);

        calendar.set(currentYear, currentMonth, calendar.getActualMaximum(Calendar.DATE));

        Date finalDate = calendar.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.format(finalDate);
    }
}
