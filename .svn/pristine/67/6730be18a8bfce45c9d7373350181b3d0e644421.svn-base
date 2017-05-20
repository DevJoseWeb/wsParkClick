package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.util.JsonDateDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateSerializer;
import br.gov.ce.saude.ponto.util.JsonTimeDeserializer;
import br.gov.ce.saude.ponto.util.JsonTimeSerializer;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author joao
 */
public class OcorrenciaBatidaVO implements Serializable {

    private BigInteger batidaRelPk;
    private BigInteger batidaRelEscalaPk;
    private BigInteger pontoProcessadoPk;
    private String diaSemana;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date data;
    
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    private Date batida1;
    
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    private Date batida2;
    
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    private Date batida3;
    
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    private Date batida4;    
    
    private BigInteger justificativaPk;
    private BigInteger justificativaProcessadaPk;
    private String tipoJustificativa;
    private String ocorrencia;
    private Boolean temJustificativa;
    private Boolean justificado;

    public OcorrenciaBatidaVO(BigInteger batidaRelPk, BigInteger batidaRelEscalaPk, BigInteger pontoProcessadoPk, 
            String diaSemana, Date data, Date batida1, Date batida2, Date batida3, Date batida4, 
            String ocorrencia, BigInteger justificativaPk, BigInteger justificativaProcessadaPk, 
            String tipoJustificativa, Boolean temJustificativa, Boolean justificado) {
        this.batidaRelPk = batidaRelPk;
        this.batidaRelEscalaPk = batidaRelEscalaPk;
        this.pontoProcessadoPk = pontoProcessadoPk;
        this.diaSemana = diaSemana;
        this.data = data;
        
        this.batida1 = batida1;
        this.batida2 = batida2;
        this.batida3 = batida3;
        this.batida4 = batida4;        
        
        this.justificativaPk = justificativaPk;
        this.justificativaProcessadaPk = justificativaProcessadaPk;
        this.tipoJustificativa = tipoJustificativa;
        this.ocorrencia = ocorrencia;
        this.temJustificativa = temJustificativa;
        this.justificado = justificado;
    }

    public BigInteger getBatidaRelPk() {
        return batidaRelPk;
    }

    public void setBatidaRelPk(BigInteger batidaRelPk) {
        this.batidaRelPk = batidaRelPk;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Boolean getTemJustificativa() {
        return temJustificativa;
    }

    public void setTemJustificativa(Boolean temJustificativa) {
        this.temJustificativa = temJustificativa;
    }

    public Boolean getJustificado() {
        return justificado;
    }

    public void setJustificado(Boolean justificado) {
        this.justificado = justificado;
    }

    public BigInteger getPontoProcessadoPk() {
        return pontoProcessadoPk;
    }

    public void setPontoProcessadoPk(BigInteger pontoProcessadoPk) {
        this.pontoProcessadoPk = pontoProcessadoPk;
    }

    public BigInteger getBatidaRelEscalaPk() {
        return batidaRelEscalaPk;
    }

    public void setBatidaRelEscalaPk(BigInteger batidaRelEscalaPk) {
        this.batidaRelEscalaPk = batidaRelEscalaPk;
    }

    public BigInteger getJustificativaPk() {
        return justificativaPk;
    }

    public void setJustificativaPk(BigInteger justificativaPk) {
        this.justificativaPk = justificativaPk;
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

    public String getTipoJustificativa() {
        return tipoJustificativa;
    }

    public void setTipoJustificativa(String tipoJustificativa) {
        this.tipoJustificativa = tipoJustificativa;
    }
    
    public BigInteger getJustificativaProcessadaPk() {
        return justificativaProcessadaPk;
    }

    public void setJustificativaProcessadaPk(BigInteger justificativaProcessadaPk) {
        this.justificativaProcessadaPk = justificativaProcessadaPk;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.batidaRelPk);
        hash = 67 * hash + Objects.hashCode(this.diaSemana);
        hash = 67 * hash + Objects.hashCode(this.data);
        hash = 67 * hash + Objects.hashCode(this.ocorrencia);
        hash = 67 * hash + Objects.hashCode(this.temJustificativa);
        hash = 67 * hash + Objects.hashCode(this.justificado);
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
        final OcorrenciaBatidaVO other = (OcorrenciaBatidaVO) obj;
        if (!Objects.equals(this.batidaRelPk, other.batidaRelPk)) {
            return false;
        }
        if (!Objects.equals(this.diaSemana, other.diaSemana)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.ocorrencia, other.ocorrencia)) {
            return false;
        }
        if (!Objects.equals(this.temJustificativa, other.temJustificativa)) {
            return false;
        }
        if (!Objects.equals(this.justificado, other.justificado)) {
            return false;
        }
        return true;
    }

}
