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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_grau_instrucao", schema = "e_base")
@XmlRootElement
@SequenceGenerator(name = "seq_grau_instrucao", sequenceName = "e_base.seq_grau_instrucao",
        initialValue = 1, allocationSize = 1)
public class GrauInstrucao extends EntidadeBase implements SesaModel {
    
    private static final long serialVersionUID = -225523874773721530L;

    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_grau_instrucao")
    @Basic(optional = false)
    @Column(name = "grau_instrucao_pk")
    private BigInteger id;

    @Size(max = 30)
    @Column(name = "descricao")
    private String descricao;

    public GrauInstrucao() {
    }

    public GrauInstrucao(BigInteger grauInstrucaoPk) {
        this.id = grauInstrucaoPk;
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
        if (!(object instanceof GrauInstrucao)) {
            return false;
        }
        GrauInstrucao other = (GrauInstrucao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.GrauInstrucao[ grauInstrucaoPk=" + id + " ]";
    }

}
