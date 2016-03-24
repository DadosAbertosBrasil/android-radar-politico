package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by Joamila on 24/03/2016.
 */
public class VotacaoDeputado {
    private String idDeputado;
    private String codSessao;
    private String data;
    private String voto;

    public VotacaoDeputado(String codSessao, String data, String voto){
        this.codSessao = codSessao;
        this.data = data;
        this.voto = voto;
    }

    public String getIdDeputado() {
        return idDeputado;
    }

    public void setIdDeputado(String idDeputado) {
        this.idDeputado = idDeputado;
    }

    public String getCodSessao() {
        return codSessao;
    }

    public void setCodSessao(String codSessao) {
        this.codSessao = codSessao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

}
