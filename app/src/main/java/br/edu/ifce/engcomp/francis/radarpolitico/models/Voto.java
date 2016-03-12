package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by francisco on 12/03/16.
 */
public class Voto {
    private String value;
    private Deputado deputado;

    public Voto() {

    }

    public Voto(String value, Deputado deputado) {
        this.value = value;
        this.deputado = deputado;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Deputado getDeputado() {
        return deputado;
    }

    public void setDeputado(Deputado deputado) {
        this.deputado = deputado;
    }
}
