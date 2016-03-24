package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by francisco on 11/03/16.
 */
public class Deputado {
    private String idCadastro;
    private String matricula;
    private String idParlamentar;

    private String condicao;
    private String nome;
    private String nomeParlamentar;
    private String urlFoto;

    private String partido;

    private String gabinete;
    private String anexo;

    private String uf;
    private String fone;
    private String email;

    private String dataNascimento;
    private String situacaoLegislaturaAtual;
    private String ufRepresentacaoAtual;
    private String nomeProfissao;

    private Frequencia frequencia;

    public Deputado() {
    }

    public String getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(String idCadastro) {
        this.idCadastro = idCadastro;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getIdParlamentar() {
        return idParlamentar;
    }

    public void setIdParlamentar(String idParlamentar) {
        this.idParlamentar = idParlamentar;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSituacaoLegislaturaAtual() {
        return situacaoLegislaturaAtual;
    }

    public void setSituacaoLegislaturaAtual(String situacaoLegislaturaAtual) {
        this.situacaoLegislaturaAtual = situacaoLegislaturaAtual;
    }

    public String getUfRepresentacaoAtual() {
        return ufRepresentacaoAtual;
    }

    public void setUfRepresentacaoAtual(String ufRepresentacaoAtual) {
        this.ufRepresentacaoAtual = ufRepresentacaoAtual;
    }

    public String getNomeProfissao() {
        return nomeProfissao;
    }

    public void setNomeProfissao(String nomeProfissao) {
        this.nomeProfissao = nomeProfissao;
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return "Deputado{" +
                "idCadastro='" + idCadastro + '\'' +
                ", matricula='" + matricula + '\'' +
                ", idParlamentar='" + idParlamentar + '\'' +
                ", condicao='" + condicao + '\'' +
                ", nome='" + nome + '\'' +
                ", nomeParlamentar='" + nomeParlamentar + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", partido='" + partido + '\'' +
                ", gabinete='" + gabinete + '\'' +
                ", anexo='" + anexo + '\'' +
                ", uf='" + uf + '\'' +
                ", fone='" + fone + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", situacaoLegislaturaAtual='" + situacaoLegislaturaAtual + '\'' +
                ", ufRepresentacaoAtual='" + ufRepresentacaoAtual + '\'' +
                ", nomeProfissao='" + nomeProfissao + '\'' +
                '}';
    }
}
