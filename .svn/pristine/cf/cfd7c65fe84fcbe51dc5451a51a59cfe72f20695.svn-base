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
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcfbandeira
 */
@Entity
@Table(name = "t_ponto_processado", schema = "e_pontows")
@SequenceGenerator(name = "seq_ponto_processado", sequenceName = "e_pontows.seq_ponto_processado",
        initialValue = 1, allocationSize = 1)
@XmlRootElement
public class PontoProcessado extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = -2806402442220033845L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ponto_processado")
    @Column(name = "ponto_processado_pk")
    private BigInteger id;

    @NotNull
    @Column(name = "situacao")
    private Character situacao;

    @JoinColumn(name = "batida_rel_fk", referencedColumnName = "batida_rel_pk")
    @ManyToOne(cascade = CascadeType.ALL)
    private BatidaRel batidaRelFk;

    @JoinColumn(name = "justificativa_fk", referencedColumnName = "justificativa_pk")
    @ManyToOne
    private TipoJustificativa justificativaFk;

    @NotNull
    @JoinColumn(name = "ocorrencia_fk", referencedColumnName = "ocorrencia_pk")
    @ManyToOne
    private Ocorrencia ocorrenciaFk;

    @NotNull
    @JoinColumn(name = "processamento_fk", referencedColumnName = "processamento_pk")
    @ManyToOne
    private Processamento processamentoFk;

    @JoinColumn(name = "batida_rel_escala_fk", referencedColumnName = "batida_rel_escala_pk")
    @ManyToOne(cascade = CascadeType.ALL)
    private BatidaEscalaRel batidaRelEscalaFk;
    
    public PontoProcessado() {
    }

    public PontoProcessado(BigInteger pontoProcessadoPk) {
        this.id = pontoProcessadoPk;
    }

    public PontoProcessado(BigInteger pontoProcessadoPk, Character situacao) {
        this.id = pontoProcessadoPk;
        this.situacao = situacao;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Character getSituacao() {
        return situacao;
    }

    public void setSituacao(Character situacao) {
        this.situacao = situacao;
    }

    public BatidaRel getBatidaRelFk() {
        return batidaRelFk;
    }

    public void setBatidaRelFk(BatidaRel batidaRelFk) {
        this.batidaRelFk = batidaRelFk;
    }

    public TipoJustificativa getJustificativaFk() {
        return justificativaFk;
    }

    public void setJustificativaFk(TipoJustificativa justificativaFk) {
        this.justificativaFk = justificativaFk;
    }

    public Ocorrencia getOcorrenciaFk() {
        return ocorrenciaFk;
    }

    public void setOcorrenciaFk(Ocorrencia ocorrenciaFk) {
        this.ocorrenciaFk = ocorrenciaFk;
    }

    public Processamento getProcessamentoFk() {
        return processamentoFk;
    }

    public void setProcessamentoFk(Processamento processamentoFk) {
        this.processamentoFk = processamentoFk;
    }

    public BatidaEscalaRel getBatidaRelEscalaFk() {
        return batidaRelEscalaFk;
    }

    public void setBatidaRelEscalaFk(BatidaEscalaRel batidaRelEscalaFk) {
        this.batidaRelEscalaFk = batidaRelEscalaFk;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final PontoProcessado other = (PontoProcessado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.PontoProcessado[ id=" + id + " ]";
    }

}
