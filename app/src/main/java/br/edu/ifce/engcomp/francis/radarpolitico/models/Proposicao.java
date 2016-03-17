package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by francisco on 14/03/16.
 */
public class Proposicao {
    private String id;
    private String nome;

    private String sigla;
    private String numero;
    private String ano;

    private String tipoProposicao;
    private String tema;
    private String ementa;
    private String explicacaoEmenta;
    private String indexacao;

    private String regimeTramitacao;
    private String situacao;

    private String urlInteiroTeor;

    private String quantidadeAutores;

    private String idAutor;
    private String nomeAutor;

    public Proposicao() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTipoProposicao() {
        return tipoProposicao;
    }

    public void setTipoProposicao(String tipoProposicao) {
        this.tipoProposicao = tipoProposicao;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public String getExplicacaoEmenta() {
        return explicacaoEmenta;
    }

    public void setExplicacaoEmenta(String explicacaoEmenta) {
        this.explicacaoEmenta = explicacaoEmenta;
    }

    public String getIndexacao() {
        return indexacao;
    }

    public void setIndexacao(String indexacao) {
        this.indexacao = indexacao;
    }

    public String getRegimeTramitacao() {
        return regimeTramitacao;
    }

    public void setRegimeTramitacao(String regimeTramitacao) {
        this.regimeTramitacao = regimeTramitacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getUrlInteiroTeor() {
        return urlInteiroTeor;
    }

    public void setUrlInteiroTeor(String urlInteiroTeor) {
        this.urlInteiroTeor = urlInteiroTeor;
    }

    public String getQuantidadeAutores() {
        return quantidadeAutores;
    }

    public void setQuantidadeAutores(String quantidadeAutores) {
        this.quantidadeAutores = quantidadeAutores;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
}
