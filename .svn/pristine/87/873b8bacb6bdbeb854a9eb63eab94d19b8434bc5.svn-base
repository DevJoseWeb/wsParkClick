/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.util.JsonDateDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateSerializer;
import br.gov.ce.saude.ponto.vo.OrganogramaVO;
import br.gov.ce.saude.ponto.vo.UnidadeFuncionalVO;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "organogramaVO",
            classes = {
                @ConstructorResult(
                        targetClass = OrganogramaVO.class,
                        columns = {
                            @ColumnResult(name = "organograma_pk"),
                            @ColumnResult(name = "sigla"),
                            @ColumnResult(name = "nome_org")
                        }
                )
            }
    ),
    @SqlResultSetMapping(
            name = "unidadeFuncionalVO",
            classes = {
                @ConstructorResult(
                        targetClass = UnidadeFuncionalVO.class,
                        columns = {
                            @ColumnResult(name = "organograma_pk"),
                            @ColumnResult(name = "org_pai_fk"),
                            @ColumnResult(name = "org_ponto_fk"),
                            @ColumnResult(name = "nome_org"),
                            @ColumnResult(name = "sigla")
                        }
                )
            }
    )
})
@Entity
@Table(name = "t_organograma", schema = "e_base")
public class Organograma extends EntidadeBase implements SesaModel {

    private static final long serialVersionUID = -7862694391148485017L;

    @Transient
    private BigInteger id;

    @Id
    @Column(name = "organograma_pk")
    private Integer organogramaPk;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome_org")
    private String nomeOrg;

    @Size(max = 100)
    @Column(name = "nome_org2")
    private String nomeOrg2;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "sigla")
    private String sigla;

    @Column(name = "tipo_orgao")
    private Character tipoOrgao;

    @Column(name = "tipo_estabelecimento")
    private Character tipoEstabelecimento;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "data_ini")
    @Temporal(TemporalType.DATE)
    private Date dataIni;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @Column(name = "ponto")
    private Character ponto;

    @NotNull
    @Column(name = "natureza")
    private Character natureza;

    @Column(name = "org_pai_fk")
    private Integer orgPai;

    @Column(name = "ramal")
    private String ramal;

    @Column(name = "org_ponto_fk")
    private Integer orgPonto;
    
    public Organograma() {
    }

    public Organograma(BigInteger organogramaPk) {
        this.id = organogramaPk;
    }

    public Organograma(BigInteger organogramaPk, String sigla, String nomeOrg) {
        setId(organogramaPk);
        this.nomeOrg = nomeOrg;
        this.sigla = sigla;
    }

    public Organograma(BigInteger organogramaPk, String nomeOrg, String sigla, Character natureza) {
        this.id = organogramaPk;
        this.nomeOrg = nomeOrg;
        this.sigla = sigla;
        this.natureza = natureza;
    }

    public BigInteger getId() {
        if (getOrganogramaPk() == null) {
            return id;
        } else {
            return new BigInteger(getOrganogramaPk().toString());
        }
    }

    public void setId(BigInteger id) {
        this.id = id;
        setOrganogramaPk(id.intValue());
    }

    public Integer getOrganogramaPk() {
        return organogramaPk;
    }

    public void setOrganogramaPk(Integer organogramaPk) {
        this.organogramaPk = organogramaPk;
    }

    public String getNomeOrg() {
        return nomeOrg;
    }

    public void setNomeOrg(String nomeOrg) {
        this.nomeOrg = nomeOrg;
    }

    public String getNomeOrg2() {
        return nomeOrg2;
    }

    public void setNomeOrg2(String nomeOrg2) {
        this.nomeOrg2 = nomeOrg2;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Character getTipoOrgao() {
        return tipoOrgao;
    }

    public void setTipoOrgao(Character tipoOrgao) {
        this.tipoOrgao = tipoOrgao;
    }

    public Character getTipoEstabelecimento() {
        return tipoEstabelecimento;
    }

    public void setTipoEstabelecimento(Character tipoEstabelecimento) {
        this.tipoEstabelecimento = tipoEstabelecimento;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Character getPonto() {
        return ponto;
    }

    public void setPonto(Character ponto) {
        this.ponto = ponto;
    }

    public Character getNatureza() {
        return natureza;
    }

    public void setNatureza(Character natureza) {
        this.natureza = natureza;
    }

    public Integer getOrgPai() {
        return orgPai;
    }

    public void setOrgPai(Integer orgPai) {
        this.orgPai = orgPai;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public Integer getOrgPonto() {
        return orgPonto;
    }

    public void setOrgPonto(Integer orgPonto) {
        this.orgPonto = orgPonto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organograma)) {
            return false;
        }
        Organograma other = (Organograma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Organograma[ organogramaPk=" + id + " ]";
    }

}
