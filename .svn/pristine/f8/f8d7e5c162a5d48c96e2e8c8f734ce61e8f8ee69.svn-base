/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.GrauInstrucao;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author esmayktillesse
 */
@Stateless
@Path("grauInstrucao")
public class GrauInstrucaoFacadeREST extends AbstractFacade<GrauInstrucao, ServiceException> {

    public GrauInstrucaoFacadeREST() {
        super(GrauInstrucao.class);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<GrauInstrucao> findAll() {
        List<GrauInstrucao> lista = super.findAll();
        return lista;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public GrauInstrucao getGrauInstrucao(@PathParam("id") BigInteger id) {
        return super.find(id);
    }
}
