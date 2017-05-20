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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_funcao", schema = "e_pontows")
@SequenceGenerator(name = "seq_funcao", sequenceName = "e_pontows.seq_funcao",
        initialValue = 1, allocationSize = 1)
public class Funcao extends EntidadeBase implements SesaModel{
   
    private static final long serialVersionUID = 8033629179766299862L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_funcao")
    @Basic(optional = false)
    @Column(name = "funcao_pk")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "descricao")
    private String descricao;

    public Funcao() {
    }

    
    public Funcao(BigInteger id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Funcao other = (Funcao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
@Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Funcao[ funcaoPk=" + id + " ]";
    } 
}
