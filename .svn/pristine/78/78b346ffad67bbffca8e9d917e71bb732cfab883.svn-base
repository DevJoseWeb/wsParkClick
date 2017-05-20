/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SemEscalaPontoVO implements Serializable {
    
    private static final long serialVersionUID = 7829612427544821580L;
        
    private String matricula;
    private String nome;
    private String unidade;
    private String setor;
    private String vinculo;

    public SemEscalaPontoVO(String matricula, String nome, String unidade, String setor, String vinculo) {
        this.matricula = matricula;
        this.nome = nome;
        this.unidade = unidade;
        this.setor = setor;
        this.vinculo = vinculo;
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

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
    
    
    
}
