package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.util.JsonDateDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateSerializer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author jr
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ValorHoraVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date competencia;
    private BigDecimal vencimento;
    private Short jornadaMensal;
    private Short jornadaSemanal;
    private BigDecimal valorHora;
    private Short jornadaDiasUteis;
    private Short diasUteis;
    //private FuncionarioContrato funcionarioContratoFk;

    public ValorHoraVO() {
    }

    public ValorHoraVO(Date competencia,
            BigDecimal valorHora,
            BigDecimal vencimento,
            Short jornadaMensal,
            Short jornadaSemanal,
            Short jornadaDiasUteis
    ) {

        this.competencia = competencia;
        this.vencimento = vencimento;
        this.jornadaMensal = jornadaMensal;
        this.jornadaSemanal = jornadaSemanal;
        this.valorHora = valorHora;
        this.jornadaDiasUteis = jornadaDiasUteis;
        //  this.funcionarioContratoFk = funcionarioContratoFk;

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

    
}
