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
import org.hibernate.annotations.Immutable;

/**
 *
 * @author jcfbandeira
 */
@Entity
@Table(name = "v_func_nao_bateu", schema = "e_base")
@Immutable
public class HorarioFuncNaoBateu implements SesaModel {
    private static final long serialVersionUID = 4966563679217902085L;
    
    @Id
    @Column(name = "id")
    private BigInteger id;
    
    @Column(name = "funcionario_horario")
    private BigInteger funcionarioHorario;
    
    @Column(name = "regime")
    private BigInteger regime;
    
    @Column(name = "escala_item")
    private BigInteger escalaItem;
    
    @Column(name = "entrada_1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada1;
    
    @Column(name = "saida_1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida1;
    
    @Column(name = "entrada_2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada2;
    
    @Column(name = "saida_2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida2;

    public HorarioFuncNaoBateu() {
    }

    public BigInteger getFuncionarioHorario() {
        return funcionarioHorario;
    }

    public void setFuncionarioHorario(BigInteger funcionarioHorario) {
        this.funcionarioHorario = funcionarioHorario;
    }

    public BigInteger getRegime() {
        return regime;
    }

    public void setRegime(BigInteger regime) {
        this.regime = regime;
    }

    public BigInteger getEscalaItem() {
        return escalaItem;
    }

    public void setEscalaItem(BigInteger escalaItem) {
        this.escalaItem = escalaItem;
    }

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

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final HorarioFuncNaoBateu other = (HorarioFuncNaoBateu) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.HorarioFuncNaoBateu[ id=" + id + " ]";
    } 
}
