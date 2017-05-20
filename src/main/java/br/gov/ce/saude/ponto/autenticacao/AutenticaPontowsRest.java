/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.autenticacao;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.MessageInfo;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Acesso;
import br.gov.ce.saude.ponto.vo.OauthCodigo;
import br.gov.ce.saude.ponto.vo.UsuarioVO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author joao
 */
@Stateless
@Path("login")
public class AutenticaPontowsRest extends AbstractFacade<Acesso, ServiceException> {

    public AutenticaPontowsRest() {
        super(Acesso.class);
    }

    @POST
    @Path("trocarsenha")
    @Produces({"application/json"})
    public MessageInfo trocarSenha(Acesso acesso) {
        MessageInfo retorno = new MessageInfo();

        Acesso usuarioLogado = null;
        try {
            usuarioLogado = (Acesso) getEntityManager().createQuery("FROM Acesso WHERE "
                    + "login=:login AND senha=:senha").
                    setParameter("login", acesso.getLogin().toUpperCase()).
                    setParameter("senha", md5(acesso.getSenha().toUpperCase())).getSingleResult();
    
            usuarioLogado.setSenha(md5(acesso.getNovaSenha().toUpperCase()));
            super.edit(usuarioLogado);
            
            retorno.setMensagemInfo("Senha atualizada com sucesso.");
        } catch (Exception e) {
            getBusiness().addErrorMessage(null, "Usuário ou senha inválido(s)");
            getBusiness().lancarException();
        }
        
        return retorno;
    }

    @POST
    @Consumes({"application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public String login(OauthCodigo entity) throws Exception {

        // Autentica login e senha
        autentica(entity);

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8087/oauth/webresources/novotoken");

        String resultado = null;
        try {
            resultado = webTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(entity.getLogin().toUpperCase(),
                                    MediaType.APPLICATION_JSON), String.class);

            JSONObject retornoJson = new JSONObject(resultado);
            byte[] decoded = Base64.decodeBase64(retornoJson.getString("token"));
            String tokenDecoded = new String(decoded, "UTF-8");
            String login = tokenDecoded.split("_")[1];

        } catch (Exception e) {
            getBusiness().addErrorMessage(null, "Usuário ou senha inválido(s)");
            getBusiness().lancarException();
        }
        return resultado;
    }

    private void autentica(OauthCodigo entity) {
        try {
            String usuario = entity.getLogin();
            String senha = entity.getSenha();
            if (usuario == null) {
                usuario = "";
            }
            if (senha == null) {
                senha = "";
            }

            String usuarioLogado = (String) getEntityManager().createNativeQuery("SELECT login FROM e_pontows.t_acesso where login=:login AND senha=:senha").
                    setParameter("login", usuario.toUpperCase()).
                    setParameter("senha", md5(senha.toUpperCase())).getSingleResult();

        } catch (Exception e) {
            getBusiness().addErrorMessage(null, "Usuario não encontrado (Usuario ou Senha incorreto(s)).");
            getBusiness().lancarException();
        }
    }

    public static String md5(String value) {

        final StringBuilder sbMd5Hash = new StringBuilder();
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final byte data[] = m.digest();

        for (byte element : data) {
            sbMd5Hash.append(Character.forDigit((element >> 4) & 0xf, 16));
            sbMd5Hash.append(Character.forDigit(element & 0xf, 16));
        }

        return sbMd5Hash.toString();
    }

    @GET
    @Path("token/{token}")
    @Produces({"application/json"})
    public UsuarioVO find(@PathParam("token") String token) {

        UsuarioVO usuarioVO = null;
        try {
            String login = null;
            byte[] decoded = Base64.decodeBase64(token);
            String tokenDecoded = new String(decoded, "UTF-8");
            if (tokenDecoded.split("_").length > 1) {
                login = tokenDecoded.split("_")[1];
            }

            String query = "SELECT t_acesso.login, t_funcionario_contrato.matricula, t_pessoa.nome\n"
                    + " FROM e_pontows.t_acesso, e_pontows.t_funcionario_contrato, e_base.t_pessoa\n"
                    + " WHERE t_acesso.login = t_funcionario_contrato.matricula AND t_funcionario_contrato.pessoa_fk = t_pessoa.pessoa_pk AND\n"
                    + "  t_acesso.login = :login";

            usuarioVO = (UsuarioVO) getEntityManager().
                    createNativeQuery(query, "usuarioToken").setParameter("login", login)
                    .getSingleResult();

        } catch (Exception e) {
            getBusiness().addErrorMessage(null, "Token inválido.");
            getBusiness().lancarException();
        }

        return usuarioVO;
    }

}
