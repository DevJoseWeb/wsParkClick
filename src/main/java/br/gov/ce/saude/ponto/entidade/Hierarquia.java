/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.vo.HierarquiaVO;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_hierarquia", schema = "e_pontows")
@SequenceGenerator(name = "seq_hierarquia", sequenceName = "e_pontows.seq_hierarquia",
        initialValue = 1, allocationSize = 1)
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "hierarquiaVO",
            classes = {
                @ConstructorResult(
                        targetClass = HierarquiaVO.class,
                        columns = {
                            @ColumnResult(name = "hierarquia_pk"),
                            @ColumnResult(name = "organograma_pk"),
                            @ColumnResult(name = "sigla"),
                            @ColumnResult(name = "nome_org"),
                            @ColumnResult(name = "tipo")
                        }
                )
            }
    )
})
public class Hierarquia extends EntidadeBase implements SesaModel {
    private static final long serialVersionUID = 7505480463210178939L;
           
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_hierarquia")
    @Basic(optional = false)
    @Column(name = "hierarquia_pk")
    private BigInteger id;
        
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;
    
    @Column(name = "tipo")
    private Character tipo;
    
    @JsonIgnore
    @JoinColumn(name = "organograma_fk", referencedColumnName = "organograma_pk")
    @ManyToOne(optional = false)
    private Organograma organogramaFk;
        
    @JsonIgnore
    @JoinColumn(name = "funcionario_contrato_fk", referencedColumnName = "funcionario_contrato_pk")
    @ManyToOne(optional = false)
    private FuncionarioContrato funcionarioContrato;
    
    @Transient
    private Integer idOrganograma;
    
    @Transient
    private BigInteger idFuncionarioContrato;
    
    @Transient
    private BigInteger idUsuario;
    

    public Hierarquia() {
    }

    public Hierarquia(BigInteger hierarquiaPk) {
        this.id = hierarquiaPk;
    }

    public Hierarquia(BigInteger hierarquiaPk, Boolean ativo) {
        this.id = hierarquiaPk;        
        this.ativo = ativo;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public FuncionarioContrato getFuncionarioContrato() {
        return funcionarioContrato;
    }

    public void setFuncionarioContrato(FuncionarioContrato funcionarioContrato) {
        this.funcionarioContrato = funcionarioContrato;
    }

    public Organograma getOrganogramaFk() {
        return organogramaFk;
    }

    public void setOrganogramaFk(Organograma organogramaFk) {
        this.organogramaFk = organogramaFk;
    }

    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.ativo);
        hash = 79 * hash + Objects.hashCode(this.tipo);
        hash = 79 * hash + Objects.hashCode(this.organogramaFk);
        hash = 79 * hash + Objects.hashCode(this.funcionarioContrato);
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
        final Hierarquia other = (Hierarquia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ativo, other.ativo)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.organogramaFk, other.organogramaFk)) {
            return false;
        }
        if (!Objects.equals(this.funcionarioContrato, other.funcionarioContrato)) {
            return false;
        }
        return true;
    }

    public Integer getIdOrganograma() {
        return idOrganograma;
    }

    public void setIdOrganograma(Integer idOrganograma) {
        this.idOrganograma = idOrganograma;
    }

    public BigInteger getIdFuncionarioContrato() {
        return idFuncionarioContrato;
    }

    public void setIdFuncionarioContrato(BigInteger idFuncionarioContrato) {
        this.idFuncionarioContrato = idFuncionarioContrato;
    }
    
    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Hierarquia[ hierarquiaPk=" + id + " ]";
    }
    
}
