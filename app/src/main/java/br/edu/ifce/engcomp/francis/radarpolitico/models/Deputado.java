package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by francisco on 11/03/16.
 */
public class Deputado {
    private String nome;
    private String nomeParlamentar;
    private String urlFoto;
    private String partido;
    private String uf;
    private String gabinete;
    private String anexo;
    private String fone;
    private String email;
    private String matricula;
    private int camaraId;

    public Deputado(String nome, String nomeParlamentar, String urlFoto, String partido, String uf, String gabinete, String anexo, String fone, String email, String matricula, int camaraId) {
        this.nome = nome;
        this.nomeParlamentar = nomeParlamentar;
        this.urlFoto = urlFoto;
        this.partido = partido;
        this.uf = uf;
        this.gabinete = gabinete;
        this.anexo = anexo;
        this.fone = fone;
        this.email = email;
        this.matricula = matricula;
        this.camaraId = camaraId;
    }

    public Deputado(String nome, String urlFoto, String partido, String uf) {
        this.nome = nome;
        this.urlFoto = urlFoto;
        this.partido = partido;
        this.uf = uf;
    }

    public Deputado(String nome, String partido, String uf){
        this.nome = nome;
        this.partido = partido;
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeParlamentar() {
        return nomeParlamentar;
    }

    public void setNomeParlamentar(String nomeParlamentar) {
        this.nomeParlamentar = nomeParlamentar;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getGabinete() {
        return gabinete;
    }

    public void setGabinete(String gabinete) {
        this.gabinete = gabinete;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCamaraId() {
        return camaraId;
    }

    public void setCamaraId(int camaraId) {
        this.camaraId = camaraId;
    }
}
