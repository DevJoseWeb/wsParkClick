    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.entidade.Funcionario;
import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author esmayktillesse
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FuncionarioVO implements Serializable {

    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private List<Funcionario> data;

    public FuncionarioVO() {
    }

    public FuncionarioVO(Integer draw, Integer recordsTotal, Integer recordsFiltered, List<Funcionario> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<Funcionario> getData() {
        return data;
    }

    public void setData(List<Funcionario> data) {
        this.data = data;
    }
}
