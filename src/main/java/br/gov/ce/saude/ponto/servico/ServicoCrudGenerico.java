/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.core.SesaException;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.autenticacao.service.AbstractFacade;
import java.math.BigInteger;
import java.util.List;
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
 * @param <T>
 * @param <X>
 */
public class ServicoCrudGenerico<T extends SesaModel, X extends SesaException> extends AbstractFacade<T, X> {

    public ServicoCrudGenerico(Class<T> entityClass) {
        super(entityClass);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public T create(T entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public T edit(T entity) {
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
    public List<T > findAll() {
        List<T > lista = super.findAll();
        return lista;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }    

}
