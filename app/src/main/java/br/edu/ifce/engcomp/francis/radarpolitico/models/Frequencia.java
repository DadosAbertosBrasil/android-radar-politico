package br.edu.ifce.engcomp.francis.radarpolitico.models;

import java.util.ArrayList;

/**
 * Created by francisco on 24/03/16.
 */
public class Frequencia {
    private String dataInicial;
    private String dataFinal;

    private ArrayList<Dia> dias;

    public Frequencia() {
        this.dias = new ArrayList<>();
    }

    public double getPercentualPresenca(){
        int numeroDePresencas = 0;

        for(Dia d : dias) {

            if(d.getFrequencia().equals("Presença")){
                numeroDePresencas += 1;
            }
        }

        return 100 * numeroDePresencas / dias.size();
    }

    public int getNumeroSessoes(){
        int numeroSessoes = 0;

        for (Dia d : dias){
            numeroSessoes += d.getNumeroSessoes();
        }

        return numeroSessoes;
    }

    public String getDataInicial() {
        return dataInicial;
    }


    public String getDataFinal() {
        return dataFinal;
    }

    public ArrayList<Dia> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dia> dias) {
        this.dias = dias;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}
