/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_funcionario_horario", schema = "e_pontows")
@SequenceGenerator(name = "seq_funcionario_horario", sequenceName = "e_pontows.seq_funcionario_horario",
        initialValue = 1, allocationSize = 1)
public class FuncionarioHorario extends EntidadeBase implements SesaModel {
    
    private static final long serialVersionUID = 921741324188110116L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_funcionario_horario")
    @Basic(optional = false)
    @Column(name = "funcionario_horario_pk")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;
        
    @JoinColumn(name = "horario_fk", referencedColumnName = "horario_pk")
    @ManyToOne
    private Horario horario;
    
    @JsonIgnore
    @JoinColumn(name = "funcionario_contrato_fk", referencedColumnName = "funcionario_contrato_pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FuncionarioContrato funcionarioContrato;

    @JoinColumn(name = "regime_fk", referencedColumnName = "regime_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private  Regime regimeFk;
    
    @Transient
    private BigInteger horarioFk;    
    @Transient
    private BigInteger funcionarioContratoFk;
    
    @Transient
    private Boolean diarista;
    @Transient
    private Boolean plantonista;
    @Transient
    private Boolean horaExtra;
    
    
    public FuncionarioHorario() {
    }

    public Regime getRegimeFk() {
        return regimeFk;
    }

    public void setRegimeFk(Regime regimeFk) {
        this.regimeFk = regimeFk;
    }
    
    public BigInteger getHorarioFk() {
        return horarioFk;
    }

    public void setHorarioFk(BigInteger horarioFk) {
        this.horarioFk = horarioFk;
    }

    public BigInteger getFuncionarioContratoFk() {
        return funcionarioContratoFk;
    }

    public void setFuncionarioContratoFk(BigInteger funcionarioContratoFk) {
        this.funcionarioContratoFk = funcionarioContratoFk;
    }
    
    

    public FuncionarioHorario(BigInteger funcionarioHorarioPk) {
        this.id = funcionarioHorarioPk;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    public FuncionarioHorario(BigInteger funcionarioHorarioPk, Boolean ativo) {
        this.id = funcionarioHorarioPk;
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

    public FuncionarioContrato getFuncionarioContrato() {
        return funcionarioContrato;
    }

    public void setFuncionarioContrato(FuncionarioContrato funcionarioContrato) {
        this.funcionarioContrato = funcionarioContrato;
    }

    public Boolean getDiarista() {
        return diarista;
    }

    public void setDiarista(Boolean diarista) {
        this.diarista = diarista;
    }

    public Boolean getPlantonista() {
        return plantonista;
    }

    public void setPlantonista(Boolean plantonista) {
        this.plantonista = plantonista;
    }

    public Boolean getHoraExtra() {
        return horaExtra;
    }

    public void setHoraExtra(Boolean horaExtra) {
        this.horaExtra = horaExtra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.ativo);
        hash = 97 * hash + Objects.hashCode(this.horario);
        hash = 97 * hash + Objects.hashCode(this.funcionarioContrato);
        hash = 97 * hash + Objects.hashCode(this.regimeFk);
        hash = 97 * hash + Objects.hashCode(this.horarioFk);
        hash = 97 * hash + Objects.hashCode(this.funcionarioContratoFk);
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
        final FuncionarioHorario other = (FuncionarioHorario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ativo, other.ativo)) {
            return false;
        }
        if (!Objects.equals(this.horario, other.horario)) {
            return false;
        }
        if (!Objects.equals(this.funcionarioContrato, other.funcionarioContrato)) {
            return false;
        }
        if (!Objects.equals(this.regimeFk, other.regimeFk)) {
            return false;
        }
        if (!Objects.equals(this.horarioFk, other.horarioFk)) {
            return false;
        }
        if (!Objects.equals(this.funcionarioContratoFk, other.funcionarioContratoFk)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.FuncionarioHorario[ funcionarioHorarioPk=" + id + " ]";
    }
    
}
