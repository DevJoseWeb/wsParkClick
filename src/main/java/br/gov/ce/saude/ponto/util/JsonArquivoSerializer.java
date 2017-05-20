/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.jboss.resteasy.util.Base64;

/**
 *
 * @author esmayktillesse
 */
public class JsonArquivoSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String arq, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        File file = new File("logo.jpg");
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        byte[] data = Base64.decode(arq);
        writer.write(data);
        writer.flush();
        writer.close();

//        File originalFile = new File("signature.jpg");
//        String encodedBase64 = null;
//        try {
//            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
//            byte[] bytes = new byte[(int)originalFile.length()];
//            fileInputStreamReader.read(bytes);
//            encodedBase64 = new String(Base64.encodeBase64(bytes));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        

    }
}
