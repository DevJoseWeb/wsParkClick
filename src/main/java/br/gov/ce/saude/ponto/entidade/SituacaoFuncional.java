/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_situacao_funcional", schema = "e_pontows")
@XmlRootElement
@SequenceGenerator(name = "seq_situacao_funcional", sequenceName = "e_pontows.seq_situacao_funcional",
        initialValue = 1, allocationSize = 1)
public class SituacaoFuncional extends EntidadeBase implements SesaModel {
    
    private static final long serialVersionUID = 1057213663565641469L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_situacao_funcional")
    @Basic(optional = false)
    @Column(name = "situacao_funcional_pk")
    private BigInteger id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "descricao")
    private String descricao;


    public SituacaoFuncional() {
    }

    public SituacaoFuncional(BigInteger situacaoFuncionalPk) {
        this.id = situacaoFuncionalPk;
    }

    public SituacaoFuncional(BigInteger situacaoFuncionalPk, String descricao) {
        this.id = situacaoFuncionalPk;
        this.descricao = descricao;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacaoFuncional)) {
            return false;
        }
        SituacaoFuncional other = (SituacaoFuncional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.SituacaoFuncional[ situacaoFuncionalPk=" + id + " ]";
    }

  }
