/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_feriado", schema = "e_pontows")
@XmlRootElement
@SequenceGenerator(name = "seq_feriado", sequenceName = "e_pontows.seq_feriado",
        initialValue = 1, allocationSize = 1)
public class Feriado extends EntidadeBase implements SesaModel  {
    
    private static final long serialVersionUID = -1584986409671972360L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_feriado")
    @Column(name = "feriado_pk")
    private BigInteger id;
    
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "descricao")
    private String descricao;
    
    @NotNull
    @Column(name = "tipo")
    private Character tipo;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "feriadoFk")
    private List<FeriadoItem> feriadoItemList;

    public Feriado() {
    }

    public Feriado(BigInteger feriadoPk) {
        this.id = feriadoPk;
    }

    public Feriado(BigInteger feriadoPk, String descricao, Character tipo) {
        this.id = feriadoPk;
        this.descricao = descricao;
        this.tipo = tipo;
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

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    //@JsonIgnore
    public List<FeriadoItem> getFeriadoItemList() {
        return feriadoItemList;
    }

    public void setFeriadoItemList(List<FeriadoItem> feriadoItemList) {
        this.feriadoItemList = feriadoItemList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Feriado other = (Feriado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Feriado[ feriadoPk=" + id + " ]";
    }

}
