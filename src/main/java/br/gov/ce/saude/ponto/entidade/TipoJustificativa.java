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

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_justificativa")
@SequenceGenerator(name = "seq_justificativa", sequenceName = "e_pontows.seq_justificativa",
        initialValue = 1, allocationSize = 1)
public class TipoJustificativa extends EntidadeBase implements SesaModel {
    
    private static final long serialVersionUID = -4921418056798152200L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_justificativa")
    @Basic(optional = false)
    @Column(name = "justificativa_pk")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "seq_justif")
    private Integer seqJustif;

    public TipoJustificativa() {
    }

    public TipoJustificativa(BigInteger justificativaPk) {
        this.id = justificativaPk;
    }

    public TipoJustificativa(BigInteger justificativaPk, String descricao) {
        this.id = justificativaPk;
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

    public Integer getSeqJustif() {
        return seqJustif;
    }

    public void setSeqJustif(Integer seqJustif) {
        this.seqJustif = seqJustif;
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
        if (!(object instanceof TipoJustificativa)) {
            return false;
        }
        TipoJustificativa other = (TipoJustificativa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.TipoJustificativa[ justificativaPk=" + id + " ]";
    }
    
}
