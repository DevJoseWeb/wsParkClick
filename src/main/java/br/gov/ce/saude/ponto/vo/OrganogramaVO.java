/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

/**
 *
 * @author joao
 */
public class OrganogramaVO {
    
    private Integer id;    
    private Integer organogramaPk;    
    private String sigla;
    private String nomeOrg;
    

    public OrganogramaVO(Integer organogramaPk, String sigla, String nomeOrg) {
        this.id = organogramaPk;
        this.organogramaPk = organogramaPk;
        this.nomeOrg = nomeOrg;
        this.sigla = sigla;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getOrganogramaPk() {
        return organogramaPk;
    }

    public void setOrganogramaPk(Integer organogramaPk) {
        this.organogramaPk = organogramaPk;
    }

    public String getNomeOrg() {
        return nomeOrg;
    }

    public void setNomeOrg(String nomeOrg) {
        this.nomeOrg = nomeOrg;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    
}
