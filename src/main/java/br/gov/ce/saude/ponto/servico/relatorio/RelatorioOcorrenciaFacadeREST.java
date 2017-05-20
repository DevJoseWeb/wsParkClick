/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico.relatorio;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Ocorrencia;
import br.gov.ce.saude.ponto.vo.PaginacaoVO;
import br.gov.ce.saude.ponto.vo.RelatorioOcorrenciasVO;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author joao
 */
@Stateless
@Path("relatoriocorrencia")
public class RelatorioOcorrenciaFacadeREST extends AbstractFacade<Ocorrencia, ServiceException> {

    public RelatorioOcorrenciaFacadeREST() {
        super(Ocorrencia.class);
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("ocorrencianaojust/{periodo}/{idPessoa}")
    @Produces({"application/json"})
    public PaginacaoVO listaOcorrencias(@PathParam("periodo") String periodo,
            @PathParam("idPessoa") BigInteger idPessoa,
            @QueryParam("idUnidade") BigInteger idUnidade,
            @QueryParam("start") int start, @QueryParam("length") int length,
            @QueryParam("search[value]") String nome, @QueryParam("draw") int draw) {

        PaginacaoVO paginacaoVO = new PaginacaoVO();
        List<RelatorioOcorrenciasVO> data = null;

        String consulta = "SELECT categoria, unidade, cast(atraso as bigint), cast(meia_falta as bigint), cast(falta as bigint), qtd_horas, vlr_pago\n"
                + "FROM (\n"
                + "SELECT organograma_pk, categoria, unidade, SUM(atraso) AS atraso, SUM(meia_falta) AS meia_falta, SUM(falta) AS falta, CAST(SUM(qtd_horas) AS TEXT) AS qtd_horas , to_char(SUM(vlr_pago), 'L9G999G990D99') AS vlr_pago\n"
                + "FROM \n"
                + "(SELECT\n"
                + "  COALESCE(COALESCE(faltas.organograma_pk, meia.organograma_pk), atraso.organograma_pk) AS organograma_pk,\n"
                + "  COALESCE(COALESCE(faltas.categoria, meia.categoria), atraso.categoria) AS categoria,\n"
                + "  COALESCE(COALESCE(faltas.unidade, meia.unidade), atraso.unidade) AS unidade,\n"
                + "  atraso.qtd AS atraso,\n"
                + "  meia.qtd AS meia_falta,\n"
                + "  faltas.qtd AS falta,\n"
                + "  (coalesce(faltas.qtd_horas,INTERVAL'00:00:00')+ coalesce(meia.qtd_horas,INTERVAL'00:00:00')+ coalesce(atraso.qtd_horas,INTERVAL'00:00:00'))  AS qtd_horas,\n"
                + "  \n"
                + "  (COALESCE(faltas.vlr_pago, 0) + COALESCE(meia.vlr_pago, 0) + COALESCE(atraso.vlr_pago, 0)) AS vlr_pago\n"
                + "FROM (SELECT\n"
                + "  falta.organograma_pk,\n"
                + "  falta.unidade,\n"
                + "  falta.categoria,\n"
                + "  COUNT(*) AS qtd,\n"
                + "  SUM(falta.tempo) AS qtd_horas,\n"
                + "  SUM(falta.qtd_horas * t_valor_hora.valor_hora) AS vlr_pago  \n"
                + "FROM e_pontows.v_faltas_cagos_unidade falta LEFT JOIN t_valor_hora ON t_valor_hora.funcionario_contrato_fk = funcionario_contrato_pk AND to_char(t_valor_hora.competencia, 'MM/yyyy') = :periodo\n"
                + "WHERE falta.ocorrencia_pk = 1\n"
                + "AND to_char(falta.data_processamento, 'MM/yyyy') = :periodo\n"
                + "GROUP BY falta.organograma_pk,\n"
                + "         falta.unidade,\n"
                + "         falta.categoria\n"
                + ") faltas\n"
                + "\n"
                + "FULL OUTER JOIN (SELECT\n"
                + "  falta.organograma_pk,\n"
                + "  falta.unidade,\n"
                + "  falta.categoria,\n"
                + "  COUNT(*) AS qtd,\n"
                + "  SUM(falta.tempo) AS qtd_horas,\n"
                + "  SUM(falta.qtd_horas * t_valor_hora.valor_hora) AS vlr_pago\n"
                + "FROM e_pontows.v_faltas_cagos_unidade falta LEFT JOIN t_valor_hora ON t_valor_hora.funcionario_contrato_fk = funcionario_contrato_pk AND to_char(t_valor_hora.competencia, 'MM/yyyy') = :periodo\n"
                + "WHERE falta.ocorrencia_pk = 6\n"
                + "AND to_char(falta.data_processamento, 'MM/yyyy') = :periodo\n"
                + "GROUP BY falta.organograma_pk,\n"
                + "         falta.unidade,\n"
                + "         falta.categoria\n"
                + ") meia\n"
                + "  ON (faltas.unidade = meia.unidade\n"
                + "  AND faltas.categoria = meia.categoria)\n"
                + "  \n"
                + "FULL OUTER JOIN (SELECT\n"
                + "  falta.organograma_pk,\n"
                + "  falta.unidade,\n"
                + "  falta.categoria,\n"
                + "  COUNT(*) AS qtd,\n"
                + "  SUM(falta.tempo) AS qtd_horas,\n"
                + "  SUM(falta.qtd_horas * t_valor_hora.valor_hora) AS vlr_pago\n"
                + "FROM e_pontows.v_faltas_cagos_unidade falta LEFT JOIN t_valor_hora ON t_valor_hora.funcionario_contrato_fk = funcionario_contrato_pk AND to_char(t_valor_hora.competencia, 'MM/yyyy') = :periodo\n"
                + "WHERE falta.ocorrencia_pk = 2\n"
                + "AND to_char(falta.data_processamento, 'MM/yyyy') = :periodo\n"
                + "GROUP BY falta.organograma_pk,\n"
                + "         falta.unidade,\n"
                + "         falta.categoria\n"
                + ") atraso\n"
                + "  ON (faltas.unidade = atraso.unidade\n"
                + "  AND faltas.categoria = atraso.categoria)   \n"
                + ") u\n"
                + "GROUP BY 1,2,3) A\n"
                + "WHERE 1=1 \n" + ((idUnidade != null) ? " AND A.organograma_pk = :idUnidade\n" : "\n");

        // Verifica se o usuario é Geral
        String consultaGlobal = "select count(*) from Hierarquia h \n"
                + " where h.tipo = 'G' and \n"
                + " h.funcionarioContrato.funcionarioFk.id = :pessoa ";

        Query query1 = getEntityManager().createQuery(consultaGlobal);
        query1.setParameter("pessoa", idPessoa);

        Long qtdGlobal = (Long) query1.getSingleResult();

        if (qtdGlobal.intValue() == 0) {
            // Inclui as unidades que o usuario tem acesso
            consulta += "AND A.organograma_pk IN ("
                    + "(SELECT \n"
                    + "  t_unidade_organica_item.organograma_fk\n"
                    + "FROM \n"
                    + "  e_pontows.t_unidade_organica, \n"
                    + "  e_pontows.t_unidade_organica_item, \n"
                    + "  e_pontows.t_hierarquia, \n"
                    + "  e_pontows.t_funcionario_contrato\n"
                    + "WHERE \n"
                    + "  t_unidade_organica.unidade_organica_pk = t_unidade_organica_item.unidade_organica_fk AND\n"
                    + "  t_hierarquia.organograma_fk = t_unidade_organica.organograma_fk AND\n"
                    + "  t_hierarquia.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                    + "  t_funcionario_contrato.pessoa_fk = :idPessoa AND t_hierarquia.tipo='U')\n"
                    + "union\n"
                    + "  (SELECT \n"
                    + "  t_hierarquia.organograma_fk\n"
                    + "FROM \n"
                    + "  e_pontows.t_hierarquia, \n"
                    + "  e_pontows.t_funcionario_contrato\n"
                    + "WHERE \n"
                    + "  t_hierarquia.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                    + "  t_funcionario_contrato.pessoa_fk = :idPessoa AND \n"
                    + "  t_hierarquia.tipo = 'S')"
                    + ")";
        }

        consulta += "ORDER BY 2, 1";

        Query query = getEntityManager().createNativeQuery(consulta, "relatorioOcorrencia");
        Query queryTodosRegistros = null;

        query.setParameter("periodo", periodo);
        if (idUnidade != null) {
            query.setParameter("idUnidade", idUnidade);
        }

        queryTodosRegistros = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM ( " + consulta + " ) Q");
        queryTodosRegistros.setParameter("periodo", periodo);
        if (idUnidade != null) {
            queryTodosRegistros.setParameter("idUnidade", idUnidade);
        }

        if (qtdGlobal.intValue() == 0) {
            query.setParameter("idPessoa", idPessoa);
            queryTodosRegistros.setParameter("idPessoa", idPessoa);
        }

        query.setMaxResults(length);
        query.setFirstResult(start);

        data = query.getResultList();
        BigInteger filtrados = (BigInteger) queryTodosRegistros.getSingleResult();

        paginacaoVO.setDraw(draw++);
        paginacaoVO.setRecordsTotal(filtrados.intValue()); //paginacaoVO.setRecordsTotal(recordsTotal.size());
        paginacaoVO.setRecordsFiltered(filtrados.intValue()); //paginacaoVO.setRecordsFiltered(recordsFiltered.size());
        paginacaoVO.setData(data);

        return paginacaoVO;
    }

    /**
     *
     * @param periodo
     * @param idPessoa
     * @param idUnidade
     * @param start
     * @param length
     * @param nome
     * @param draw
     * @return
     */
    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("pessoanaojust/{periodo}/{idPessoa}")
    @Produces({"application/json"})
    public PaginacaoVO listaOcorrenciasPorNome(@PathParam("periodo") String periodo,
            @PathParam("idPessoa") BigInteger idPessoa,
            @QueryParam("idUnidade") BigInteger idUnidade,
            @QueryParam("start") int start, @QueryParam("length") int length,
            @QueryParam("search[value]") String nome, @QueryParam("draw") int draw) {

        PaginacaoVO paginacaoVO = new PaginacaoVO();
        List<RelatorioOcorrenciasVO> data = null; // Somente os dados que serão mostrados

        Query query = null;
        Query queryTodosRegistros = null;

        String consulta = "SELECT nome,\n"
                + "       matricula,\n"
                + "       categoria,\n"
                + "       unidade,\n"
                + "       cast(atraso AS bigint),\n"
                + "       cast(meia_falta AS bigint),\n"
                + "       cast(falta AS bigint),\n"
                + "       qtd_horas,\n"
                + "       vlr_pago\n"
                + "FROM\n"
                + "  (SELECT nome,\n"
                + "          matricula,\n"
                + "          organograma_pk,\n"
                + "          funcional_fk,          \n"
                + "          categoria,\n"
                + "          unidade,\n"
                + "          SUM(atraso) AS atraso,\n"
                + "          SUM(meia_falta) AS meia_falta,\n"
                + "          SUM(falta) AS falta,\n"
                + "          CAST(SUM(qtd_horas) AS TEXT) AS qtd_horas,\n"
                + "          to_char(SUM(vlr_pago), 'L9G999G990D99') AS vlr_pago\n"
                + "   FROM\n"
                + "     (SELECT organograma_pk,\n"
                + "	     funcional_fk,\n"
                + "             nome,\n"
                + "             matricula,\n"
                + "             categoria,\n"
                + "             unidade,\n"
                + "             atraso,\n"
                + "             meia_falta,\n"
                + "             falta,\n"
                + "             coalesce(qtd_horas,INTERVAL'00:00:00') AS qtd_horas,\n"
                + "             COALESCE(vlr_pago, 0) AS vlr_pago\n"
                + "      FROM (\n"
                + "              (SELECT falta.organograma_pk,\n"
                + "                      falta.funcional_fk,\n"
                + "                      falta.nome,\n"
                + "                      falta.matricula,\n"
                + "                      falta.unidade,\n"
                + "                      falta.categoria,\n"
                + "                      COUNT(*) AS falta,\n"
                + "                      CAST(NULL AS bigint) AS meia_falta,\n"
                + "                      CAST(NULL AS bigint) AS atraso,\n"
                + "                      SUM(falta.tempo) AS qtd_horas,\n"
                + "                      SUM(falta.qtd_horas * t_valor_hora.valor_hora) AS vlr_pago\n"
                + "               FROM e_pontows.v_faltas_cagos_unidade falta\n"
                + "               LEFT JOIN t_valor_hora ON t_valor_hora.funcionario_contrato_fk = funcionario_contrato_pk\n"
                + "               AND to_char(t_valor_hora.competencia, 'MM/yyyy') = :periodo\n"
                + "               WHERE falta.ocorrencia_pk = 1\n"
                + "                 AND to_char(falta.data_processamento, 'MM/yyyy') = :periodo \n"
                + "                 AND ((:nome is null) OR (falta.nome like :nome))\n"
                + "GROUP BY falta.organograma_pk,\n"
                + "         falta.funcional_fk,\n"
                + "         falta.nome,\n"
                + "         falta.matricula,\n"
                + "         falta.unidade,\n"
                + "         falta.categoria)\n"
                + "            UNION\n"
                + "              (SELECT falta.organograma_pk,\n"
                + "		      falta.funcional_fk,\n"
                + "                      falta.nome,\n"
                + "                      falta.matricula,\n"
                + "                      falta.unidade,\n"
                + "                      falta.categoria,\n"
                + "                      CAST(NULL AS bigint) AS falta,\n"
                + "                      COUNT(*) AS meia_falta,\n"
                + "                      CAST(NULL AS bigint) AS atraso,\n"
                + "                      SUM(falta.tempo) AS qtd_horas,\n"
                + "                      SUM(falta.qtd_horas * t_valor_hora.valor_hora) AS vlr_pago\n"
                + "               FROM e_pontows.v_faltas_cagos_unidade falta\n"
                + "               LEFT JOIN t_valor_hora ON t_valor_hora.funcionario_contrato_fk = funcionario_contrato_pk\n"
                + "               AND to_char(t_valor_hora.competencia, 'MM/yyyy') = :periodo\n"
                + "               WHERE falta.ocorrencia_pk = 6\n"
                + "                 AND to_char(falta.data_processamento, 'MM/yyyy') = :periodo \n"
                + "                 AND ((:nome is null) OR (falta.nome like :nome))\n"
                + "GROUP BY falta.organograma_pk,\n"
                + "         falta.funcional_fk,\n"
                + "         falta.nome,\n"
                + "         falta.matricula,\n"
                + "         falta.unidade,\n"
                + "         falta.categoria)\n"
                + "            UNION\n"
                + "              (SELECT falta.organograma_pk,\n"
                + "		      falta.funcional_fk,\n"
                + "                      falta.nome,\n"
                + "                      falta.matricula,\n"
                + "                      falta.unidade,\n"
                + "                      falta.categoria,\n"
                + "                      CAST(NULL AS bigint) AS falta,\n"
                + "                      CAST(NULL AS bigint) AS meia_falta,\n"
                + "                      COUNT(*) AS atraso,\n"
                + "                      SUM(falta.tempo) AS qtd_horas,\n"
                + "                      SUM(falta.qtd_horas * t_valor_hora.valor_hora) AS vlr_pago\n"
                + "               FROM e_pontows.v_faltas_cagos_unidade falta\n"
                + "               LEFT JOIN t_valor_hora ON t_valor_hora.funcionario_contrato_fk = funcionario_contrato_pk\n"
                + "               AND to_char(t_valor_hora.competencia, 'MM/yyyy') = :periodo \n"
                + "               WHERE falta.ocorrencia_pk = 2\n"
                + "                 AND to_char(falta.data_processamento, 'MM/yyyy') = :periodo \n"
                + "                 AND ((:nome is null) OR (falta.nome like :nome))\n"
                + "GROUP BY falta.organograma_pk,\n"
                + "	 falta.funcional_fk,\n"
                + "         falta.nome,\n"
                + "         falta.matricula,\n"
                + "         falta.unidade,\n"
                + "         falta.categoria) --selct interno\n"
                + "         ) b\n"
                + "         ) u\n"
                + "   GROUP BY 1,\n"
                + "            2,\n"
                + "            3,\n"
                + "            4,\n"
                + "            5,\n"
                + "            6) A\n"
                + "WHERE 1=1  \n" + ((idUnidade != null) ? " AND A.organograma_pk = :idUnidade\n" : "\n");

        // Verifica se o usuario é Geral
        String consultaGlobal = "select count(*) from Hierarquia h \n"
                + " where h.tipo = 'G' and \n"
                + " h.funcionarioContrato.funcionarioFk.id = :pessoa ";

        Query query1 = getEntityManager().createQuery(consultaGlobal);
        query1.setParameter("pessoa", idPessoa);
        Long qtdGlobal = (Long) query1.getSingleResult();

        if (qtdGlobal.intValue() == 0) {
            // Inclui as unidades que o usuario tem acesso
            consulta += "AND ( A.organograma_pk IN "
                    + "(SELECT \n"
                    + "  t_unidade_organica_item.organograma_fk\n"
                    + "FROM \n"
                    + "  e_pontows.t_unidade_organica, \n"
                    + "  e_pontows.t_unidade_organica_item, \n"
                    + "  e_pontows.t_hierarquia, \n"
                    + "  e_pontows.t_funcionario_contrato\n"
                    + "WHERE \n"
                    + "  t_unidade_organica.unidade_organica_pk = t_unidade_organica_item.unidade_organica_fk AND\n"
                    + "  t_hierarquia.organograma_fk = t_unidade_organica.organograma_fk AND\n"
                    + "  t_hierarquia.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                    + "  t_funcionario_contrato.pessoa_fk = :idPessoa AND t_hierarquia.tipo='U')\n"
                    + " OR A.funcional_fk IN \n"
                    + "  (SELECT \n"
                    + "  t_hierarquia.organograma_fk\n"
                    + "FROM \n"
                    + "  e_pontows.t_hierarquia, \n"
                    + "  e_pontows.t_funcionario_contrato\n"
                    + "WHERE \n"
                    + "  t_hierarquia.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                    + "  t_funcionario_contrato.pessoa_fk = :idPessoa AND \n"
                    + "  t_hierarquia.tipo = 'S')"
                    + ")";
        }

        consulta += "ORDER BY 4, 1";

        query = getEntityManager().createNativeQuery(consulta, "relatorioOcorrenciapessoa");
        query.setParameter("periodo", periodo);
        if (idUnidade != null) {
            query.setParameter("idUnidade", idUnidade);
        }

        if (qtdGlobal.intValue() == 0) {
            query.setParameter("idPessoa", idPessoa);
        }

        queryTodosRegistros = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM ( " + consulta + " ) Q");

        if (nome != null) {
            nome = nome.toUpperCase();
        }
        query.setParameter("nome", "%" + nome + "%");
        queryTodosRegistros.setParameter("nome", "%" + nome + "%");
        queryTodosRegistros.setParameter("periodo", periodo);
        if (idUnidade != null) {
            queryTodosRegistros.setParameter("idUnidade", idUnidade);
        }

        if (qtdGlobal.intValue() == 0) {
            query.setParameter("idPessoa", idPessoa);
            queryTodosRegistros.setParameter("idPessoa", idPessoa);
        }

        query.setMaxResults(length);
        query.setFirstResult(start);

        data = query.getResultList();
        BigInteger filtrados = (BigInteger) queryTodosRegistros.getSingleResult();

        paginacaoVO.setDraw(draw++);
        paginacaoVO.setRecordsTotal(filtrados.intValue()); //paginacaoVO.setRecordsTotal(recordsTotal.size());
        paginacaoVO.setRecordsFiltered(filtrados.intValue()); //paginacaoVO.setRecordsFiltered(recordsFiltered.size());
        paginacaoVO.setData(data);

        return paginacaoVO;
    }

}
