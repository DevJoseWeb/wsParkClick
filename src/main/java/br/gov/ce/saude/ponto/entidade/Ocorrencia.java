/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.vo.OcorrenciaBatidaVO;
import br.gov.ce.saude.ponto.vo.OcorrenciaEscalaVO;
import br.gov.ce.saude.ponto.vo.RelatorioOcorrenciasVO;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "t_ocorrencia", schema = "e_pontows")
@XmlRootElement
@SequenceGenerator(name = "seq_ocorrencia", sequenceName = "e_pontows.seq_ocorrencia",
        initialValue = 1, allocationSize = 1)

@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "ocorrenciaBatida",
            classes = {
                @ConstructorResult(
                        targetClass = OcorrenciaBatidaVO.class,
                        columns = {
                            @ColumnResult(name = "batida_rel_pk"),
                            @ColumnResult(name = "batida_rel_escala_pk"),
                            @ColumnResult(name = "ponto_processado_pk"),
                            @ColumnResult(name = "dia_semana"),
                            @ColumnResult(name = "data"),
                            @ColumnResult(name = "batida_1"),
                            @ColumnResult(name = "batida_2"),
                            @ColumnResult(name = "batida_3"),
                            @ColumnResult(name = "batida_4"),
                            @ColumnResult(name = "ocorrencia"),
                            @ColumnResult(name = "tipo_justificativa_fk"),
                            @ColumnResult(name = "tipo_justificativa_processada_fk"),
                            @ColumnResult(name = "tipo_justificativa"),
                            @ColumnResult(name = "tem_justificativa"),
                            @ColumnResult(name = "justificado")
                        }
                )
            }
    ),
    @SqlResultSetMapping(
            name = "ocorrenciaEscala",
            classes = {
                @ConstructorResult(
                        targetClass = OcorrenciaEscalaVO.class,
                        columns = {
                            @ColumnResult(name = "batida_rel_pk"),
                            @ColumnResult(name = "batida_rel_escala_pk"),
                            @ColumnResult(name = "ponto_processado_pk"),
                            @ColumnResult(name = "dia_semana"),
                            @ColumnResult(name = "data"),
                            @ColumnResult(name = "batida_1"),
                            @ColumnResult(name = "batida_2"),
                            @ColumnResult(name = "batida_3"),
                            @ColumnResult(name = "batida_4"),
                            @ColumnResult(name = "ocorrencia"),
                            @ColumnResult(name = "tipo_justificativa_fk"),
                            @ColumnResult(name = "tipo_justificativa_processada_fk"),
                            @ColumnResult(name = "tipo_justificativa"),
                            @ColumnResult(name = "tem_justificativa"),
                            @ColumnResult(name = "justificado")
                        }
                )
            }
    ),
    @SqlResultSetMapping(
            name = "relatorioOcorrencia",
            classes = {
                @ConstructorResult(
                        targetClass = RelatorioOcorrenciasVO.class,
                        columns = {
                            @ColumnResult(name = "categoria"),
                            @ColumnResult(name = "unidade"),
                            @ColumnResult(name = "atraso"),
                            @ColumnResult(name = "meia_falta"),
                            @ColumnResult(name = "falta"),
                            @ColumnResult(name = "qtd_horas"),
                            @ColumnResult(name = "vlr_pago")
                        }
                )
            }
    ),
    @SqlResultSetMapping(
            name = "relatorioOcorrenciapessoa",
            classes = {
                @ConstructorResult(
                        targetClass = RelatorioOcorrenciasVO.class,
                        columns = {
                            @ColumnResult(name = "nome"),
                            @ColumnResult(name = "matricula"),                            
                            @ColumnResult(name = "categoria"),
                            @ColumnResult(name = "unidade"),
                            @ColumnResult(name = "atraso"),
                            @ColumnResult(name = "meia_falta"),
                            @ColumnResult(name = "falta"),
                            @ColumnResult(name = "qtd_horas"),
                            @ColumnResult(name = "vlr_pago")
                        }
                )
            }
    )
})
public class Ocorrencia extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = 4486944218531064529L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ocorrencia")
    @Basic(optional = false)
    @Column(name = "ocorrencia_pk")
    private BigInteger id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "abreviatura")
    private String abreviatura;

    public Ocorrencia() {
    }

    public Ocorrencia(BigInteger ocorrenciaPk) {
        this.id = ocorrenciaPk;
    }

    public Ocorrencia(BigInteger ocorrenciaPk, String descricao, String abreviatura) {
        this.id = ocorrenciaPk;
        this.descricao = descricao;
        this.abreviatura = abreviatura;
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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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
        if (!(object instanceof Ocorrencia)) {
            return false;
        }
        Ocorrencia other = (Ocorrencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Ocorrencia[ ocorrenciaPk=" + id + " ]";
    }

}
