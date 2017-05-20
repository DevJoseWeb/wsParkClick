/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_acesso")
@SequenceGenerator(name = "seq_acesso", sequenceName = "e_pontows.seq_acesso",
        initialValue = 1, allocationSize = 1)
public class Acesso extends EntidadeBase implements SesaModel {
    
    private static final long serialVersionUID = -8607851174801704506L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_acesso")
    @Basic(optional = false)
    @Column(name = "acesso_pk")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "login")
    private String login;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "senha")
    private String senha;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "acesso_pontoweb")
    private boolean acessoPontoweb;
    
    @Transient
    private String novaSenha;

    public Acesso() {
    }

    public Acesso(BigInteger acessoPk) {
        this.id = acessoPk;
    }

    public Acesso(BigInteger acessoPk, String login, String senha, boolean acessoPontoweb) {
        this.id = acessoPk;
        this.login = login;
        this.senha = senha;
        this.acessoPontoweb = acessoPontoweb;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getAcessoPontoweb() {
        return acessoPontoweb;
    }

    public void setAcessoPontoweb(boolean acessoPontoweb) {
        this.acessoPontoweb = acessoPontoweb;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.login);
        hash = 11 * hash + Objects.hashCode(this.senha);
        hash = 11 * hash + (this.acessoPontoweb ? 1 : 0);
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
        final Acesso other = (Acesso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (this.acessoPontoweb != other.acessoPontoweb) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Acesso[ acessoPk=" + id + " ]";
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }    
}
