package br.gov.ce.saude.ponto.servico;
import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author jr
 */
@Stateless
@Path("cliente")
public class ClienteFacadeREST extends AbstractFacade<Cliente, ServiceException> {

    public ClienteFacadeREST() {
        super(Cliente.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Cliente create(Cliente entity) {
        return super.create(entity);
    }
    
    @GET
    @Override
    @Produces({"application/json"})
    public List<Cliente> findAll() {
        List<Cliente> lista = super.findAll();
        return lista;
    }
    
}
