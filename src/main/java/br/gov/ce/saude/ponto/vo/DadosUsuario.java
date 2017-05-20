/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

/**
 *
 * @author joao
 */
public class DadosUsuario {
    private String matricula;
    private String nome;
    private String cargo;
    private String vinculo;
    private String unidOrg;
    private String unidFunc;

    public DadosUsuario(String matricula, String nome, String cargo, String vinculo, String unidOrg, String unidFunc) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.vinculo = vinculo;
        this.unidOrg = unidOrg;
        this.unidFunc = unidFunc;
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

    public String getUnidOrg() {
        return unidOrg;
    }

    public void setUnidOrg(String unidOrg) {
        this.unidOrg = unidOrg;
    }

    public String getUnidFunc() {
        return unidFunc;
    }

    public void setUnidFunc(String unidFunc) {
        this.unidFunc = unidFunc;
    }
    
    
    
}
