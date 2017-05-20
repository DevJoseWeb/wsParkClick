/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.util.JsonDateDeserializerbr;
import br.gov.ce.saude.ponto.util.JsonDateSerializerbr;
import br.gov.ce.saude.ponto.util.JsonDateTimeDeserializerBr;
import br.gov.ce.saude.ponto.util.JsonDateTimeSerializerBr;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AcompanhamentoVO implements Serializable {

    private static final long serialVersionUID = 2517752753686867813L;

    private BigInteger funcionarioContratoPk;
    private String matricula;
    private String nome;
    private String regime;
    private String cargo;
    private String vinculo;
    @JsonSerialize(using = JsonDateSerializerbr.class)
    @JsonDeserialize(using = JsonDateDeserializerbr.class)
    private Date data;
    @JsonSerialize(using = JsonDateTimeSerializerBr.class)
    @JsonDeserialize(using = JsonDateTimeDeserializerBr.class)
    private Date b1;
    @JsonSerialize(using = JsonDateTimeSerializerBr.class)
    @JsonDeserialize(using = JsonDateTimeDeserializerBr.class)
    private Date b2;
    @JsonSerialize(using = JsonDateTimeSerializerBr.class)
    @JsonDeserialize(using = JsonDateTimeDeserializerBr.class)
    private Date b3;
    @JsonSerialize(using = JsonDateTimeSerializerBr.class)
    @JsonDeserialize(using = JsonDateTimeDeserializerBr.class)
    private Date b4;

    public AcompanhamentoVO() {
    }

    public AcompanhamentoVO(BigInteger funcionarioContratoPk, String matricula, String nome, String regime, String cargo, String vinculo, Date data, Date b1, Date b2, Date b3, Date b4) {
        this.funcionarioContratoPk = funcionarioContratoPk;
        this.matricula = matricula;
        this.nome = nome;
        this.regime = regime;
        this.cargo = cargo;
        this.vinculo = vinculo;
        this.data = data;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
    }

    public BigInteger getFuncionarioContratoPk() {
        return funcionarioContratoPk;
    }

    public void setFuncionarioContratoPk(BigInteger funcionarioContratoPk) {
        this.funcionarioContratoPk = funcionarioContratoPk;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

}
