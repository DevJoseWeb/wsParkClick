package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author esmayk.tillesse
 */
@Entity
@Table(name = "t_gerador", schema = "e_pontows")
@SequenceGenerator(name = "seq_gerador", sequenceName = "e_pontows.seq_gerador",
        initialValue = 1, allocationSize = 1)
@XmlRootElement
public class Gerador extends EntidadeBase implements SesaModel {
   
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gerador")
    @Basic(optional = false)
    @Column(name = "gerador_pk")
    private BigInteger id;

    @JsonIgnore
    @Transient
    @Column(name = "datasoli")
    @Temporal(TemporalType.DATE)
    private Calendar datasoli;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "unidade_fk")
    private Integer UnidadeFk;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "digito")
    private Integer digito;
    
    @Transient
    private String nomeFuncionario;
    
    @JsonIgnore
    @JoinColumn(name = "pessoa_fk", referencedColumnName = "pessoa_pk")
    @ManyToOne
    private Funcionario funcionarioFk;
    
    public Gerador() {
    }

    public Gerador(BigInteger id) {
        this.id = id;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Calendar getDatasoli() {
        return datasoli;
    }

    public void setDatasoli(Calendar datasoli) {
        this.datasoli = datasoli;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    
    public Integer getUnidadeFk() {
        return UnidadeFk;
    }

    public void setUnidadeFk(Integer UnidadeFk) {
        this.UnidadeFk = UnidadeFk;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getDigito() {
        return digito;
    }

    public void setDigito(Integer digito) {
        this.digito = digito;
    }


    @Override
    public String toString() {
        return "Id" + id;
    }

    public Funcionario getFuncionarioFk() {
        return funcionarioFk;
    }

    public void setFuncionarioFk(Funcionario funcionarioFk) {
        this.funcionarioFk = funcionarioFk;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.datasoli);
        hash = 29 * hash + Objects.hashCode(this.matricula);
        hash = 29 * hash + Objects.hashCode(this.quantidade);
        hash = 29 * hash + Objects.hashCode(this.digito);
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
        final Gerador other = (Gerador) obj;
        if (!Objects.equals(this.datasoli, other.datasoli)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.digito, other.digito)) {
            return false;
        }
        return true;
    }

   
}
