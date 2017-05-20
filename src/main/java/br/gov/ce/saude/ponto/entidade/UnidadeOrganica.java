/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_unidade_organica", schema = "e_pontows")
@SequenceGenerator(name = "seq_unidade_organica", sequenceName = "e_pontows.seq_unidade_organica",
        initialValue = 1, allocationSize = 1)
public class UnidadeOrganica extends EntidadeBase implements SesaModel {
    private static final long serialVersionUID = -7638459193791403558L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_unidade_organica")
    @Basic(optional = false)
    @Column(name = "unidade_organica_pk")
    private BigInteger id;
    
    @JoinColumn(name = "organograma_fk", referencedColumnName = "organograma_pk")
    @ManyToOne(optional = false)
    private Organograma organogramaFk;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeOrganica")
    private Collection<UnidadeOrganicaItem> unidadeOrganicaItemCollection;

    public UnidadeOrganica() {
    }

    public UnidadeOrganica(BigInteger unidadeOrganicaPk) {
        this.id = unidadeOrganicaPk;
    }

   
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    
    public Collection<UnidadeOrganicaItem> getUnidadeOrganicaItemCollection() {
        return unidadeOrganicaItemCollection;
    }

    public void setUnidadeOrganicaItemCollection(Collection<UnidadeOrganicaItem> unidadeOrganicaItemCollection) {
        this.unidadeOrganicaItemCollection = unidadeOrganicaItemCollection;
    }

    public Organograma getOrganogramaFk() {
        return organogramaFk;
    }

    public void setOrganogramaFk(Organograma organogramaFk) {
        this.organogramaFk = organogramaFk;
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
        if (!(object instanceof UnidadeOrganica)) {
            return false;
        }
        UnidadeOrganica other = (UnidadeOrganica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.UnidadeOrganica[ unidadeOrganicaPk=" + id + " ]";
    }
    
}
