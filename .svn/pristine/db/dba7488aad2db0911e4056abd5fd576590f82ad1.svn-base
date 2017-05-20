/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author jcfbandeira
 */
@Entity
@Table(name = "v_feriado_mes", schema = "e_pontows")
public class FeriadoMes implements SesaModel {
    private static final long serialVersionUID = 8111724613395051146L;
 
    @Transient
    private BigInteger id;
    
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    @Id
    private Date data;

    @Size(max = 80)
    @Column(name = "feriado")
    private String feriado;

    public FeriadoMes() {
    }

    public String getFeriado() {
        return feriado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.data);
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
        final FeriadoMes other = (FeriadoMes) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }


    public void setFeriado(String feriado) {
        this.feriado = feriado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

}
