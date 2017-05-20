package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.util.JsonDateDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateSerializer;
import br.gov.ce.saude.ponto.util.JsonEntidadeFkSerializer;
import br.gov.ce.saude.ponto.vo.ValorHoraVO;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author jr
 */

@Entity
@Table(name = "t_valor_hora", schema = "e_pontows")
@SequenceGenerator(name = "seq_valor_hora", sequenceName = "e_pontows.seq_valor_hora",
        initialValue = 1, allocationSize = 1)
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "valorHora",
            classes = {
                @ConstructorResult(
                        targetClass = ValorHoraVO.class,
                        columns = {                            
                            @ColumnResult(name = "competencia"),
                            @ColumnResult(name = "valor_hora"),                            
                            @ColumnResult(name = "vencimento"),
                            @ColumnResult(name = "jornada_mensal"),
                            @ColumnResult(name = "jornada_semanal"),                            
                            @ColumnResult(name = "jornada_dias_uteis")                          
                        }
                )
            }
    )
})
@XmlRootElement
public class ValorHora extends EntidadeBase implements SesaModel {
    private static final long serialVersionUID = 1L;
    
  /*valor_hora_pk bigint NOT NULL DEFAULT nextval('seq_valor_hora'::regclass),
  funcionario_contrato_fk bigint NOT NULL,
  competencia date NOT NULL,
  vencimento numeric(12,2) NOT NULL,
  jornada_mensal smallint NOT NULL,
  jornada_semanal smallint NOT NULL,
  valor_hora numeric(6,4) NOT NULL,
  jornada_dias_uteis smallint DEFAULT 0, -- carga horaria x dias ulteis da competencia
  dias_uteis smallint DEFAULT 0, -- numero de dias uteis na competencia
  CONSTRAINT xpk_valor_hora PRIMARY KEY (valor_hora_pk),*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_valor_hora")
    @Basic(optional = false)
    @Column(name = "valor_hora_pk")
    private BigInteger id;
    
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "competencia")
    @Temporal(TemporalType.DATE)
    private Date competencia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "vencimento")
    private BigDecimal vencimento;
    
    @Basic(optional = false)
    @Column(name = "jornada_mensal")
    private Short jornadaMensal;

    @Basic(optional = false)
    @Column(name = "jornada_semanal")
    private Short jornadaSemanal;
    
    @Basic(optional = false)
    @Column(name = "valor_hora")
    private BigDecimal valorHora;
   
    @Basic(optional = false)
    @Column(name = "jornada_dias_uteis")
    private Short jornadaDiasUteis;

    @Basic(optional = false)
    @Column(name = "dias_uteis")
    private Short diasUteis;
    
//    @JsonSerialize(using = JsonEntidadeFkSerializer.class)
//    @JoinColumn(name = "funcionario_contrato_fk", referencedColumnName = "funcionario_contrato_pk")
//    @ManyToOne
//    private FuncionarioContrato funcionarioContratoFk;

    public ValorHora() {
    }

    public ValorHora(BigInteger id) {
        this.id = id;
    }

    public ValorHora(BigInteger id, Date competencia) {
        this.id = id;
        this.competencia = competencia;
        
    }
    
    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Date competencia) {
        this.competencia = competencia;
    }

    public BigDecimal getVencimento() {
        return vencimento;
    }

    public void setVencimento(BigDecimal vencimento) {
        this.vencimento = vencimento;
    }

    public Short getJornadaMensal() {
        return jornadaMensal;
    }

    public void setJornadaMensal(Short jornadaMensal) {
        this.jornadaMensal = jornadaMensal;
    }

    public Short getJornadaSemanal() {
        return jornadaSemanal;
    }

    public void setJornadaSemanal(Short jornadaSemanal) {
        this.jornadaSemanal = jornadaSemanal;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public Short getJornadaDiasUteis() {
        return jornadaDiasUteis;
    }

    public void setJornadaDiasUteis(Short jornadaDiasUteis) {
        this.jornadaDiasUteis = jornadaDiasUteis;
    }

    public Short getDiasUteis() {
        return diasUteis;
    }

    public void setDiasUteis(Short diasUteis) {
        this.diasUteis = diasUteis;
    }

//    public FuncionarioContrato getFuncionarioContratoFk() {
//        return funcionarioContratoFk;
//    }
//
//    public void setFuncionarioContratoFk(FuncionarioContrato funcionarioContratoFk) {
//        this.funcionarioContratoFk = funcionarioContratoFk;
//    }
//    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorHora)) {
            return false;
        }
        ValorHora other = (ValorHora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + id;
    }
}
  
    