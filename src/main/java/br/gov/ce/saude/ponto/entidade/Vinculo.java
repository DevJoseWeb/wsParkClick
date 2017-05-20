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
import org.hibernate.envers.Audited;

/**
 *
 * @author esmayktillesse
 */
@Entity
//@Audited
@Table(name = "t_vinculo", schema = "e_pontows")
@SequenceGenerator(name = "seq_vinculo", sequenceName = "e_pontows.seq_vinculo",
        initialValue = 1, allocationSize = 1)
@XmlRootElement
public class Vinculo extends EntidadeBase implements SesaModel {
    
    private static final long serialVersionUID = -8156026534526752435L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_vinculo")
    @Basic(optional = false)
    @Column(name = "vinculo_pk")
    private BigInteger id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "descricao")
    private String descricao;


    public Vinculo() {
    }

    public Vinculo(BigInteger id) {
        this.id = id;
    }

    public Vinculo(BigInteger id, String descricao) {
        this.id = id;
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
        if (!(object instanceof Vinculo)) {
            return false;
        }
        Vinculo other = (Vinculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Vinculo[ id=" + id + " ]";
    }

}
