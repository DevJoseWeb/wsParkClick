/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Funcao;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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
@Path("funcao")
public class FuncaoFacadeREST extends AbstractFacade<Funcao, ServiceException> {

    public FuncaoFacadeREST() {
        super(Funcao.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Funcao create(Funcao entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Funcao edit(Funcao entity) {
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
    public List<Funcao> findAll() {
        List<Funcao> lista = super.findAll();
        return lista;
    }


    @GET
    @Path("hospitalar")
    @Produces({"application/json"})
    public List<Funcao> buscar() {
        List<Funcao> lista = super.findAll();
        List<Funcao> retorno = new ArrayList<Funcao>();
        List auxlist=  Arrays.asList(2,3,5,10,15,16,21,25,26,28,29,30,31,32,47,98,99,101,143,906,909,911);
        //2,3,5,10,15,16,21,25,26,28,29,30,31,32,47,98,99,101,143,906,909,911
        for (Funcao funcao : lista) {
          if (auxlist.contains(funcao.getId().intValue())){
              retorno.add(funcao);
          }  
        }
        lista.clear();
        return retorno;
    }
    
    


    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Funcao getVinculo(@PathParam("id") BigInteger id) {
        return super.find(id);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
}
