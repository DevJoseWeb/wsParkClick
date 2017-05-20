package br.gov.ce.saude.ponto.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
/**
 *
 * @author jr
 */
public class JsonDateSerializerbr extends JsonSerializer<Date> {
    
    private static  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        
        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);

    }
}
