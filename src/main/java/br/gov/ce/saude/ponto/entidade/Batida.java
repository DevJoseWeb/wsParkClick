/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.entidade;

import br.gov.ce.auditoriaws.entidade.EntidadeBase;
import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.vo.BatidaUnidadeVO;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "t_batida", schema = "e_pontows")
@XmlRootElement
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "batidaUnidade",
            classes = {
                @ConstructorResult(
                        targetClass = BatidaUnidadeVO.class,
                        columns = {
                            @ColumnResult(name = "id"),
                            @ColumnResult(name = "matricula"),
                            @ColumnResult(name = "nome"),
                            @ColumnResult(name = "data"),
                            @ColumnResult(name = "unidade"),
                            @ColumnResult(name = "macaddress")
                        }
                )
            }
                )
})
@SequenceGenerator(name = "seq_batida", sequenceName = "e_pontows.seq_batida",
        initialValue = 1, allocationSize = 1)
public class Batida extends EntidadeBase implements SesaModel {
    private static final long serialVersionUID = 3985547206353243052L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_batida")
    @Column(name = "batida_pk")
    private BigInteger id;

    @Column(name = "data_batida")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Size(min = 1, max = 12)
    @Column(name = "macaddress")
    private String macaddress;        
    
    @Size(min = 1, max = 12)
    @Column(name = "ipaddress")
    private String ipaddress;        

    @JoinColumn(name = "funcionario_horario_fk", referencedColumnName = "funcionario_horario_pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FuncionarioHorario funcionarioHorario;

    public Batida() {
    }

    public Batida(BigInteger id) {
        this.id = id;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public FuncionarioHorario getFuncionarioHorario() {
        return funcionarioHorario;
    }

    public void setFuncionarioHorario(FuncionarioHorario funcionarioHorario) {
        this.funcionarioHorario = funcionarioHorario;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Batida other = (Batida) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "br.gov.ce.saude.ponto.entidade.Batida[ batidaPk=" + id + " ]";
    }

}
