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

/**
 *
 * @author jcfbandeira
 */
@Entity
@Table(name = "v_batidas_mes", schema = "e_base")
public class BatidaMes implements SesaModel{
    private static final long serialVersionUID = -8459513978874153007L;
    
    @Id
    @Column(name = "id")
    private BigInteger id;
    
    @Column(name = "batida_rel_pk")
    private BigInteger batidaRelPk;
    
    @Column(name = "batida_rel_escala_pk")
    private BigInteger batidaRelEscalaPk;
    
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @Column(name = "batida_1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batida1;
    
    @Column(name = "batida_2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batida2;
    
    @Column(name = "batida_3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batida3;
    
    @Column(name = "batida_4")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batida4;
    
    @Column(name = "funcionario_horario_fk")
    private BigInteger funcionarioHorarioFk;
    
    @Column(name = "escala_item_fk")
    private BigInteger escalaItemFk;
    
    @Column(name = "unidade_organica")
    private BigInteger unidadeOrganica;

    public BatidaMes() {
    }

    public BigInteger getBatidaRelPk() {
        return batidaRelPk;
    }

    public void setBatidaRelPk(BigInteger batidaRelPk) {
        this.batidaRelPk = batidaRelPk;
    }

    public BigInteger getBatidaRelEscalaPk() {
        return batidaRelEscalaPk;
    }

    public void setBatidaRelEscalaPk(BigInteger batidaRelEscalaPk) {
        this.batidaRelEscalaPk = batidaRelEscalaPk;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getBatida1() {
        return batida1;
    }

    public void setBatida1(Date batida1) {
        this.batida1 = batida1;
    }

    public Date getBatida2() {
        return batida2;
    }

    public void setBatida2(Date batida2) {
        this.batida2 = batida2;
    }

    public Date getBatida3() {
        return batida3;
    }

    public void setBatida3(Date batida3) {
        this.batida3 = batida3;
    }

    public Date getBatida4() {
        return batida4;
    }

    public void setBatida4(Date batida4) {
        this.batida4 = batida4;
    }

    public BigInteger getFuncionarioHorarioFk() {
        return funcionarioHorarioFk;
    }

    public void setFuncionarioHorarioFk(BigInteger funcionarioHorarioFk) {
        this.funcionarioHorarioFk = funcionarioHorarioFk;
    }

    public BigInteger getEscalaItemFk() {
        return escalaItemFk;
    }

    public void setEscalaItemFk(BigInteger escalaItemFk) {
        this.escalaItemFk = escalaItemFk;
    }

    public BigInteger getUnidadeOrganica() {
        return unidadeOrganica;
    }

    public void setUnidadeOrganica(BigInteger unidadeOrganica) {
        this.unidadeOrganica = unidadeOrganica;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final BatidaMes other = (BatidaMes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
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
