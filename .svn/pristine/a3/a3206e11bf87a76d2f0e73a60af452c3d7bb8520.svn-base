/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.BatidaRel;
import br.gov.ce.saude.ponto.entidade.EscalaItem;
import br.gov.ce.saude.ponto.entidade.FuncionarioContrato;
import br.gov.ce.saude.ponto.entidade.FuncionarioHorario;
import br.gov.ce.saude.ponto.entidade.Horario;
import br.gov.ce.saude.ponto.entidade.Regime;
import br.gov.ce.saude.ponto.util.TipoRegime;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author joao
 */
@Stateless
@Path("funcionariohorario")
public class FuncionarioHorarioFacadeREST extends AbstractFacade<FuncionarioHorario, ServiceException> {

    public FuncionarioHorarioFacadeREST() {
        super(FuncionarioHorario.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public FuncionarioHorario find(@PathParam("id") BigInteger id) {
        FuncionarioHorario funcionarioHorario = super.find(id);
        if (funcionarioHorario == null) {
            funcionarioHorario.getHorario().getId();
        }
        return funcionarioHorario;
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public FuncionarioHorario create(FuncionarioHorario entity) {

        FuncionarioHorario funcHorarioAntigo = null;
        try {
            Query queryFuncHorarioAntigo = getEntityManager().
                    createQuery("FROM FuncionarioHorario WHERE funcionarioContrato.id =:funcontrato AND ativo=true");
            queryFuncHorarioAntigo.setParameter("funcontrato", entity.getFuncionarioContratoFk());
            funcHorarioAntigo = (FuncionarioHorario) queryFuncHorarioAntigo.getSingleResult();
            funcHorarioAntigo.setAtivo(Boolean.FALSE);
        } catch (Exception e) {
        }

        Query queryContrato = getEntityManager().
                createQuery("FROM FuncionarioContrato WHERE id =:funcontrato");
        queryContrato.setParameter("funcontrato", entity.getFuncionarioContratoFk());
        FuncionarioContrato funcionarioContrato = (FuncionarioContrato) queryContrato.getSingleResult();

        BigInteger codigoRegime = TipoRegime.getId(entity.getDiarista(), entity.getPlantonista(), entity.getHoraExtra());
        Query queryRegime = getEntityManager().createQuery("FROM Regime WHERE id =:idregime");
        queryRegime.setParameter("idregime", codigoRegime);
        Regime regime = (Regime) queryRegime.getSingleResult();

        Query queryHorario = getEntityManager().
                createQuery("FROM Horario WHERE id =:idHorario");
        queryHorario.setParameter("idHorario", entity.getHorarioFk());
        Horario horario = (Horario) queryHorario.getSingleResult();

        entity.setHorario(horario);
        entity.setRegimeFk(regime);
        entity.setFuncionarioContrato(funcionarioContrato);
        entity.setAtivo(Boolean.TRUE);

        super.create(entity);

        // Para todas as escalas com data de inicio maiores que hoje atualiza para o novo horario
        Query queryEscalas = getEntityManager().
                createQuery("FROM EscalaItem WHERE entrada >= :data AND funcionarioHorario.funcionarioContrato.id = :idFuncionarioContrato");
        queryEscalas.setParameter("data", new Date());
        queryEscalas.setParameter("idFuncionarioContrato", funcionarioContrato.getId());
        List<EscalaItem> escalaItens = (List<EscalaItem>) queryEscalas.getResultList();
        for (EscalaItem escala : escalaItens) {
            escala.setFuncionarioHorario(entity);
        }

        // Atualiza as batidas do dia com o novo funcionario horario
        if (funcHorarioAntigo != null) {
            Query queryBatidas = getEntityManager().
                    createQuery("FROM BatidaRel WHERE to_char(data,'dd/MM/yyyy') = :data AND funcionarioHorario.id = :idFuncionarioHorario");

            SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
            queryBatidas.setParameter("data", dmy.format(new Date()));

            queryBatidas.setParameter("idFuncionarioHorario", funcHorarioAntigo.getId());
            List<BatidaRel> batidaRels = (List<BatidaRel>) queryBatidas.getResultList();

            if (!batidaRels.isEmpty()) {
                Query query = getEntityManager().createNativeQuery("UPDATE t_batida\n"
                        + "   SET funcionario_horario_fk= " + entity.getId() + " \n"
                        + " WHERE cast (data_batida as date) = :hoje \n"
                        + " AND funcionario_horario_fk = :funcionario_horario_antigo");

                try {
                    query.setParameter("hoje", dmy.parse(dmy.format(new Date())));
                } catch (ParseException ex) {                    
                }
                query.setParameter("funcionario_horario_antigo", funcHorarioAntigo.getId());
                query.executeUpdate();
            }

            for (BatidaRel batidaRel : batidaRels) {
                batidaRel.setFuncionarioHorario(entity);
            }
        }
        return entity;
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public FuncionarioHorario edit(FuncionarioHorario entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigInteger id) {
        super.remove(super.find(id));
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<FuncionarioHorario> findAll() {
        List<FuncionarioHorario> lista = super.findAll();
        return lista;
    }

    @POST
    @Path("incluir")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public FuncionarioHorario criar(FuncionarioHorario entity) {
        Query queryContrato = getEntityManager().
                createQuery("FROM FuncionarioContrato WHERE id =:funcontrato");
        queryContrato.setParameter("funcontrato", entity.getFuncionarioContratoFk());
        FuncionarioContrato funcionarioContrato = (FuncionarioContrato) queryContrato.getSingleResult();
        entity.setFuncionarioContrato(funcionarioContrato);
        return super.create(entity);
    }

}
