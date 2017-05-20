/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.util.JsonDateDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateSerializer;
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
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_feriado_item", schema = "e_pontows")
@XmlRootElement
@SequenceGenerator(name = "seq_horario_item", sequenceName = "e_pontows.seq_horario_item",
        initialValue = 1, allocationSize = 1)
public class FeriadoItem extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = 3173060346258531999L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_horario_item")
    @Column(name = "feriado_item_pk")
    private BigInteger id;

    @NotNull
    @Column(name = "data")
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Temporal(TemporalType.DATE)
    private Date data;

    @JsonIgnore
    @JoinColumn(name = "feriado_fk", referencedColumnName = "feriado_pk")
    @ManyToOne(optional = false)
    private Feriado feriadoFk;

    public FeriadoItem() {
    }

    public FeriadoItem(BigInteger feriadoItemPk) {
        this.id = feriadoItemPk;
    }

    public FeriadoItem(BigInteger feriadoItemPk, Date data) {
        this.id = feriadoItemPk;
        this.data = data;
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

    public Feriado getFeriadoFk() {
        return feriadoFk;
    }

    public void setFeriadoFk(Feriado feriadoFk) {
        this.feriadoFk = feriadoFk;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final FeriadoItem other = (FeriadoItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.FeriadoItem[ feriadoItemPk=" + id + " ]";
    }

}
