package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.util.JsonDateTimeDeserializerBr;
import br.gov.ce.saude.ponto.util.JsonDateTimeSerializerBr;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author jr
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BatidaPontorelVO implements Serializable {

    private BigInteger id;
    @JsonSerialize(using = JsonDateTimeSerializerBr.class)
    @JsonDeserialize(using = JsonDateTimeDeserializerBr.class)
    private Date dataBatida; 

    public BatidaPontorelVO(BigInteger id, Date dataBatida) {
        this.id = id;
        this.dataBatida = dataBatida;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getDataBatida() {
        return dataBatida;
    }

    public void setDataBatida(Date dataBatida) {
        this.dataBatida = dataBatida;
    }
}