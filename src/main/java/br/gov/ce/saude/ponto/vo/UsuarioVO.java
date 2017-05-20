/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author joao
 */
public class UsuarioVO {
    
    private BigInteger pessoaPk;
    private String cpf;
    
    private String login;
    private String matricula;
    private String nome;
    private BigInteger usuarioPk;
    private Boolean ativo;
    

    public UsuarioVO(String login, String matricula, String nome) {
        this.login = login;
        this.matricula = matricula;
        this.nome = nome;
    }

    public UsuarioVO(BigInteger pessoaPk, String nome, String cpf, String login, BigInteger usuarioPk) {
        this.pessoaPk = pessoaPk;
        this.cpf = cpf;
        //this.matricula = matricula;
        this.nome = nome;
        this.login = login;
        this.usuarioPk = usuarioPk;
    }
    
    public UsuarioVO(BigInteger pessoaPk, String nome, String cpf, String login, BigInteger usuarioPk, Boolean ativo) {
        this.pessoaPk = pessoaPk;
        this.cpf = cpf;
        this.ativo = ativo;
        this.nome = nome;
        this.login = login;
        this.usuarioPk = usuarioPk;
    }

    public BigInteger getPessoaPk() {
        return pessoaPk;
    }

    public void setPessoaPk(BigInteger pessoaPk) {
        this.pessoaPk = pessoaPk;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public BigInteger getUsuarioPk() {
        return usuarioPk;
    }

    public void setUsuarioPk(BigInteger usuarioPk) {
        this.usuarioPk = usuarioPk;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.pessoaPk);
        hash = 53 * hash + Objects.hashCode(this.cpf);
        hash = 53 * hash + Objects.hashCode(this.login);
        hash = 53 * hash + Objects.hashCode(this.matricula);
        hash = 53 * hash + Objects.hashCode(this.nome);
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
        final UsuarioVO other = (UsuarioVO) obj;
        if (!Objects.equals(this.pessoaPk, other.pessoaPk)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
           
}
