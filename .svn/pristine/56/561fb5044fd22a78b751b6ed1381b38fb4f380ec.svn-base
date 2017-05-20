package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.ValorHora;
import br.gov.ce.saude.ponto.vo.ValorHoraVO;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jr
 */
@Stateless
@Path("cargahoraria")
public class CargaHorariaFacadeREST extends AbstractFacade<ValorHora, ServiceException> {

    public CargaHorariaFacadeREST() {
        super(ValorHora.class);
    }

    @GET
    @Path("carga/{matricula}")
    @Produces({"application/json"})
    public ValorHoraVO getCarga(@PathParam("matricula") String matricula) throws SQLException {
        String hql = "SELECT e_pontows.t_valor_hora.competencia,\n"
                + "    e_pontows.t_valor_hora.valor_hora,\n"
                + "    e_pontows.t_valor_hora.vencimento,\n"
                + "    e_pontows.t_valor_hora.jornada_mensal,\n"
                + "    e_pontows.t_valor_hora.jornada_semanal,\n"
                + "    e_pontows.t_valor_hora.jornada_dias_uteis\n"
                + "  FROM e_pontows.t_funcionario_contrato,\n"
                + "       e_pontows.t_valor_hora\n"
                + "  WHERE "
                + "  t_funcionario_contrato.matricula = :matricula "
                + "AND e_pontows.t_funcionario_contrato.funcionario_contrato_pk = "
                + "e_pontows.t_valor_hora.funcionario_contrato_fk "
                + " order by t_valor_hora.competencia"
                + " limit 1";

        Query consulta = getEntityManager().createNativeQuery(hql, "valorHora");
        //Query consulta = getEntityManager().createNativeQuery(hql);
        consulta.setParameter("matricula", matricula);

        //Object vh1 = null;// (ValorHoraVO) consulta.getSingleResult();
        ValorHoraVO vh = null;
        try {
            vh = (ValorHoraVO) consulta.getSingleResult();
            //vh1 = consulta.getSingleResult();
        } catch (NoResultException e) {
        }

        //ValorHoraVO vh = null;
        return vh;
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<ValorHora> findAll() {
        List<ValorHora> lista = super.findAll();
        return lista;
    }
}
