/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico.relatorio;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Ocorrencia;
import br.gov.ce.saude.ponto.servico.BatidaRelFacadeREST;
import br.gov.ce.saude.ponto.vo.PaginacaoVO;
import br.gov.ce.saude.ponto.vo.RelatorioOcorrenciasVO;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("semescala")
public class RelatorioSemEscalaFacadeREST extends AbstractFacade<Ocorrencia, ServiceException> {

    public RelatorioSemEscalaFacadeREST() {
        super(Ocorrencia.class);
    }

    private Date ultimoDiaMesAtual(String mes, String ano) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");

        Calendar cal = GregorianCalendar.getInstance();
        try {
            if (mes.length() == 1) {
                mes = "0" + mes;
            }
            cal.setTime(sdf.parse(mes + "/" + ano));
        } catch (ParseException ex) {
            Logger.getLogger(BatidaRelFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        int dia = 28;
        if (sdf.format(new Date()).equals(mes + "/" + ano)) {
            Calendar calAtual = Calendar.getInstance();
            calAtual.setTime(new Date());
            dia = calAtual.get(Calendar.DAY_OF_MONTH);
        } else {
            dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        String data = ano + "-" + mes + "-" + dia;

        SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdfData.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioSemEscalaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Date primeiroDiaMes(String mes, String ano) {
        String data = ano + "-" + mes + "-01";
        SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdfData.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioSemEscalaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Lista pessoas que nao batem ponto por hierarquia
     *
     * @param mes
     * @param ano
     * @param idPessoa
     * @param start
     * @param length
     * @param nome
     * @param draw
     * @return
     */
    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("{idPessoa}/{mes}/{ano}")
    @Produces({"application/json"})
    public PaginacaoVO listaOcorrenciasPorNome(@PathParam("mes") String mes,
            @PathParam("ano") String ano,
            @PathParam("idPessoa") BigInteger idPessoa,
            @QueryParam("idUnidade") BigInteger idUnidade,
            @QueryParam("idVinculo") BigInteger idVinculo,
            @QueryParam("start") int start, @QueryParam("length") int length,
            @QueryParam("search[value]") String nome, @QueryParam("draw") int draw) {

        PaginacaoVO paginacaoVO = new PaginacaoVO();
        List<RelatorioOcorrenciasVO> data = null; // Somente os dados que serão mostrados

        Query query = null;
        Query queryTodosRegistros = null;

        String consulta = "SELECT \n"
                + "  distinct t_funcionario_contrato.matricula, \n"
                + "  t_pessoa.nome,   \n"
                + "  organica.sigla as unidade, \n"
                + "  funcional.sigla as setor,\n"
                + "  (select descricao from e_pontows.t_vinculo where vinculo_pk = t_funcionario_contrato.vinculo_fk) as vinculo\n"
                + "FROM   \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_base.t_pessoa, \n"
                + "  e_pontows.t_funcionario_horario,   \n"
                + "  e_base.t_organograma organica, \n"
                + "  e_base.t_organograma funcional\n"
                + "WHERE \n"
                + "  regime_fk in (2,4,6,7) AND\n"
                + "  situacao_funcional_fk =1 AND\n"
                + "  t_pessoa.pessoa_pk = t_funcionario_contrato.pessoa_fk AND\n"
                + "  t_funcionario_horario.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                + "  organica.organograma_pk = t_funcionario_contrato.unidade_organica_fk AND\n"
                + "  funcional.organograma_pk = t_funcionario_contrato.unidade_funcional_fk AND\n"
                + "  \n"
                + "  funcionario_horario_pk not in (select funcionario_horario_fk -- TEM ESCALA MAS NAO TEM BATIDA\n"
                + "	                         from e_pontows.t_escala_item LEFT JOIN e_pontows.t_funcionario_horario \n"
                + "				      ON t_escala_item.funcionario_horario_fk = t_funcionario_horario.funcionario_horario_pk\n"
                + "                                 where funcionario_horario_pk = t_funcionario_horario.funcionario_horario_pk)\n"
                + "                                 \n"
                + "                                 and funcionario_contrato_pk not in (select funcionario_contrato_fk\n"
                + "                                      from e_pontows.t_afastamento\n"
                + "                                      where data_inicial <= :ultimoDia\n"
                + "                                         and coalesce(data_final, :ultimoDia) - data_inicial >= CAST(:ultimoDia AS date) - CAST(:primeiroDia AS date)\n"
                + "                                         and coalesce(data_final, :ultimoDia) >=:primeiroDia)\n"
                + ((idUnidade != null) ? " AND organica.organograma_pk = :idUnidade\n" : "\n")
                + ((idVinculo != null) ? " AND t_funcionario_contrato.vinculo_fk = :idVinculo\n" : "\n")
                + ((nome != null && nome.length() > 0) ? " AND nome like'%" + nome.toUpperCase() + "%'\n" : "\n");

        // Verifica se o usuario é Geral   :primeiroDia  and  :ultimoDia
        String consultaGlobal = "select count(*) from Hierarquia h \n"
                + " where h.tipo = 'G' and \n"
                + " h.funcionarioContrato.funcionarioFk.id = :pessoa ";

        Query query1 = getEntityManager().createQuery(consultaGlobal);
        query1.setParameter("pessoa", idPessoa);
        Long qtdGlobal = (Long) query1.getSingleResult();

        if (qtdGlobal.intValue() == 0) {
            // Inclui as unidades que o usuario tem acesso
            consulta += " AND ( organica.organograma_pk IN "
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
                    + " OR funcional.organograma_pk IN \n"
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

        consulta += "ORDER BY 3,2";

        query = getEntityManager().createNativeQuery(consulta, "relatorioPessoassemEscala");
        query.setParameter("primeiroDia", primeiroDiaMes(mes, ano));
        query.setParameter("ultimoDia", ultimoDiaMesAtual(mes, ano));
        if (idUnidade != null) {
            query.setParameter("idUnidade", idUnidade);
        }
        if (idVinculo != null) {
            query.setParameter("idVinculo", idVinculo);
        }

        if (qtdGlobal.intValue() == 0) {
            query.setParameter("idPessoa", idPessoa);
        }

        queryTodosRegistros = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM ( " + consulta + " ) Q");
        queryTodosRegistros.setParameter("primeiroDia", primeiroDiaMes(mes, ano));
        queryTodosRegistros.setParameter("ultimoDia", ultimoDiaMesAtual(mes, ano));
        if (idUnidade != null) {
            queryTodosRegistros.setParameter("idUnidade", idUnidade);
        }
        if (idVinculo != null) {
            queryTodosRegistros.setParameter("idVinculo", idVinculo);
        }

        query.setParameter("primeiroDia", primeiroDiaMes(mes, ano));
        query.setParameter("ultimoDia", ultimoDiaMesAtual(mes, ano));

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
