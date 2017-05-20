/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.MessageInfo;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.PontoProcessado;
import java.math.BigInteger;
import java.util.List;
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
 * @author Cristiano Bandeira
 */
@Stateless
@Path("pontoprocessado")
public class PontoProcessadoFacadeREST extends AbstractFacade<PontoProcessado, ServiceException> {

    public PontoProcessadoFacadeREST() {
        super(PontoProcessado.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public MessageInfo atualizaProcessamento(PontoProcessado entity) {

        PontoProcessado pontoProcessado = null;

        if (entity.getId() != null) {
            Query query = getEntityManager().createNativeQuery("UPDATE t_ponto_processado\n"
                    + "   SET justificativa_fk= " + entity.getJustificativaFk().getId() + " \n"
                    + " WHERE ponto_processado_pk=:idPontoProcessado");

            query.setParameter("idPontoProcessado", entity.getId());
            query.executeUpdate();

            if (entity.getBatidaRelFk() != null && entity.getBatidaRelFk().getId() != null) {
                Query queryBatida = getEntityManager().createNativeQuery("UPDATE t_batida_rel\n"
                        + "   SET justificativa_gestor_fk= " + entity.getJustificativaFk().getId() + " \n"
                        + " WHERE batida_rel_pk=:idBatidaRelPk");

                queryBatida.setParameter("idBatidaRelPk", entity.getBatidaRelFk().getId());
                queryBatida.executeUpdate();

            } else if (entity.getBatidaRelEscalaFk() != null && entity.getBatidaRelEscalaFk().getId() != null) {
                Query queryBatidaEscala = getEntityManager().createNativeQuery("UPDATE t_batida_rel_escala\n"
                        + "   SET justificativa_gestor_fk= " + entity.getJustificativaFk().getId() + " \n"
                        + " WHERE batida_rel_escala_pk=:idBatidaRelEscalaPk");

                queryBatidaEscala.setParameter("idBatidaRelEscalaPk", entity.getBatidaRelEscalaFk().getId());
                queryBatidaEscala.executeUpdate();

            }

        } else {
            getBusiness().addErrorMessage(null, "Não foi possível realizar a justificativa.");
            getBusiness().lancarException();
        }

        return new MessageInfo(null, "Atualizado com sucesso");
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public PontoProcessado edit(PontoProcessado entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigInteger id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public PontoProcessado find(@PathParam("id") BigInteger id) {
        return super.find(id);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<PontoProcessado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

}
