package br.gov.ce.saude.ponto.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author joao
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BatidaEscalaVO implements Serializable {
    
    private Date dataBatida;
    private Integer diaDaSemana;

    private Date b1;
    private Date b2;
    
    
    private String data; 
    @JsonSerialize
    @JsonProperty("entrada")
    private String batida1;
    @JsonProperty("horarioEntrada")
    private String batida2;
    
    @JsonProperty("saida")
    private String batida3;
    @JsonProperty("horarioSaida")
    private String batida4;

    @JsonIgnore
    private Date entrada;
    @JsonIgnore
    private Date saida;
    
    private String total = "00:00";
    private String saldo = "00:00";
    
    // Justificado depende da justificativa
    // Justificativa é a justificativa descrita pelo usuario
    // Tipo_justificativa é o tipo de justificativa selecionado pelo usuário
    private Boolean justificado;
    private String  justificativa;
    private String  tipo_justificativa;
    
    
    private Boolean justificado_chefe;
    private String tipo_justificativa_chefe;
    private BigInteger batidaEscalaRelPk;
    private BigInteger escalaItemPk;
    private String diaDaSemanaStr;  
    
    @JsonIgnore
    private Long deveTrabalhar = new Long("0");

    public BatidaEscalaVO() {
    }

    public BatidaEscalaVO(Date data, Integer diaDaSemana, Date b1, Date b2, Date entrada, Date saida, 
            String justificativa, Boolean justificado_chefe, String tipo_justificativa_chefe, String tipo_justificativa, BigInteger batidaPk, BigInteger escalaItemPk) {
        this.dataBatida = data; 
        this.diaDaSemana = diaDaSemana;
        this.b1 = b1;
        this.b2 = b2;
        this.entrada = entrada;
        this.saida = saida;        
        this.justificativa = justificativa;         
        this.justificado_chefe = justificado_chefe;         
        this.tipo_justificativa = tipo_justificativa;
        this.tipo_justificativa_chefe = tipo_justificativa_chefe;        
        this.batidaEscalaRelPk = batidaPk;        
        this.escalaItemPk = escalaItemPk;
    }

    public Integer getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(Integer diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
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

    public Long getDeveTrabalhar() {
        return deveTrabalhar;
    }

    public void setDeveTrabalhar(Long deveTrabalhar) {
        this.deveTrabalhar = deveTrabalhar;
    }

    public Boolean getJustificado() {
        return justificado;
    }

    public void setJustificado(Boolean justificado) {
        this.justificado = justificado;
    }
    
    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBatida1() {
        return batida1;
    }

    public void setBatida1(String batida1) {
        this.batida1 = batida1;
    }

    public String getBatida2() {
        return batida2;
    }

    public void setBatida2(String batida2) {
        this.batida2 = batida2;
    }

    public String getBatida3() {
        return batida3;
    }

    public void setBatida3(String batida3) {
        this.batida3 = batida3;
    }

    public String getBatida4() {
        return batida4;
    }

    public void setBatida4(String batida4) {
        this.batida4 = batida4;
    }

    public Date getDataBatida() {
        return dataBatida;
    }

    public void setDataBatida(Date dataBatida) {
        this.dataBatida = dataBatida;
    }

    public Date getB1() {
        return b1;
    }

    public void setB1(Date b1) {
        this.b1 = b1;
    }

    public Date getB2() {
        return b2;
    }

    public void setB2(Date b2) {
        this.b2 = b2;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public BigInteger getBatidaEscalaRelPk() {
        return batidaEscalaRelPk;
    }

    public void setBatidaEscalaRelPk(BigInteger batidaEscalaRelPk) {
        this.batidaEscalaRelPk = batidaEscalaRelPk;
    }

    public String getDiaDaSemanaStr() {
        return diaDaSemanaStr;
    }

    public void setDiaDaSemanaStr(String diaDaSemanaStr) {
        this.diaDaSemanaStr = diaDaSemanaStr;
    }

    public BigInteger getEscalaItemPk() {
        return escalaItemPk;
    }

    public void setEscalaItemPk(BigInteger escalaItemPk) {
        this.escalaItemPk = escalaItemPk;
    }

    public String getTipo_justificativa() {
        return tipo_justificativa;
    }

    public void setTipo_justificativa(String tipo_justificativa) {
        this.tipo_justificativa = tipo_justificativa;
    }

    public Boolean getJustificado_chefe() {
        return justificado_chefe;
    }

    public void setJustificado_chefe(Boolean justificado_chefe) {
        this.justificado_chefe = justificado_chefe;
    }

    public String getTipo_justificativa_chefe() {
        return tipo_justificativa_chefe;
    }

    public void setTipo_justificativa_chefe(String tipo_justificativa_chefe) {
        this.tipo_justificativa_chefe = tipo_justificativa_chefe;
    }
      
    
}
