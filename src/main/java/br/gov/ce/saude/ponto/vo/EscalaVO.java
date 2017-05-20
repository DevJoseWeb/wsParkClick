/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.util.Date;

/**
 *
 * @author joao
 */
public class EscalaVO {
    private Date entrada;
    private Date saida;

    public EscalaVO(Date entrada, Date saida) {
        this.entrada = entrada;
        this.saida = saida;
    }
    
    

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
}
