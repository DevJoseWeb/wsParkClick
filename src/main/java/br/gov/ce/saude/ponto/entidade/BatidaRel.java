/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.vo.BatidaEscalaVO;
import br.gov.ce.saude.ponto.vo.BatidaPontorelVO;
import br.gov.ce.saude.ponto.vo.BatidaVO;
import br.gov.ce.saude.ponto.vo.EscalaVO;
import br.gov.ce.saude.ponto.vo.NaoBatemPontoVO;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_batida_rel", schema = "e_pontows")
@XmlRootElement
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "batidaPonto",
            classes = {
                @ConstructorResult(
                        targetClass = BatidaVO.class,
                        columns = {
                            @ColumnResult(name = "data"),
                            @ColumnResult(name = "dia_semana"),
                            @ColumnResult(name = "batida_1"),
                            @ColumnResult(name = "batida_2"),
                            @ColumnResult(name = "batida_3"),
                            @ColumnResult(name = "batida_4"),
                            @ColumnResult(name = "entrada_1"),
                            @ColumnResult(name = "saida_1"),
                            @ColumnResult(name = "entrada_2"),
                            @ColumnResult(name = "saida_2"),
                            @ColumnResult(name = "justificativa"),
                            @ColumnResult(name = "justificado_chefe"),
                            @ColumnResult(name = "tipo_justificativa_chefe"),
                            @ColumnResult(name = "tipo_justificativa"),
                            @ColumnResult(name = "batida_rel_pk")
                        }
                )
            }
    ),
    
    @SqlResultSetMapping(
            name = "batidaEscalaPonto",
            classes = {
                @ConstructorResult(
                        targetClass = BatidaEscalaVO.class,
                        columns = {
                            @ColumnResult(name = "data"),
                            @ColumnResult(name = "dia_semana"),                            
                            @ColumnResult(name = "batida_1"),
                            @ColumnResult(name = "batida_2"),
                            @ColumnResult(name = "entrada"),
                            @ColumnResult(name = "saida"),
                            @ColumnResult(name = "justificativa"),
                            @ColumnResult(name = "justificado_chefe"),
                            @ColumnResult(name = "tipo_justificativa_chefe"),
                            @ColumnResult(name = "tipo_justificativa"),
                            @ColumnResult(name = "batida_rel_escala_pk"),
                            @ColumnResult(name = "escala_item_pk")
                        }
                )
            }
    ),
    
    @SqlResultSetMapping(
            name = "batidaPontoind",
            classes = {
                @ConstructorResult(
                        targetClass = BatidaPontorelVO.class,
                        columns = {
                            @ColumnResult(name = "funcionario_contrato_pk"),
                            @ColumnResult(name = "data_batida")
                            
                        }
                )
            }
    ),

    @SqlResultSetMapping(
            name = "batidaEscala",
            classes = {
                @ConstructorResult(
                        targetClass = EscalaVO.class,
                        columns = {
                            @ColumnResult(name = "entrada"),
                            @ColumnResult(name = "saida")
                        }
    )
            }
    ),
    
    @SqlResultSetMapping(
            name = "relatorioPessoasNaoBatem",
            classes = {
                @ConstructorResult(
                        targetClass = NaoBatemPontoVO.class,
                        columns = {
                            @ColumnResult(name = "matricula"),
                            @ColumnResult(name = "nome"),
                            @ColumnResult(name = "setor"),
                            @ColumnResult(name = "unidade"),
                            @ColumnResult(name = "vinculo")                      
                        }
                )
            }
    )

})
@SequenceGenerator(name = "seq_batida_rel", sequenceName = "e_pontows.seq_batida_rel",
        initialValue = 1, allocationSize = 1)
public class BatidaRel extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = 4432056493873286638L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_batida_rel")
    @Column(name = "batida_rel_pk")
    private BigInteger id;

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

    @Size(max = 1000)
    @Column(name = "justificativa")
    private String justificativa;

    @JsonIgnore
    @JoinColumn(name = "funcionario_horario_fk", referencedColumnName = "funcionario_horario_pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FuncionarioHorario funcionarioHorario;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    @JoinColumn(name = "tipo_justificativa_fk", referencedColumnName = "justificativa_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoJustificativa tipoJustificativa;
    
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "batidarelFk", 
            fetch = FetchType.EAGER)
    private List<JustificativaFuncionario> justificativaBatidas = new ArrayList<JustificativaFuncionario>();


    public BatidaRel() {
    }

    public BatidaRel(BigInteger batidaRelPk) {
        this.id = batidaRelPk;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
    

    public List<JustificativaFuncionario> getJustificativaBatidas() {
        return justificativaBatidas;
    }

    public void setJustificativaBatidas(List<JustificativaFuncionario> justificativaBatidas) {
        this.justificativaBatidas = justificativaBatidas;
    }
    
    public TipoJustificativa getTipoJustificativa() {
        return tipoJustificativa;
    }

    public void setTipoJustificativa(TipoJustificativa tipoJustificativa) {
        this.tipoJustificativa = tipoJustificativa;
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
        if (!(object instanceof BatidaRel)) {
            return false;
        }
        BatidaRel other = (BatidaRel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.BatidaRel[ batidaRelPk=" + id + " ]";
    }

    public FuncionarioHorario getFuncionarioHorario() {
        return funcionarioHorario;
    }

    public void setFuncionarioHorario(FuncionarioHorario funcionarioHorario) {
        this.funcionarioHorario = funcionarioHorario;
    }

}
