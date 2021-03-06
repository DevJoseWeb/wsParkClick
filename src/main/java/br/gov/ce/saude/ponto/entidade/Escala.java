/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.vo.SemEscalaPontoVO;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_escala")
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "relatorioPessoassemEscala",
            classes = {
                @ConstructorResult(
                        targetClass = SemEscalaPontoVO.class,
                        columns = {
                            @ColumnResult(name = "matricula"),
                            @ColumnResult(name = "nome"),
                            @ColumnResult(name = "unidade"),
                            @ColumnResult(name = "setor"),
                            @ColumnResult(name = "vinculo")
                        }
                )
            }
    )

})
public class Escala extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "escala_pk")
    private BigInteger id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private Character tipo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "formato")
    private Character formato;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false, mappedBy = "escala")
    @BatchSize(size = 50)
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "escala")
    private Collection<EscalaItem> escalaItemCollection;

    public Escala() {
    }

    public Escala(BigInteger escalaPk) {
        this.id = escalaPk;
    }

    public Escala(BigInteger escalaPk, Character tipo, String descricao) {
        this.id = escalaPk;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getFormato() {
        return formato;
    }

    public void setFormato(Character formato) {
        this.formato = formato;
    }

    public Collection<EscalaItem> getEscalaItemCollection() {
        return escalaItemCollection;
    }

    public void setEscalaItemCollection(Collection<EscalaItem> escalaItemCollection) {
        this.escalaItemCollection = escalaItemCollection;
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
        if (!(object instanceof Escala)) {
            return false;
        }
        Escala other = (Escala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Escala[ escalaPk=" + id + " ]";
    }

}
