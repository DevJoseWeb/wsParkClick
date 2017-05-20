package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Regime;
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
@Path("regime")
public class RegimeFacadeREST  extends AbstractFacade<Regime, ServiceException> {

     public RegimeFacadeREST() {
        super(Regime.class);
    }
     
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Regime create(Regime entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Regime edit(Regime entity) {
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
    public List<Regime> findAll() {
        List<Regime> lista = super.findAll();
        return lista;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Regime getVinculo(@PathParam("id") BigInteger id) {
        return super.find(id);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
}
