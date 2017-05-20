/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author joao
 */
public class HierarquiaVO {

    private BigInteger hierarquiaPk;
    private Integer organogramaPk;
    private String sigla;
    private String nome_org;
    private Character tipo;

    public HierarquiaVO(BigInteger hierarquiaPk, Integer organogramaPk, String sigla, String nome_org, Character tipo) {
        this.hierarquiaPk = hierarquiaPk;
        this.organogramaPk = organogramaPk;
        this.sigla = sigla;
        this.nome_org = nome_org;
        this.tipo = tipo;
    }
    
    public BigInteger getHierarquiaPk() {
        return hierarquiaPk;
    }

    public void setHierarquiaPk(BigInteger hierarquiaPk) {
        this.hierarquiaPk = hierarquiaPk;
    }

    public Integer getOrganogramaPk() {
        return organogramaPk;
    }

    public void setOrganogramaPk(Integer organogramaPk) {
        this.organogramaPk = organogramaPk;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome_org() {
        return nome_org;
    }

    public void setNome_org(String nome_org) {
        this.nome_org = nome_org;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.hierarquiaPk);
        hash = 29 * hash + Objects.hashCode(this.organogramaPk);
        hash = 29 * hash + Objects.hashCode(this.sigla);
        hash = 29 * hash + Objects.hashCode(this.nome_org);
        hash = 29 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HierarquiaVO other = (HierarquiaVO) obj;
        if (!Objects.equals(this.hierarquiaPk, other.hierarquiaPk)) {
            return false;
        }
        if (!Objects.equals(this.organogramaPk, other.organogramaPk)) {
            return false;
        }
        if (!Objects.equals(this.sigla, other.sigla)) {
            return false;
        }
        if (!Objects.equals(this.nome_org, other.nome_org)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }
    
    
}
