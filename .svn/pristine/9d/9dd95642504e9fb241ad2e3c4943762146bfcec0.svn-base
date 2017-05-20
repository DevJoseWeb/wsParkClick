/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.vo.HorarioItemVO;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_horario", schema = "e_pontows")
@SequenceGenerator(name = "seq_horario", sequenceName = "e_pontows.seq_horario",
        initialValue = 1, allocationSize = 1)
@XmlRootElement
public class Horario extends EntidadeBase implements SesaModel {
   
    private static final long serialVersionUID = 1005807376815831584L;

   

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_horario")
    @Basic(optional = false)
    @Column(name = "horario_pk")
    private BigInteger id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;

    @Column(name = "cod_horario")
    private Integer codHorario;

    @Column(name = "organograma_fk")
    private Integer organogramaFk;

    @Column(name = "turno")
    private Character turno;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "horarioFk")
    private List<HorarioItem> horarioItemCollection;
    
    //@JsonIgnore
    @Transient
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "horarioFk")
    private List<HorarioItemVO> horarioItemVoCollection;

    public List<HorarioItemVO> getHorarioItemVoCollection() {
        return horarioItemVoCollection;
    }

    public void setHorarioItemVoCollection(List<HorarioItemVO> horarioItemVoCollection) {
        this.horarioItemVoCollection = horarioItemVoCollection;
    }
    
    public Horario() {
    }

    public Horario(BigInteger id) {
        this.id = id;
    }

    public Horario(BigInteger id, String descricao, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = ativo;
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

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(Integer codHorario) {
        this.codHorario = codHorario;
    }

    @XmlTransient
    public List<HorarioItem> getHorarioItemCollection() {
        return horarioItemCollection;
    }

    public void setHorarioItemCollection(List<HorarioItem> horarioItemCollection) {
        this.horarioItemCollection = horarioItemCollection;
    }

    public Integer getOrganogramaFk() {
        return organogramaFk;
    }

    public void setOrganogramaFk(Integer organogramaFk) {
        this.organogramaFk = organogramaFk;
    }

    public Character getTurno() {
        return turno;
    }

    public void setTurno(Character turno) {
        this.turno = turno;
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
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Horario[ horarioPk=" + id + " ]";
    }

}
