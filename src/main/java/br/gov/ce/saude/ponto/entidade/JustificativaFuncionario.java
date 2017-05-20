/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_justificativa_funcionario", schema = "e_pontows")
@XmlRootElement
@SequenceGenerator(name = "seq_justificativa_funcionario", sequenceName = "e_pontows.seq_justificativa_funcionario", 
        initialValue = 1, allocationSize = 1)
public class JustificativaFuncionario extends EntidadeBase implements SesaModel {
    
    private static final long serialVersionUID = 7629303287199718294L;
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_justificativa_funcionario")        
    @Column(name = "justificativa_funcionario_pk")
    private BigInteger id;
    
    @Size(max = 50)
    @Column(name = "mimetype")
    private String mimetype;
    
    @Size(max = 200)
    @Column(name = "imagem")
    private String imagem;
        
    @JsonIgnore
    @JoinColumn(name = "batida_rel_fk", referencedColumnName = "batida_rel_pk")   
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private BatidaRel batidarelFk;

    @JsonIgnore
    @JoinColumn(name = "batida_rel_escala_fk", referencedColumnName = "batida_rel_escala_pk")   
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private BatidaEscalaRel batidarelEscalaFk;
    
    public JustificativaFuncionario() {
    }

    public JustificativaFuncionario(BigInteger justificativaBatidarelPk) {
        this.id = justificativaBatidarelPk;
    }

    public BatidaEscalaRel getBatidarelEscalaFk() {
        return batidarelEscalaFk;
    }

    public void setBatidarelEscalaFk(BatidaEscalaRel batidarelEscalaFk) {
        this.batidarelEscalaFk = batidarelEscalaFk;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public BatidaRel getBatidarelFk() {
        return batidarelFk;
    }

    public void setBatidarelFk(BatidaRel batidarelFk) {
        this.batidarelFk = batidarelFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JustificativaFuncionario)) {
            return false;
        }
        JustificativaFuncionario other = (JustificativaFuncionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.JustificativaBatidarel[ justificativaBatidarelPk=" + id + " ]";
    }
    
}
