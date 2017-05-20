/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_horario_item", schema = "e_pontows")
@XmlRootElement
@SequenceGenerator(name = "seq_horario_item", sequenceName = "e_pontows.seq_horario_item",
        initialValue = 1, allocationSize = 1)

public class HorarioItem extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = 7710794748308375329L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_horario_item")
    @Basic(optional = false)
    @Column(name = "horario_item_pk")
    private BigInteger id;

    @Column(name = "dia_semana")
    private Short diaSemana;

    //@JsonSerialize(using = JsonDateTimeSerializer.class)
    @Column(name = "entrada_1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada1;
    
    //@JsonSerialize(using = JsonDateTimeSerializer.class)
    @Column(name = "saida_1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida1;
    
    //@JsonSerialize(using = JsonDateTimeSerializer.class)
    @Column(name = "entrada_2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada2;
    
    //@JsonSerialize(using = JsonDateTimeSerializer.class)
    @Column(name = "saida_2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida2;

    @Column(name = "vira_dia")
    private Boolean viraDia;

    @Column(name = "cod_horario")
    private Integer codHorario;

    @JsonIgnore
    @JoinColumn(name = "horario_fk", referencedColumnName = "horario_pk")
    @ManyToOne(optional = false)
    private Horario horarioFk;

    public HorarioItem() {
    }

    public HorarioItem(BigInteger horarioItemPk) {
        this.id = horarioItemPk;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Short getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Short diaSemana) {
        this.diaSemana = diaSemana;
    }

    //@JsonSerialize(using = JsonDateSerializer.class)
    public Date getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(Date entrada1) {
        this.entrada1 = entrada1;
    }

    public Date getSaida1() {
        return saida1;
    }

    public void setSaida1(Date saida1) {
        this.saida1 = saida1;
    }

    public Date getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(Date entrada2) {
        this.entrada2 = entrada2;
    }

    public Date getSaida2() {
        return saida2;
    }

    public void setSaida2(Date saida2) {
        this.saida2 = saida2;
    }

    public Boolean getViraDia() {
        return viraDia;
    }

    public void setViraDia(Boolean viraDia) {
        this.viraDia = viraDia;
    }

    public Integer getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(Integer codHorario) {
        this.codHorario = codHorario;
    }

    public Horario getHorarioFk() {
        return horarioFk;
    }

    public void setHorarioFk(Horario horarioFk) {
        this.horarioFk = horarioFk;
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
        if (!(object instanceof HorarioItem)) {
            return false;
        }
        HorarioItem other = (HorarioItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.HorarioItem[ horarioItemPk=" + id + " ]";
    }
}
