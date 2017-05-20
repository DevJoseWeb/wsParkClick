/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author joao
 */
public class ContratoVO implements Serializable {
    private static final long serialVersionUID = 2997137404646500787L;
    
    private BigInteger funcionarioContratoPk;
    private BigInteger funcionarioHorarioPk;
    private String nome;
    private String matricula;
    private String unidade;
    private String diarista;
    private String plantonista;
    private String horaExtra;
    private BigInteger horarioFk; 

    public ContratoVO(BigInteger funcionarioContratoPk, BigInteger funcionarioHorarioPk, BigInteger horarioFk, String nome, String matricula, String unidade, String diarista, String plantonista, String horaExtra) {
        this.funcionarioContratoPk = funcionarioContratoPk;
        this.funcionarioHorarioPk = funcionarioHorarioPk;
        this.horarioFk = horarioFk;
        this.nome = nome;
        this.matricula = matricula;
        this.unidade = unidade;
        this.diarista = diarista;
        this.plantonista = plantonista;
        this.horaExtra = horaExtra;
    }
    
    public BigInteger getFuncionarioContratoPk() {
        return funcionarioContratoPk;
    }

    public void setFuncionarioContratoPk(BigInteger funcionarioContratoPk) {
        this.funcionarioContratoPk = funcionarioContratoPk;
    }

    public BigInteger getFuncionarioHorarioPk() {
        return funcionarioHorarioPk;
    }

    public void setFuncionarioHorarioPk(BigInteger funcionarioHorarioPk) {
        this.funcionarioHorarioPk = funcionarioHorarioPk;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getDiarista() {
        return diarista;
    }

    public void setDiarista(String diarista) {
        this.diarista = diarista;
    }

    public String getPlantonista() {
        return plantonista;
    }

    public void setPlantonista(String plantonista) {
        this.plantonista = plantonista;
    }

    public String getHoraExtra() {
        return horaExtra;
    }

    public void setHoraExtra(String horaExtra) {
        this.horaExtra = horaExtra;
    }

    public BigInteger getHorarioFk() {
        return horarioFk;
    }

    public void setHorarioPk(BigInteger horarioFk) {
        this.horarioFk = horarioFk;
    }
    
}
