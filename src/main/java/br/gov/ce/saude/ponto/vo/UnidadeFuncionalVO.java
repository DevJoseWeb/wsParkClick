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
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UnidadeFuncionalVO implements Serializable {

    private Integer organogramaPk;
    private Integer orgPaiFk;
    private Integer orgPontoFk;
    private String nomeOrg;
    private String sigla;

    public UnidadeFuncionalVO() {
    }

    public UnidadeFuncionalVO(Integer organogramaPk, Integer orgPaiFk, Integer orgPontoFk, String nomeOrg, String sigla) {
        this.organogramaPk = organogramaPk;
        this.orgPaiFk = orgPaiFk;
        this.orgPontoFk = orgPontoFk;
        this.nomeOrg = nomeOrg;
        this.sigla = sigla;
    }

    public Integer getOrganogramaPk() {
        return organogramaPk;
    }

    public void setOrganogramaPk(Integer organogramaPk) {
        this.organogramaPk = organogramaPk;
    }

    public Integer getOrgPaiFk() {
        return orgPaiFk;
    }

    public void setOrgPaiFk(Integer orgPaiFk) {
        this.orgPaiFk = orgPaiFk;
    }

    public Integer getOrgPontoFk() {
        return orgPontoFk;
    }

    public void setOrgPontoFk(Integer orgPontoFk) {
        this.orgPontoFk = orgPontoFk;
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
