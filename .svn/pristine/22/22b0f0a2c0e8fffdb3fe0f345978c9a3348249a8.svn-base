/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.util;

import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author jcfbandeira
 */
public class Arquivo implements SesaModel{

    private static final long serialVersionUID = -3029111629095698875L;
    
    private BigInteger id;
    
    @JsonSerialize(using = JsonArquivoSerializer.class)
    private String arq;

    public String getArq() {
        return arq;
    }

    public void setArq(String arq) {
        this.arq = arq;
    }
            
            
    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;        
    }
    
}
