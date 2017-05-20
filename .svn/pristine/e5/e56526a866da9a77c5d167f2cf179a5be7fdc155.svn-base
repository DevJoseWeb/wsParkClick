/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.LegendaEscala;
import java.math.BigInteger;
import java.sql.SQLException;
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
 * @author jcfbandeira
 */
@Stateless
@Path("legendaescala")
public class LegendaEscalaFacadeREST extends br.gov.ce.autenticacao.service.AbstractFacade<LegendaEscala, ServiceException> {

    public LegendaEscalaFacadeREST() {
        super(LegendaEscala.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public LegendaEscala create(LegendaEscala entity) {

        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public LegendaEscala edit(LegendaEscala entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigInteger id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("porUnidade/{idUnidade}")
    @Produces({"application/json"})
    public List<LegendaEscala> find(@PathParam("idUnidade") Integer idUnidade) throws SQLException {
        
        Query queryEscala = getBusiness().getSesaDao().getEntityManager().
                createQuery("FROM LegendaEscala where "
                        + " organogramaFk.id = :idUnidade");
        queryEscala.setParameter("idUnidade", idUnidade);
        
        List<LegendaEscala> legendas = (List<LegendaEscala>) queryEscala.getResultList();
        return legendas;
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<LegendaEscala> findAll() {
        List<LegendaEscala> lista = super.findAll();
        return lista;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
}
