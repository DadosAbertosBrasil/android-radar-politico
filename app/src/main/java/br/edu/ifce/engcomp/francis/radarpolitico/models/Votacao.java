package br.edu.ifce.engcomp.francis.radarpolitico.models;

import java.util.ArrayList;

/**
 * Created by francisco on 12/03/16.
 */
public class Votacao {
    private String resumo;
    private String dataVotacao;

    private ArrayList<Voto> votos;

    public Votacao() {

    }

    public Votacao(String resumo, ArrayList<Voto> votos, String dataVotacao) {
        this.resumo = resumo;
        this.votos = votos;
        this.dataVotacao = dataVotacao;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public ArrayList<Voto> getVotos() {
        return votos;
    }

    public void setVotos(ArrayList<Voto> votos) {
        this.votos = votos;
    }

    public String getDataVotacao() {
        return dataVotacao;
    }

    public void setDataVotacao(String dataVotacao) {
        this.dataVotacao = dataVotacao;
    }

    @Override
    public String toString() {
        return "Votacao{" +
                "resumo='" + resumo + '\'' +
                ", dataVotacao='" + dataVotacao + '\'' +
                '}';
    }
}
