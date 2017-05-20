/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.vo;

import br.gov.ce.saude.ponto.util.JsonDateTimeDeserializer;
import br.gov.ce.saude.ponto.util.JsonDateTimeSerializer;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author joao
 */
public class EscalaItemVO implements Serializable {

    private static final long serialVersionUID = -6897496970464531947L;

    private BigInteger id;
    
    private BigInteger funcionarioHorarioFk;
    private BigInteger funcionarioContratoFk;
    
    private Character tipoEscala;
    private List<EscalaItemData> escalaItemList;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    private Date entrada;
    
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)    
    private Date saida;
    
    private BigInteger escala;
    private BigInteger legendaEscala;

    public EscalaItemVO() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public BigInteger getEscala() {
        return escala;
    }

    public void setEscala(BigInteger escala) {
        this.escala = escala;
    }

    public BigInteger getLegendaEscala() {
        return legendaEscala;
    }

    public void setLegendaEscala(BigInteger legendaEscala) {
        this.legendaEscala = legendaEscala;
    }

    public Character getTipoEscala() {
        return tipoEscala;
    }

    public void setTipoEscala(Character tipoEscala) {
        this.tipoEscala = tipoEscala;
    }

    public BigInteger getFuncionarioHorarioFk() {
        return funcionarioHorarioFk;
    }

    public void setFuncionarioHorarioFk(BigInteger funcionarioHorarioFk) {
        this.funcionarioHorarioFk = funcionarioHorarioFk;
    }

    public List<EscalaItemData> getEscalaItemList() {
        return escalaItemList;
    }

    public void setEscalaItemList(List<EscalaItemData> escalaItemList) {
        this.escalaItemList = escalaItemList;
    }

    public BigInteger getFuncionarioContratoFk() {
        return funcionarioContratoFk;
    }

    public void setFuncionarioContratoFk(BigInteger funcionarioContratoFk) {
        this.funcionarioContratoFk = funcionarioContratoFk;
    }
    

    public static class EscalaItemData {

        private BigInteger escalaItemPk;

        private String data_entrada;
        private String hora_entrada;

        private String data_saida;
        private String hora_saida;

        public EscalaItemData() {
        }

        public BigInteger getEscalaItemPk() {
            return escalaItemPk;
        }

        public void setEscalaItemPk(BigInteger escalaItemPk) {
            this.escalaItemPk = escalaItemPk;
        }

        public String getData_entrada() {
            return data_entrada;
        }

        public void setData_entrada(String data_entrada) {
            this.data_entrada = data_entrada;
        }

        public String getHora_entrada() {
            return hora_entrada;
        }

        public void setHora_entrada(String hora_entrada) {
            this.hora_entrada = hora_entrada;
        }

        public String getData_saida() {
            return data_saida;
        }

        public void setData_saida(String data_saida) {
            this.data_saida = data_saida;
        }

        public String getHora_saida() {
            return hora_saida;
        }

        public void setHora_saida(String hora_saida) {
            this.hora_saida = hora_saida;
        }

        
        
        @Override
        public int hashCode() {
            int hash = 5;
            hash = 83 * hash + Objects.hashCode(this.escalaItemPk);
            hash = 83 * hash + Objects.hashCode(this.data_entrada);
            hash = 83 * hash + Objects.hashCode(this.hora_entrada);
            hash = 83 * hash + Objects.hashCode(this.data_saida);
            hash = 83 * hash + Objects.hashCode(this.hora_saida);
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
            final EscalaItemData other = (EscalaItemData) obj;
            if (!Objects.equals(this.escalaItemPk, other.escalaItemPk)) {
                return false;
            }
            if (!Objects.equals(this.data_entrada, other.data_entrada)) {
                return false;
            }
            if (!Objects.equals(this.hora_entrada, other.hora_entrada)) {
                return false;
            }
            if (!Objects.equals(this.data_saida, other.data_saida)) {
                return false;
            }
            if (!Objects.equals(this.hora_saida, other.hora_saida)) {
                return false;
            }
            return true;
        }
        
        
        
    }

}
