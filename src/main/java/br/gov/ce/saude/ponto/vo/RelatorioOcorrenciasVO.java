/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.math.BigInteger;

/**
 *
 * @author joao
 */
public class RelatorioOcorrenciasVO {
    
    private String nome;
    private String matricula;
    
    private String categoria;
    private String unidade;
    
    private BigInteger atraso;
    private BigInteger meiaFalta;
    private BigInteger falta;
    
    private String qtdHoras;
    private String vlrPago;

    public RelatorioOcorrenciasVO(String categoria, String unidade, BigInteger atraso, BigInteger meiaFalta, BigInteger falta, String qtdHoras, String vlrPago) {
        this.categoria = categoria;
        this.unidade = unidade;
        this.atraso = atraso;
        this.meiaFalta = meiaFalta;
        this.falta = falta;
        this.qtdHoras = qtdHoras;
        this.vlrPago = vlrPago;
    }
    
    
    public RelatorioOcorrenciasVO(String nome, String matricula, String categoria, String unidade, 
            BigInteger atraso, BigInteger meiaFalta, BigInteger falta, String qtdHoras, String vlrPago) {        
        this.nome = nome;
        this.matricula = matricula;
        
        this.categoria = categoria;
        this.unidade = unidade;
        this.atraso = atraso;
        this.meiaFalta = meiaFalta;
        this.falta = falta;
        this.qtdHoras = qtdHoras;
        this.vlrPago = vlrPago;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigInteger getAtraso() {
        return atraso;
    }

    public void setAtraso(BigInteger atraso) {
        this.atraso = atraso;
    }

    public BigInteger getMeiaFalta() {
        return meiaFalta;
    }

    public void setMeiaFalta(BigInteger meiaFalta) {
        this.meiaFalta = meiaFalta;
    }

    public BigInteger getFalta() {
        return falta;
    }

    public void setFalta(BigInteger falta) {
        this.falta = falta;
    }

    public String getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(String qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public String getVlrPago() {
        return vlrPago;
    }

    public void setVlrPago(String vlrPago) {
        this.vlrPago = vlrPago;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
    
}
