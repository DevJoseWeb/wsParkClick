/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

/**
 *
 * @author joao
 */
import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.BatidaEscalaRel;
import br.gov.ce.saude.ponto.entidade.BatidaRel;
import br.gov.ce.saude.ponto.entidade.EscalaItem;
import br.gov.ce.saude.ponto.entidade.FuncionarioHorario;
import br.gov.ce.saude.ponto.entidade.JustificativaFuncionario;
import br.gov.ce.saude.ponto.entidade.TipoJustificativa;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("/justificativabatida")
public class JustificativaFileUploadService extends AbstractFacade<JustificativaFuncionario, ServiceException> {

    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "/opt/wildfly/imagensJustificativas/";
    private static final int MAX_FILE_SIZE = 5000000; // 5MG

    private SimpleDateFormat format_dd_MM_yyyy = new SimpleDateFormat("dd/MM/yyyy");

    public JustificativaFileUploadService() {
        super(JustificativaFuncionario.class);
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") BigInteger id) {
        try {
            JustificativaFuncionario jb = super.find(id);
            //jb.setBatidarelFk(null);
            getBusiness().getSesaDao().getEntityManager().remove(jb);
            getBusiness().getSesaDao().getEntityManager().flush();
            File f = new File(SERVER_UPLOAD_LOCATION_FOLDER + File.separator + jb.getImagem());
            if (f.exists()) {
                f.delete();
            }
            //super.remove(super.find(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"ok\":\"ok\"}";
    }

    @GET
    @Path("imagem/{arquivo}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response find(@PathParam("arquivo") String arquivo) throws ParseException {
        File file = new File(SERVER_UPLOAD_LOCATION_FOLDER + File.separator + arquivo);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=" + arquivo);
        return response.build();
    }

    private String getImputString(String imput, Map<String, List<InputPart>> multiMap) throws Exception {
        String saida = null;
        if (multiMap.get(imput) != null && !multiMap.get(imput).isEmpty()) {
            saida = multiMap.get(imput).get(0).getBodyAsString();
        }
        return saida;
    }

    @Transactional
    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadFile(MultipartFormDataInput input) throws Exception {

        String filePath = "";
        String fileName = "";
        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        BatidaRel batidaRel = null;
        BatidaEscalaRel batidaEscalaRel = null;
        EscalaItem escalaItem = null;

        List<InputPart> inPart = formParts.get("file[]");

        String batidaRelPk = getImputString("batidaRel", formParts);
        String batidaEscalaRelPk = getImputString("batidaEscalaRelPk", formParts);
        String escalaItemPk = getImputString("escalaItemPk", formParts);

        String justificativa = getImputString("justificativa", formParts);
        if (justificativa != null) {
            justificativa = new String(justificativa.getBytes("UTF-8"));
        }

        String data = getImputString("data", formParts);
        String tipoJustificativa = getImputString("tipoJustificativa", formParts);
        String matricula = getImputString("matricula", formParts);

        String token = getImputString("token", formParts); // Validar se o token é valido

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8087/oauth/webresources/oauth");

        JSONObject jsonServico = new JSONObject();
        jsonServico.put("token", token);
        jsonServico.put("servicoAberto", 17);

        String resultado = null;
        try {
            resultado = webTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(jsonServico.toString(),
                                    MediaType.APPLICATION_JSON), String.class);
        } catch (Exception e) {
            getBusiness().addErrorMessage(null, "Token Inválido");
            getBusiness().lancarException();
        }
        jsonServico = new JSONObject(resultado);

        boolean tokenValido = (Boolean) jsonServico.get("tokenValido");

        if (tokenValido) {

            batidaRel = null;
            if (batidaRelPk != null && batidaRelPk.replaceAll(" ", "").length() > 0) {
                batidaRel = (BatidaRel) getEntityManager().createQuery("from BatidaRel where id=:id").
                        setParameter("id", new BigInteger(batidaRelPk)).getSingleResult();
            } else if (batidaEscalaRelPk != null && batidaEscalaRelPk.replaceAll(" ", "").length() > 0) {
                batidaEscalaRel = (BatidaEscalaRel) getEntityManager().createQuery("from BatidaEscalaRel where id=:id").
                        setParameter("id", new BigInteger(batidaEscalaRelPk)).getSingleResult();
            } else if (escalaItemPk != null && escalaItemPk.replaceAll(" ", "").length() > 0) {
                escalaItem = (EscalaItem) getEntityManager().createQuery("from EscalaItem where id=:id").
                        setParameter("id", new BigInteger(escalaItemPk)).getSingleResult();
                try {
                    batidaEscalaRel = (BatidaEscalaRel) getEntityManager().createQuery("from BatidaEscalaRel where escalaItemFk.id=:id").
                            setParameter("id", new BigInteger(escalaItemPk)).getSingleResult();
                } catch (Exception e) {
                }

                if (batidaEscalaRel == null) {
                    batidaEscalaRel = new BatidaEscalaRel();
                    batidaEscalaRel.setEscalaItemFk(escalaItem);
                }

            } else {
                FuncionarioHorario funcionarioHor = null;
                try {
                    funcionarioHor = (FuncionarioHorario) getEntityManager().createQuery("from FuncionarioHorario fh "
                            + " where fh.funcionarioContrato.matricula=:matricula AND fh.ativo=true").
                            setParameter("matricula", matricula).getSingleResult();
                } catch (Exception ex) {
                    getBusiness().addErrorMessage(null, "O usuário não possui horário cadastrado.");
                    getBusiness().lancarException();
                }

                batidaRel = new BatidaRel();
                batidaRel.setData(format_dd_MM_yyyy.parse(data));
                batidaRel.setFuncionarioHorario(funcionarioHor);
                //getEntityManager().persist(batidaRel);
            }

            if (tipoJustificativa != null) {
                TipoJustificativa tipoJustificativaAux = (TipoJustificativa) getEntityManager().createQuery("from TipoJustificativa where id=:id").
                        setParameter("id", new BigInteger(tipoJustificativa)).getSingleResult();

                if (batidaRel != null) {
                    batidaRel.setTipoJustificativa(tipoJustificativaAux);
                } else {
                    batidaEscalaRel.setTipoJustificativa(tipoJustificativaAux);
                }

            }

            if (batidaRel != null) {
                batidaRel.setJustificativa(justificativa);
            } else {
                batidaEscalaRel.setJustificativa(justificativa);
            }

//            if (tipoJustificativa != null) {
//                batidaRel.setTipoJustificativa((TipoJustificativa) getEntityManager().createQuery("from TipoJustificativa where id=:id").
//                        setParameter("id", new BigInteger(tipoJustificativa)).getSingleResult());
//            }
            if (inPart != null) {
                for (InputPart inputPart : inPart) {
                    try {
                        // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
                        MultivaluedMap<String, String> headers = inputPart.getHeaders();
                        filePath = parseFileName(headers);
                        if (filePath != null && filePath.trim().equals("")) {
                            break;
                        }
                        filePath = new Date().getTime() + filePath;
                        fileName = filePath.toString();
                        String mediaType = inputPart.getMediaType().toString();
                        if (mediaType != null && !mediaType.contains("text/plain")) {

                            if (!(mediaType.equals("application/pdf") || mediaType.equals("image/png")
                                    || mediaType.equals("image/jpg") || mediaType.equals("image/jpeg")
                                    || mediaType.equals("image/bmp"))) {
                                getBusiness().addErrorMessage(null, "Somente arquivos do tipo pdf, png, jpq e bmp são aceitos !");
                                getBusiness().lancarException();
                            }
                            // Handle the body of that part with an InputStream
                            InputStream istream = inputPart.getBody(InputStream.class, null);

                            filePath = SERVER_UPLOAD_LOCATION_FOLDER + filePath;

                            saveFile(istream, filePath);

                            JustificativaFuncionario justificativaBatidarel = new JustificativaFuncionario();

                            if (batidaRel != null) {
                                justificativaBatidarel.setBatidarelFk(batidaRel);
                            } else {
                                justificativaBatidarel.setBatidarelEscalaFk(batidaEscalaRel);
                            }

                            justificativaBatidarel.setImagem(fileName);
                            justificativaBatidarel.setMimetype(inputPart.getMediaType().toString());

                            if (batidaRel != null) {
                                if (batidaRel.getJustificativaBatidas() == null) {
                                    batidaRel.setJustificativaBatidas(new ArrayList<JustificativaFuncionario>());
                                }
                                batidaRel.getJustificativaBatidas().add(justificativaBatidarel);
                            } else {
                                if (batidaEscalaRel.getJustificativaBatidas() == null) {
                                    batidaEscalaRel.setJustificativaBatidas(new ArrayList<JustificativaFuncionario>());
                                }
                                batidaEscalaRel.getJustificativaBatidas().add(justificativaBatidarel);
                            }
                        }
                    } catch (IOException e) {
                        getBusiness().addErrorMessage(null, e.getMessage());
                        getBusiness().lancarException();
                    }

                }
            }

            if (batidaRel != null) {
                getBusiness().getSesaDao().getEntityManager().persist(batidaRel);
            } else {
                getBusiness().getSesaDao().getEntityManager().persist(batidaEscalaRel);
            }

        } else {
            getBusiness().addErrorMessage(null, "Token Inválido");
            getBusiness().lancarException();
        }

        if (batidaRel != null) {
            return batidaRel;
        } else {
            return batidaEscalaRel;
        }

    }

    // Parse Content-Disposition header to get the original file name
    private String parseFileName(MultivaluedMap<String, String> headers) {

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {

            if ((name.trim().startsWith("filename"))) {

                String[] tmp = name.split("=");

                String fileName = tmp[1].trim().replaceAll("\"", "");

                return fileName;
            }
        }
        return "randomName";
    }

    // save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream,
            String serverLocation) {

        OutputStream outpuStream = null;
        try {
            outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            int i = MAX_FILE_SIZE / 1024;
            File arquivo = new File(serverLocation);
            outpuStream = new FileOutputStream(arquivo);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                if (i < 0) {
                    if (arquivo.exists()) {
                        arquivo.delete();
                    }
                    getBusiness().addErrorMessage(null, "O arquivo deve ser menor que 3MG");
                    getBusiness().lancarException();
                }
                outpuStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            getBusiness().addErrorMessage(null, e.getMessage());
            getBusiness().lancarException();
        } finally {
            try {
                if (outpuStream != null) {
                    outpuStream.flush();
                    outpuStream.close();
                }
            } catch (IOException e) {
                getBusiness().addErrorMessage(null, e.getMessage());
                getBusiness().lancarException();
            }

        }
    }
}
