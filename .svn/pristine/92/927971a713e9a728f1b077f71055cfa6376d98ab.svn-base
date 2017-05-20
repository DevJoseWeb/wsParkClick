/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Afastamento;
import br.gov.ce.saude.ponto.vo.PaginacaoVO;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author esmayktillesse
 */
@Stateless
@Path("afastamento")
public class AfastamentoFacadeREST extends AbstractFacade<Afastamento, ServiceException> {

    public AfastamentoFacadeREST() {
        super(Afastamento.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Afastamento create(Afastamento entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Afastamento edit(Afastamento entity) {
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
    public List<Afastamento> findAll() {
        List<Afastamento> lista = super.findAll();
        return lista;
    }
    
    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("filtro")
    @Produces({"application/json"})
    public PaginacaoVO afastamento(@QueryParam("start") int start,
            @QueryParam("length") int length, @QueryParam("search[value]") String descricao,
            @QueryParam("draw") int draw) {
        
        PaginacaoVO paginacaoVO = new PaginacaoVO();
        List<Afastamento> data = null; // Somente os dados que serão mostrados
        List<Afastamento> recordsFiltered = null; // O total dos filtrados 
        Query query = null;
        Query queryTodosRegistros = null;
        
        String consulta = "FROM Afastamento WHERE 1=1 ";
        
        if (descricao != null && !descricao.equals("")) {
            consulta += " AND funcionarioContratoFk.funcionarioFk.nome like :descricao";            
        }
        
        query = getEntityManager().createQuery(consulta); 
        queryTodosRegistros = getEntityManager().createQuery("SELECT COUNT(*) "+ consulta);
        
        if (descricao != null && !descricao.equals("")) {           
            query.setParameter("descricao", "%"+  descricao.toUpperCase() +"%" );
            queryTodosRegistros.setParameter("descricao", "%"+  descricao.toUpperCase() +"%" );
        }
                
        query.setMaxResults(length);
        query.setFirstResult(start);

        data = query.getResultList();
        Long filtrados = (Long)queryTodosRegistros.getSingleResult();

        paginacaoVO.setDraw(draw++);
        paginacaoVO.setRecordsTotal(filtrados.intValue()); //paginacaoVO.setRecordsTotal(recordsTotal.size());
        paginacaoVO.setRecordsFiltered(filtrados.intValue()); //paginacaoVO.setRecordsFiltered(recordsFiltered.size());
        paginacaoVO.setData(data);

        
        return paginacaoVO;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Afastamento getAfastamento(@PathParam("id") BigInteger id) {
        return super.find(id);
    }
}
