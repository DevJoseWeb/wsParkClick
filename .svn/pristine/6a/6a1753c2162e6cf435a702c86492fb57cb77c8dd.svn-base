/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.io.Serializable;
import java.math.BigInteger;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HoraTrabalhadaRelVO implements Serializable {

    private static final long serialVersionUID = -5618027304311826453L;
    
    public BigInteger empresa;
    public BigInteger setor;
    public BigInteger funcao;
    public String data_inicio;
    public String data_fim;

    public HoraTrabalhadaRelVO() {
    }

    public HoraTrabalhadaRelVO(BigInteger empresa, BigInteger setor, BigInteger funcao, String data_inicio, String data_fim) {
        this.empresa = empresa;
        this.setor = setor;
        this.funcao = funcao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public BigInteger getEmpresa() {
        return empresa;
    }

    public void setEmpresa(BigInteger empresa) {
        this.empresa = empresa;
    }

    public BigInteger getSetor() {
        return setor;
    }

    public void setSetor(BigInteger setor) {
        this.setor = setor;
    }

    public BigInteger getFuncao() {
        return funcao;
    }

    public void setFuncao(BigInteger funcao) {
        this.funcao = funcao;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }
    
    
}
 