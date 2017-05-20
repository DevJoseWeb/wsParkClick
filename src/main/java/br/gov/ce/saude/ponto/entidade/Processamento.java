/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jcfbandeira
 */
@Entity
@Table(name = "t_processamento", schema = "e_pontows")
@SequenceGenerator(name = "seq_processamento", sequenceName = "e_pontows.seq_processamento",
        initialValue = 1, allocationSize = 1)
@XmlRootElement
public class Processamento extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = 3702727401237169438L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_processamento")
    @Column(name = "processamento_pk")
    private BigInteger id;

    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @NotNull
    @Column(name = "periodo_inicial")
    @Temporal(TemporalType.DATE)
    private Date periodoInicial;

    @NotNull
    @Column(name = "situacao")
    private Character situacao;

    @NotNull
    @Column(name = "periodo_final")
    @Temporal(TemporalType.DATE)
    private Date periodoFinal;

    @NotNull
    @Column(name = "tipo")
    private Character tipo;

    @NotNull
    @JoinColumn(name = "organograma_fk", referencedColumnName = "organograma_pk")
    @ManyToOne
    private Organograma organogramaFk;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "processamentoFk")
    private Collection<PontoProcessado> pontoProcessadoCollection;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "processamentoFk")
    private Collection<LogProcessamento> logProcessamentoCollection;

    public Processamento() {
    }

    public Processamento(BigInteger id) {
        this.id = id;
    }

    public Processamento(BigInteger id, Date data, Date periodoInicial, Character situacao,
            Date periodoFinal, Character tipo, Organograma organogramaFk) {
        this.id = id;
        this.data = data;
        this.periodoInicial = periodoInicial;
        this.situacao = situacao;
        this.periodoFinal = periodoFinal;
        this.tipo = tipo;
        this.organogramaFk = organogramaFk;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Character getSituacao() {
        return situacao;
    }

    public void setSituacao(Character situacao) {
        this.situacao = situacao;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Organograma getOrganogramaFk() {
        return organogramaFk;
    }

    public void setOrganogramaFk(Organograma organogramaFk) {
        this.organogramaFk = organogramaFk;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PontoProcessado> getPontoProcessadoCollection() {
        return pontoProcessadoCollection;
    }

    public void setPontoProcessadoCollection(Collection<PontoProcessado> pontoProcessadoCollection) {
        this.pontoProcessadoCollection = pontoProcessadoCollection;
    }

    public Collection<LogProcessamento> getLogProcessamentoCollection() {
        return logProcessamentoCollection;
    }

    public void setLogProcessamentoCollection(Collection<LogProcessamento> logProcessamentoCollection) {
        this.logProcessamentoCollection = logProcessamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Processamento other = (Processamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Processamento[ id=" + id + " ]";
    }

}
