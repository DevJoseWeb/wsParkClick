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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jcfbandeira 
 */
@Entity
@Table(name = "t_log_processamento", schema = "e_pontows")
@SequenceGenerator(name = "seq_log_processamento", sequenceName = "e_pontows.seq_log_processamento",
        initialValue = 1, allocationSize = 1)
public class LogProcessamento extends EntidadeBase implements SesaModel {
    private static final long serialVersionUID = 5923612178159234205L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_log_processamento")
    @Basic(optional = false)
    @Column(name = "log_processamento_pk")
    private BigInteger id;

    @NotNull
    @JoinColumn(name = "processamento_fk", referencedColumnName = "processamento_pk")
    @ManyToOne
    private Processamento processamentoFk;
    
    @JoinColumn(name = "batida_rel_fk", referencedColumnName = "batida_rel_pk")
    @ManyToOne
    private BatidaRel batidaRelFk;
    
    @JoinColumn(name = "batida_rel_escala_fk", referencedColumnName = "batida_rel_escala_pk")
    @ManyToOne
    private BatidaEscalaRel batidaEscalaRelFk;

    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "log")
    private String log;

    public LogProcessamento() {
    }

    public LogProcessamento(BigInteger id, String log) {
        this.id = id;
        this.log = log;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Processamento getProcessamentoFk() {
        return processamentoFk;
    }

    public void setProcessamentoFk(Processamento processamentoFk) {
        this.processamentoFk = processamentoFk;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public BatidaRel getBatidaRelFk() {
        return batidaRelFk;
    }

    public void setBatidaRelFk(BatidaRel batidaRelFk) {
        this.batidaRelFk = batidaRelFk;
    }

    public BatidaEscalaRel getBatidaEscalaRelFk() {
        return batidaEscalaRelFk;
    }

    public void setBatidaEscalaRelFk(BatidaEscalaRel batidaEscalaRelFk) {
        this.batidaEscalaRelFk = batidaEscalaRelFk;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final LogProcessamento other = (LogProcessamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.LogProcessamento[ id=" + id + " ]";
    }
}
