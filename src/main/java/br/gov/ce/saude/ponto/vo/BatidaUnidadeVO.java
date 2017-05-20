package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.util.JsonDateTimeDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateTimeSerializer;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author cristiano
 */
public class BatidaUnidadeVO implements Serializable {
    private static final long serialVersionUID = 6730491149861321598L;
    
    private BigInteger id;
    
    private String Matricula;
    
    private String nome; 
    
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    private Date dataBatida;
 

    private String unidade;
    private String macAddres;  
    
    public BatidaUnidadeVO() {
    }

    public BatidaUnidadeVO(BigInteger id,String Matricula, String nome, Date dataBatida, String unidade, String macAddres) {
        this.id = id;
        this.Matricula = Matricula;
        this.nome = nome;
        this.dataBatida = dataBatida;
        this.unidade = unidade;
        this.macAddres = macAddres;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataBatida() {
        return dataBatida;
    }

    public void setDataBatida(Date dataBatida) {
        this.dataBatida = dataBatida;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getMacAddres() {
        return macAddres;
    }

    public void setMacAddres(String macAddres) {
        this.macAddres = macAddres;
    }
    
    

}
