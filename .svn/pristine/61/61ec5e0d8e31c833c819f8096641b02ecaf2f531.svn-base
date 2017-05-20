/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.util.JsonDateTimeDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateTimeSerializer;
import br.gov.ce.saude.ponto.vo.EscalaMensalVO;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.envers.NotAudited;

/**
 *
 * @author joao
 */
//@Audited
@Entity
@Table(name = "t_escala_item")
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "escalaMensalVO",
            classes = {
                @ConstructorResult(
                        targetClass = EscalaMensalVO.class,
                        columns = {
                            @ColumnResult(name = "id"),
                            @ColumnResult(name = "matricula"),
                            @ColumnResult(name = "nome"),
                            @ColumnResult(name = "funcao"),
                            @ColumnResult(name = "idescala"),
                            @ColumnResult(name = "entrada"),
                            @ColumnResult(name = "saida"),
                            @ColumnResult(name = "idlegenda"),
                            @ColumnResult(name = "legenda")
                        }
                )
            }
    )
})

@SequenceGenerator(name = "seq_escala_item", sequenceName = "e_pontows.seq_escala_item",
        initialValue = 1, allocationSize = 1)
public class EscalaItem extends EntidadeBase implements SesaModel {
    private static final long serialVersionUID = -6888792562457318398L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_escala_item")
    @Basic(optional = false)
    @Column(name = "escala_item_pk")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "entrada")
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;
    
    @Basic(optional = false)
    @NotNull
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    @Column(name = "saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;
    
    @NotAudited
    @JoinColumn(name = "funcionario_horario_fk", referencedColumnName = "funcionario_horario_pk")
    @JsonIgnore
    @ManyToOne(optional = false, cascade = { CascadeType.REFRESH })
    private FuncionarioHorario funcionarioHorario;
    
    @NotAudited
    @JsonIgnore
    @JoinColumn(name = "escala_fk", referencedColumnName = "escala_pk")
    @ManyToOne(optional = false, cascade = { CascadeType.REFRESH})
    private Escala escala;
    
    
    @NotAudited
    @JoinColumn(name = "legenda_escala_fk", referencedColumnName = "legenda_escala_pk")
    @ManyToOne(optional = false, cascade = { CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private LegendaEscala legendaEscala;

    public EscalaItem() {
    }

    public EscalaItem(BigInteger escalaItemPk) {
        this.id = escalaItemPk;
    }

    public EscalaItem(BigInteger escalaItemPk, Date entrada, Date saida) {
        this.id = escalaItemPk;
        this.entrada = entrada;
        this.saida = saida;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public FuncionarioHorario getFuncionarioHorario() {
        return funcionarioHorario;
    }

    public void setFuncionarioHorario(FuncionarioHorario funcionarioHorario) {
        this.funcionarioHorario = funcionarioHorario;
    }

    public LegendaEscala getLegendaEscala() {
        return legendaEscala;
    }

    public void setLegendaEscala(LegendaEscala legendaEscala) {
        this.legendaEscala = legendaEscala;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.entrada);
        hash = 79 * hash + Objects.hashCode(this.saida);
        hash = 79 * hash + Objects.hashCode(this.funcionarioHorario);
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
        final EscalaItem other = (EscalaItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.entrada, other.entrada)) {
            return false;
        }
        if (!Objects.equals(this.saida, other.saida)) {
            return false;
        }
        if (!Objects.equals(this.funcionarioHorario, other.funcionarioHorario)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.autenticacao.EscalaItem[ escalaItemPk=" + id + " ]";
    }

    public Escala getEscala() {
        return escala;
    }

    public void setEscala(Escala escala) {
        this.escala = escala;
    }
    
}
