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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_unidade_organica_item", schema = "e_pontows")
@SequenceGenerator(name = "seq_unidade_organica_item", sequenceName = "e_pontows.seq_unidade_organica_item",
        initialValue = 1, allocationSize = 1)
public class UnidadeOrganicaItem extends EntidadeBase implements SesaModel {
    private static final long serialVersionUID = -7384550274945997636L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unidade_organica_item_pk")
    private BigInteger id;
    
    @JoinColumn(name = "organograma_fk", referencedColumnName = "organograma_pk")
    @ManyToOne(optional = false)
    private Organograma organogramaFk;
    
    @JsonIgnore
    @JoinColumn(name = "unidade_organica_fk", referencedColumnName = "unidade_organica_pk")
    @ManyToOne(optional = false)
    private UnidadeOrganica unidadeOrganica;

    public UnidadeOrganicaItem() {
    }

    public UnidadeOrganicaItem(BigInteger unidadeOrganicaItemPk) {
        this.id = unidadeOrganicaItemPk;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Organograma getOrganogramaFk() {
        return organogramaFk;
    }

    public void setOrganogramaFk(Organograma organogramaFk) {
        this.organogramaFk = organogramaFk;
    }


    public UnidadeOrganica getUnidadeOrganica() {
        return unidadeOrganica;
    }

    public void setUnidadeOrganica(UnidadeOrganica unidadeOrganica) {
        this.unidadeOrganica = unidadeOrganica;
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
        if (!(object instanceof UnidadeOrganicaItem)) {
            return false;
        }
        UnidadeOrganicaItem other = (UnidadeOrganicaItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.UnidadeOrganicaItem[ unidadeOrganicaItemPk=" + id + " ]";
    }
    
}
