/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BatePontoVO implements Serializable {
    
    private static final long serialVersionUID = 7829612427544821580L;
    
    private BigInteger funcionarioContratoPk;
    private String matricula;
    private String nome;
    private String categoria;
    private BigInteger idUnidade;
    private BigInteger idSetor;
    private String unidade;
    private String setor;
    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private List<BatePontoVO> data;

    public BatePontoVO() {
    }

    public BatePontoVO(BigInteger funcionarioContratoPk, String matricula, String nome, String categoria, BigInteger idUnidade, BigInteger idSetor, String unidade, String setor) {
        this.funcionarioContratoPk = funcionarioContratoPk;
        this.matricula = matricula;
        this.nome = nome;
        this.categoria = categoria;
        this.idUnidade = idUnidade;
        this.idSetor = idSetor;
        this.unidade = unidade;
        this.setor = setor;
    }

    

    public BigInteger getFuncionarioContratoPk() {
        return funcionarioContratoPk;
    }

    public void setFuncionarioContratoPk(BigInteger funcionarioContratoPk) {
        this.funcionarioContratoPk = funcionarioContratoPk;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigInteger getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(BigInteger idUnidade) {
        this.idUnidade = idUnidade;
    }

    public BigInteger getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(BigInteger idSetor) {
        this.idSetor = idSetor;
    }
    
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<BatePontoVO> getData() {
        return data;
    }

    public void setData(List<BatePontoVO> data) {
        this.data = data;
    }

}
