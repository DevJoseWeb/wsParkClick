/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.util;


import br.gov.ce.autenticacao.core.SesaModel;
import br.gov.ce.saude.ponto.entidade.Funcionario;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;


/**
 *
 * @author esmayktillesse
 */
@Component
public class JsonEntidadeFkSerializer extends JsonSerializer<SesaModel> {

    @Override
    public void serialize(SesaModel t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
      jg.writeStartObject();
      jg.writeStringField("id", t.getId().toString());
      jg.writeEndObject();        
    }

}
