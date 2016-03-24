package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by francisco on 24/03/16.
 */
public class Dia {
    String data;
    String frequencia;
    int numeroSessoes;

    public Dia() {
    }

    public String getData() {
        return data;
    }

    public int getNumeroSessoes() {
        return numeroSessoes;
    }

    public void setNumeroSessoes(int numeroSessoes) {
        this.numeroSessoes = numeroSessoes;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
}
