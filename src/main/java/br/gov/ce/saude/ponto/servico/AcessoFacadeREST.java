/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.MessageInfo;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Acesso;
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
@Path("acesso")
public class AcessoFacadeREST extends AbstractFacade<Acesso, ServiceException> {

    public AcessoFacadeREST() {
        super(Acesso.class);
    }

    @POST
    @Path("zerarsenhamjp")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public MessageInfo zerarSenhaMjp(String matricula) {
        Acesso acesso = new Acesso();
        acesso.setLogin(matricula);
        List<Acesso> listAcesso = getBusiness().listarPorExemplo(acesso, 1);

        if (listAcesso != null && listAcesso.size() > 0) {
            acesso = listAcesso.get(0);
            acesso.setSenha("0922bd366fab6bebe9c56961858eb9cc");
            return new MessageInfo(null, "Senha zerada com sucesso.");
        }
        
        getBusiness().addErrorMessage(null, "Não foi possivel zerar a senha do usuário");
        getBusiness().lancarException();
        return null;
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Acesso create(Acesso entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Acesso edit(Acesso entity) {
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
    public List<Acesso> findAll() {
        List<Acesso> lista = super.findAll();
        return lista;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Acesso getAcesso(@PathParam("id") BigInteger id) {
        return super.find(id);
    }
}
