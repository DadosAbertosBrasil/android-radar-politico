package br.edu.ifce.engcomp.francis.radarpolitico.models;

/**
 * Created by francisco on 12/03/16.
 */
public class Votacao {
    private String resumo;

    private String data;
    private String hora;

    private String objetoVotacao;
    private String codigoSessao;

    private int totalVotosSim;
    private int totalVotosNao;
    private int totalVotosAbstencao;
    private int totalVotosObstrucao;
    private int totalVotosSessao;


    public Votacao() {

    }

    public Votacao(String resumo, String data, String hora, String objetoVotacao, String codigoSessao,
                   int totalVotosSim, int totalVotosNao, int totalVotosAbstencao, int totalVotosObstrucao, int totalVotosSessao) {

        this.resumo = resumo;
        this.data = data;
        this.hora = hora;
        this.objetoVotacao = objetoVotacao;
        this.codigoSessao = codigoSessao;
        this.totalVotosSim = totalVotosSim;
        this.totalVotosNao = totalVotosNao;
        this.totalVotosAbstencao = totalVotosAbstencao;
        this.totalVotosObstrucao = totalVotosObstrucao;
        this.totalVotosSessao = totalVotosSessao;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObjetoVotacao() {
        return objetoVotacao;
    }

    public void setObjetoVotacao(String objetoVotacao) {
        this.objetoVotacao = objetoVotacao;
    }

    public String getCodigoSessao() {
        return codigoSessao;
    }

    public void setCodigoSessao(String codigoSessao) {
        this.codigoSessao = codigoSessao;
    }

    public int getTotalVotosSim() {
        return totalVotosSim;
    }

    public void setTotalVotosSim(int totalVotosSim) {
        this.totalVotosSim = totalVotosSim;
    }

    public int getTotalVotosNao() {
        return totalVotosNao;
    }

    public void setTotalVotosNao(int totalVotosNao) {
        this.totalVotosNao = totalVotosNao;
    }

    public int getTotalVotosAbstencao() {
        return totalVotosAbstencao;
    }

    public void setTotalVotosAbstencao(int totalVotosAbstencao) {
        this.totalVotosAbstencao = totalVotosAbstencao;
    }

    public int getTotalVotosObstrucao() {
        return totalVotosObstrucao;
    }

    public void setTotalVotosObstrucao(int totalVotosObstrucao) {
        this.totalVotosObstrucao = totalVotosObstrucao;
    }

    public int getTotalVotosSessao() {
        return totalVotosSessao;
    }

    public void setTotalVotosSessao(int totalVotosSessao) {
        this.totalVotosSessao = totalVotosSessao;
    }
}
