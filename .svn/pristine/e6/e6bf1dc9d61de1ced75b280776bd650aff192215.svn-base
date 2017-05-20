/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico.relatorio;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Ocorrencia;
import br.gov.ce.saude.ponto.vo.HoraTrabalhadaRelVO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author joao
 */
@Stateless
@Path("relatorio")
public class RelatorioPontoAgrupadoREST extends AbstractFacade<Ocorrencia, ServiceException> {

    public RelatorioPontoAgrupadoREST() {
        super(Ocorrencia.class);
    }

    
    /**
     * Gera relatorio de batidas de ponto de plantonistas passando obrigatorio o intervalo de datas,
     *  e opcionalmente unidade funcional, função ou empresa do funcionario
     * @param ht
     * @return
     * @throws ParseException
     * @throws SQLException
     * @throws NamingException
     * @throws JRException 
     */
    @POST
    @Path("pontoagrupado")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getRelatorio(HoraTrabalhadaRelVO ht) throws ParseException, SQLException, NamingException, JRException {

        String sql_fragment = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date inicio = new Date(sdf.parse(ht.data_inicio).getTime());
        Date data_fim = new Date(sdf.parse(ht.data_fim).getTime());
        
        Map<String, Object> params = new HashMap<>();
        params.put("P_DATA_INICIO", inicio);
        params.put("P_DATA_FIM",  data_fim);

        if ((ht.setor != null )) {
            sql_fragment += "  AND  fc.unidade_funcional_fk = "+ht.setor+" \n";
        }

        if ((ht.funcao != null)) {
            sql_fragment += "  AND  fc.funcao_fk = "+ht.funcao+" \n";
        } else {
            sql_fragment += "  AND  fc.funcao_fk  IS NOT NULL \n";
        }

        if ((ht.empresa != null)) {
            sql_fragment += "  AND  p.empresa_fk = "+ht.empresa+" \n";
        } else {
            sql_fragment += "  AND  p.empresa_fk  IS NULL \n";
        }
        
        params.put("sql_fragment", sql_fragment);
        
        Response response = null;
        try {            
            response = getRelatorio("relPontoAgrupadoMensal.jasper", params);
        } catch (Exception ex) {
            getBusiness().addErrorMessage(null, "Não foi possivel gerar o relatório.");
            getBusiness().lancarException();
        }

        return response;
    }

}
