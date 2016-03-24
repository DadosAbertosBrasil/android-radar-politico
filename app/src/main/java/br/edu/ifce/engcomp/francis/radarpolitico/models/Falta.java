package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by Joamila on 24/03/2016.
 */
public class Falta {
    private String idDeputado;
    private String data;
    private String status;

    public Falta(String data, String status){
        this.data = data;
        this.status = status;
    }

    public String getIdDeputado() {
        return idDeputado;
    }

    public void setIdDeputado(String idDeputado) {
        this.idDeputado = idDeputado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
