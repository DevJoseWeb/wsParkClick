/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.util.JsonDateDeserializerbr;
import br.gov.ce.saude.ponto.util.JsonDateSerializerbr;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
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
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_afastamento", schema = "e_pontows")
@SequenceGenerator(name = "seq_afastamento", sequenceName = "e_pontows.seq_afastamento",
        initialValue = 1, allocationSize = 1)
public class Afastamento extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = 553568256927617238L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_afastamento")
    @Column(name = "afastamento_pk")
    private BigInteger id;

    
    @NotNull
    @JoinColumn(name = "funcionario_contrato_fk", referencedColumnName = "funcionario_contrato_pk")
    @ManyToOne
    private FuncionarioContrato funcionarioContratoFk;

    @NotNull
    @JoinColumn(name = "ocorrencia_fk", referencedColumnName = "ocorrencia_pk")
    @ManyToOne(optional = false)
    private Ocorrencia ocorrenciaFk;

    @Column(name = "ativo")
    private Boolean ativo;

    @NotNull
    @Column(name = "data_inicial")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializerbr.class)
    @JsonDeserialize(using = JsonDateDeserializerbr.class)
    private Date dataInicial;

    @Column(name = "data_final")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializerbr.class)
    @JsonDeserialize(using = JsonDateDeserializerbr.class)
    private Date dataFinal;

    public Afastamento() {
    }

    public Afastamento(BigInteger id, Ocorrencia ocorrenciaFk, Boolean ativo,
            Date dataInicial, Date dataFinal) {
        this.id = id;
        this.ocorrenciaFk = ocorrenciaFk;
        this.ativo = ativo;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Ocorrencia getOcorrenciaFk() {
        return ocorrenciaFk;
    }

    public void setOcorrenciaFk(Ocorrencia ocorrenciaFk) {
        this.ocorrenciaFk = ocorrenciaFk;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public FuncionarioContrato getFuncionarioContratoFk() {
        return funcionarioContratoFk;
    }

    public void setFuncionarioContratoFk(FuncionarioContrato funcionarioContratoFk) {
        this.funcionarioContratoFk = funcionarioContratoFk;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Afastamento other = (Afastamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Funcao[ id=" + id + " ]";
    }
}
