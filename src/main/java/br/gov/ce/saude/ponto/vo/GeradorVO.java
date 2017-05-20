package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.entidade.Funcionario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
* @author esmayk.tillesse
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GeradorVO implements Serializable {

    private Date datasoli;
    private String matricula;
    private List<Funcionario> FuncionarioFk;
    private Integer UnidadeFk;
    private Integer quantidade;
    private String tipo;
    private Integer digito;

    public GeradorVO() {
    }

    public GeradorVO(Date datasoli, String matricula, List<Funcionario> FuncionarioFk, Integer UnidadeFk, Integer quantidade, String tipo, Integer digito) {
        this.datasoli = datasoli;
        this.matricula = matricula;
        this.FuncionarioFk = FuncionarioFk;
        this.UnidadeFk = UnidadeFk;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.digito = digito;
    }

    public Date getDatasoli() {
        return datasoli;
    }

    public void setDatasoli(Date datasoli) {
        this.datasoli = datasoli;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Funcionario> getFuncionarioFk() {
        return FuncionarioFk;
    }

    public void setFuncionarioFk(List<Funcionario> FuncionarioFk) {
        this.FuncionarioFk = FuncionarioFk;
    }

    public Integer getUnidadeFk() {
        return UnidadeFk;
    }

    public void setUnidadeFk(Integer UnidadeFk) {
        this.UnidadeFk = UnidadeFk;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDigito() {
        return digito;
    }

    public void setDigito(Integer digito) {
        this.digito = digito;
    }

}
