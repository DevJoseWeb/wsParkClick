/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Funcao;
import br.gov.ce.saude.ponto.util.Arquivo;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author esmayktillesse
 */
@Stateless
@Path("arqivo")
public class ArquivoFacadeREST extends AbstractFacade<Arquivo, ServiceException> {

    public ArquivoFacadeREST() {
        super(Arquivo.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public void gravar(Arquivo entity) {
        BigInteger id = entity.getId();
    }
}
