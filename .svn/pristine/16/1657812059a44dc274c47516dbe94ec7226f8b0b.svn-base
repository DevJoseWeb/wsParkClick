package br.gov.ce.saude.ponto.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author joao
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BatidaVO implements Serializable {
    
    private Date dataBatida;
    private Integer diaDaSemana;

    private Date b1;
    private Date b2;
    private Date b3;
    private Date b4;

    private Date entrada1;
    private Date saida1;
    private Date entrada2;
    private Date saida2;

    private String data;    
    private String batida1;
    private String batida2;
    private String batida3;
    private String batida4;

    private String total = "00:00";
    private String saldo = "00:00";
        
    private Boolean justificado; // JUSTIFICADO PELO USUARIO
    private String  justificativa; // JUSTIFICATIVA DO USUARIO
    private String  tipo_justificativa; // TIPO JUSTIFICATIVA DO USUARIO
       
    private Boolean justificado_chefe; 
    private String tipo_justificativa_chefe;
    
    private BigInteger batidaPk;
    private String diaDaSemanaStr;

    public BatidaVO() {
    }

    public BatidaVO(Date data, Integer diaDaSemana, Date b1, Date b2, Date b3, Date b4, 
            Date entrada1, Date saida1, Date entrada2, Date saida2, String justificativa,
            Boolean justificado_chefe, String tipo_justificativa_chefe, String tipo_justificativa, BigInteger batidaPk) {
        this.dataBatida = data;
        this.diaDaSemana = diaDaSemana;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.entrada1 = entrada1;
        this.saida1 = saida1;
        this.entrada2 = entrada2;
        this.saida2 = saida2;
        this.tipo_justificativa = tipo_justificativa;
        this.tipo_justificativa_chefe = tipo_justificativa_chefe;  
        this.justificativa = justificativa;
        this.justificado_chefe = justificado_chefe;
        this.batidaPk = batidaPk;
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

    
    public Integer getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(Integer diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Date getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(Date entrada1) {
        this.entrada1 = entrada1;
    }

    public Date getSaida1() {
        return saida1;
    }

    public void setSaida1(Date saida1) {
        this.saida1 = saida1;
    }

    public Date getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(Date entrada2) {
        this.entrada2 = entrada2;
    }

    public Date getSaida2() {
        return saida2;
    }

    public void setSaida2(Date saida2) {
        this.saida2 = saida2;
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

    public Date getB3() {
        return b3;
    }

    public void setB3(Date b3) {
        this.b3 = b3;
    }

    public Date getB4() {
        return b4;
    }

    public void setB4(Date b4) {
        this.b4 = b4;
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

    public BigInteger getBatidaPk() {
        return batidaPk;
    }

    public void setBatidaPk(BigInteger batidaPk) {
        this.batidaPk = batidaPk;
    }

    public String getDiaDaSemanaStr() {
        return diaDaSemanaStr;
    }

    public void setDiaDaSemanaStr(String diaDaSemanaStr) {
        this.diaDaSemanaStr = diaDaSemanaStr;
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
