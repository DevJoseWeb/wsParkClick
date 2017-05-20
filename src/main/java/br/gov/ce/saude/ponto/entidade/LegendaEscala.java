/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.util.JsonTimeDeserializer;
import br.gov.ce.saude.ponto.util.JsonTimeSerializer;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author jcfbandeira
 */
@Entity
@Table(name = "t_legenda_escala", schema = "e_pontows")
@SequenceGenerator(name = "seq_legenda_escala", sequenceName = "e_pontows.seq_legenda_escala",
        initialValue = 1, allocationSize = 1)
public class LegendaEscala extends EntidadeBase implements SesaModel {

    

    private static final long serialVersionUID = -1804044535450368757L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_legenda_escala")
    @Column(name = "legenda_escala_pk")
    private BigInteger id;

    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "sigla")
    private String sigla;

    @Column(name = "entrada")
    @Temporal(TemporalType.TIME)
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    private Date entrada;

    @Column(name = "saida")
    @Temporal(TemporalType.TIME)
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    private Date saida;
    
    @NotNull
    @JoinColumn(name = "organograma_fk", referencedColumnName = "organograma_pk")
    @ManyToOne(optional = false, cascade = { CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Organograma organogramaFk;

    public LegendaEscala() {
    }

    public LegendaEscala(BigInteger id) {
        this.id = id;
    }

    public LegendaEscala(BigInteger id, String sigla, Date entrada, Date saida, Organograma organogramaFk) {
        this.id = id;
        this.sigla = sigla;
        this.entrada = entrada;
        this.saida = saida;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    
    public Organograma getOrganogramaFk() {
        return organogramaFk;
    }

    public void setOrganogramaFk(Organograma organogramaFk) {
        this.organogramaFk = organogramaFk;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LegendaEscala other = (LegendaEscala) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.LegendaEscala[ id=" + id + " ]";
    }

}
