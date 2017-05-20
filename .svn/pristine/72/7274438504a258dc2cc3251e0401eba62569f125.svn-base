/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.util.JsonDateTimeSerializer;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author joao
 */
public class EscalaMensalVO implements Serializable {

    private static final long serialVersionUID = -8032428964498115883L;

    public EscalaMensalVO() {
    }

    public EscalaMensalVO(BigInteger id, String matricula, String nome, 
            String funcao, BigInteger idEscala, Date entrada, Date saida, 
            BigInteger idLegenda, String legenda) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.funcao = funcao;
        this.idEscala = idEscala;
        this.entrada = entrada;
        this.saida = saida;
        this.idLegenda = idLegenda;
        this.legenda = legenda;
    }


    private BigInteger id;

    private String matricula;
           
    private String nome;
    
    private String funcao;
    
    private BigInteger idEscala;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private Date entrada;
    
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private Date saida;
    
    private BigInteger idLegenda;
    
    private String legenda;
   
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigInteger getIdEscala() {
        return idEscala;
    }

    public void setIdEscala(BigInteger idEscala) {
        this.idEscala = idEscala;
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

    public BigInteger getIdLegenda() {
        return idLegenda;
    }

    public void setIdLegenda(BigInteger idLegenda) {
        this.idLegenda = idLegenda;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    
}
