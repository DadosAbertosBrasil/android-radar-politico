package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous;

/**
 * Created by francisco on 19/04/16.
 */
public class CDUrlFormatter {

//    Base Urls

    private static final String BASE_URL = "http://www.camara.gov.br/SitCamaraWS/";

    private static final String DEPUTADOS_BASE_URL   = BASE_URL + "Deputados.asmx/";
    private static final String ORGAOS_BASE_URL      = BASE_URL + "Orgaos.asmx/";
    private static final String PROPOSICOES_BASE_URL = BASE_URL + "Proposicoes.asmx/";
    private static final String SESSOES_BASE_URL     = BASE_URL + "SessoesReunioes.asmx/";

//    Formatadores de url referentes a Deputados da Câmara

    public static String obterDeputados() {
        return DEPUTADOS_BASE_URL +
                "ObterDeputados";
    }

    public static String obterDeputado(String idDeputado, String numeroLegislatura) {
        return DEPUTADOS_BASE_URL +
                String.format("ObterDetalhesDeputado?ideCadastro=%s&numLegislatura=%s", idDeputado, numeroLegislatura);
    }

    public static String obterLideresBancadas() {
        return DEPUTADOS_BASE_URL +
                "ObterLideresBancadas";
    }

    public static String obterPartidosCD() {
        return DEPUTADOS_BASE_URL +
                "ObterPartidosCD";
    }

    public static String obterPartidosBlocoCD(String idBloco, String numeroLegislatura) {
        return DEPUTADOS_BASE_URL +
                String.format("ObterPartidosBlocoCD?numLegislatura=%s&idBloco=%s", idBloco, numeroLegislatura);
    }

//    Formatadores de url relacioandas aos órgãos do legislativo e seus processos

    public static String listarOrgaosLegislativo() {
        return ORGAOS_BASE_URL +
                "ListarCargosOrgaosLegislativosCD";
    }

    public static String listarTiposOrgao() {
        return ORGAOS_BASE_URL +
                "ListarTiposOrgaos";
    }

    public static String obterAndamento(String sigla, String numeroDoAno, String dataInicio, String codigoOrgao) {
        return ORGAOS_BASE_URL +
                String.format("ObterAndamento?sigla=%s&numero=%s&ano=%s&dataIni=%s&codOrgao=%s", sigla, numeroDoAno, dataInicio, codigoOrgao);
    }

    public static String obterEmendasSubstitutivoRedacaoFinal(String tipo, String numero, String ano) {
        return ORGAOS_BASE_URL +
                String.format("ObterEmendasSubstitutivoRedacaoFinal?tipo=%s&numero=%s&ano=%s", tipo, numero, ano);
    }

    public static String obterIntegraComissoesRelatorPorTipo(String tipo, String numero, String ano) {
        return ORGAOS_BASE_URL +
                String.format("ObterIntegraComissoesRelator?tipo=%s&numero=%s&ano=%s", tipo, numero, ano);
    }

    public static String obterMembrosOrgaoPorID(String idOrgao){
        return ORGAOS_BASE_URL +
                String.format("ObterMembrosOrgao?IDOrgao=%s", idOrgao);
    }

    public static String obterOrgaos() {
        return ORGAOS_BASE_URL +
                "ObterOrgaos";
    }

    public static String obterPauta(String idOrgao, String dataInicio, String dataFim) {
        return ORGAOS_BASE_URL +
                String.format("ObterPauta?IDOrgao=%s&datIni=%s&datFim=%s", idOrgao, dataInicio, dataFim);
    }

    public static String obterRegimeTramitacaoDeDespacho(String tipo, String numero, String ano) {
        return ORGAOS_BASE_URL +
                String.format("ObterRegimeTramitacaoDespacho?tipo=%s&numero=%s&ano=%s", tipo, numero, ano);
    }

//    Formatadores de Url Referentes a Proposicoes que tramitam na Camara dos Deputados

    public static String listarProposicoes(String sigla, String numero, String ano, String dataApresentacaoInicial, String dataApresentacaoFinal, String idTipoAutor, String parteNomeAutor,
                                           String siglaPartidoAutor, String siglaUFAutor, String generoAutor, String idSituacaoProposicao, String idOrgaoProposicao, String tramitacao){

        return PROPOSICOES_BASE_URL +
                String.format("ListarProposicoes?sigla=%s&numero=%s&ano=%s&datApresentacaoIni=%s&datApresentacaoFim=%s&parteNomeAutor=%s" +
                                "&idTipoAutor=%s&siglaPartidoAutor=%s&siglaUFAutor=%s&generoAutor=%s&codEstado=%s&codOrgaoEstado=%s&emTramitacao=%s",
                        sigla, numero, ano, dataApresentacaoInicial, dataApresentacaoFinal, idTipoAutor, parteNomeAutor, siglaPartidoAutor, siglaUFAutor,
                        generoAutor, idSituacaoProposicao, idOrgaoProposicao, tramitacao);
    }

    public static String listarSiglasTipoProposicao() {
        return PROPOSICOES_BASE_URL +
                "ListarSiglasTipoProposicao";
    }

    public static String listarSituacoesPreposicao() {
        return PROPOSICOES_BASE_URL +
                "ListarSituacoesProposicao";
    }

    public static String listarTiposAutores() {
        return PROPOSICOES_BASE_URL +
                "ListarTiposAutores";
    }

    public static String obterProposicao(String tipo, String numero, String ano) {
        return PROPOSICOES_BASE_URL +
                String.format("ObterProposicao?tipo=%s&numero=%s&ano=%s", tipo, numero, ano);
    }

    public static String obterProposicao(String idProposicao) {
        return PROPOSICOES_BASE_URL +
                String.format("ObterProposicaoPorID?IdProp=%s", idProposicao);
    }

    public static String obterVotacaoProposicao(String tipo, String numero, String ano) {
        return PROPOSICOES_BASE_URL +
                String.format("ObterVotacaoProposicao?tipo=%s&numero=%s&ano=%s", tipo, numero, ano);
    }

    public static String listarProposicoesVotadasEmPlenario(String ano, String tipo) {
        return PROPOSICOES_BASE_URL +
                String.format("ListarProposicoesVotadasEmPlenario?ano=%s&tipo=%s", ano, tipo);
    }

    public static String listarProposicoesTramitadasNoPeriodo(String dataInicio, String dataFim) {
        return PROPOSICOES_BASE_URL +
                String.format("ListarProposicoesTramitadasNoPeriodo?dtInicio=%s&dtFim=%s", dataInicio, dataFim);
    }

//    Formatadores de url relacioandos à sessões e reuniões

    public static String listarDiscursosPlenario(String dataInicio, String dataFim, String codigoSessao, String parteNomeParlamentar, String siglaPartido, String siglaUF) {
        return SESSOES_BASE_URL +
                String.format("ListarDiscursosPlenario?dataIni=%s&dataFim=%s&codigoSessao=%s&parteNomeParlamentar=%s&siglaPartido=%s&siglaUF=%s",
                        dataInicio, dataFim, codigoSessao, parteNomeParlamentar, siglaPartido, siglaUF);
    }

    public static String listarPresencasDoDia(String data, String numeroMatriculaParlamentar, String siglaPartido, String siglaUF) {
        return SESSOES_BASE_URL +
                String.format("ListarPresencasDia?data=%s&numLegislatura=%s&numMatriculaParlamentar=%s&siglaPartido=%s&siglaUF=%s", data, numeroMatriculaParlamentar, siglaPartido, siglaUF);
    }

    public static String listarPresencasParlamentar(String dataInicio, String dataFim, String numeroMatriculaParlamentar){
        return SESSOES_BASE_URL +
                String.format("ListarPresencasParlamentar?dataIni=%s&dataFim=%s&numMatriculaParlamentar=%s", dataInicio, dataFim, numeroMatriculaParlamentar);
    }

    public static String listarSituacoesProposicaoReuniaoSessao() {
        return SESSOES_BASE_URL +
                "ListarSituacoesReuniaoSessao";
    }

    public static String obterInteiroTeorDiscursosPlenario(String codigoSessao, String numeroOrador, String numeroQuarto, String numeroInsercao) {
        return SESSOES_BASE_URL +
                String.format("obterInteiroTeorDiscursosPlenario?codSessao=%s&numOrador=%s&numQuarto=%s&numInsercao=%s", codigoSessao, numeroOrador, numeroQuarto, numeroInsercao);
    }


}
