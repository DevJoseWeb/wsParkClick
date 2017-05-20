/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Vinculo;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
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
 * @author esmayktillesse
 */
@Stateless
@Path("vinculo")
public class VinculoFacadeREST extends AbstractFacade<Vinculo, ServiceException> {

    public VinculoFacadeREST() {
        super(Vinculo.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Vinculo create(Vinculo entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Vinculo edit(Vinculo entity) {
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
    public List<Vinculo> findAll() {
        List<Vinculo> lista = super.findAll();
        return lista;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Vinculo getVinculo(@PathParam("id") BigInteger id) {
        return super.find(id);
    }
}
