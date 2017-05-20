package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Funcionario;
import br.gov.ce.saude.ponto.entidade.Gerador;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author bomba
 *
 * 20/03/2016
 */
@Stateless
@Path("gerador")
public class GeradorFacadeREST extends AbstractFacade<Gerador, ServiceException> {

    public GeradorFacadeREST() {
        super(Gerador.class);
    }

    //cadastra a lista de matriculas
    //Recebe a lista de matrícula do gerarmatriculaPOST
    @GET
    @Path("gerar/{pardigito}/{parquantidade}/{paridfuncionario}/{paridunidade}")
    @Produces({"application/json"})
    public List<Gerador> getGerador(
                                    @PathParam("pardigito") Integer pardigito,
                                    @PathParam("parquantidade") Integer parquantidade,
                                    @PathParam("paridfuncionario") BigInteger paridfuncionario,
                                    @PathParam("paridunidade") Integer paridunidade ) {
        
        List<Gerador> listaGerador = new ArrayList<Gerador>();
        List<String> listaMatricula = gerarmatriculaPOST(pardigito, parquantidade);
        Query query = getEntityManager().createQuery("FROM Funcionario WHERE id=:paridfuncionario");
        query.setParameter("paridfuncionario", paridfuncionario);
        Funcionario f = (Funcionario) query.getSingleResult();
        for (String matricula : listaMatricula) {
            Gerador gerador = new Gerador();
            gerador.setMatricula(matricula);
            gerador.setDigito(pardigito);
            gerador.setQuantidade(parquantidade);
            gerador.setFuncionarioFk(f);
            gerador.setUnidadeFk(paridunidade);
            gerador.setNomeFuncionario(f.getNome());
            super.create(gerador);
            listaGerador.add(gerador);
        }
        return listaGerador;
    }

    //lista todos

    @GET
    @Produces({"application/json"})
    @Override
    public List<Gerador> findAll() {
        List<Gerador> lista = super.findAll();
        return lista;
    }

    //pesquisa por nome monta o combobox
    @GET
    @Path("pesqnome/{parnome}")
    @Produces({"application/json"})
    public List getPesqFuncNome(@PathParam("parnome") String parnome) {
        List pesqFuncNome = PesqFuncNome(parnome);
        return pesqFuncNome;
    }

    //pesquisa por sigla monta o combobox
    @GET
    @Path("pesqsigla/{parsigla}")
    @Produces({"application/json"})
    public List getPesqSigla(@PathParam("parsigla") String parsigla) {
        List pesqSigla = PesqSigla(parsigla);
        return pesqSigla;
    }

    //pesquisa por matricula monta o combobox
    @GET
    @Path("pesqmatricula/{parmatricula}")
    @Produces({"application/json"})
    public List getPesqMatricula(@PathParam("parmatricula") String parmatricula) {
        List pesqMatricula = PesqMatricula(parmatricula);
        return pesqMatricula;
    }

    /*========================================================================================
     ============================   GERADOR DE MATŔICULA    =================================
     Pesquisa  a ultima matrícula baseado no tipo, 1 tercerizado 
     2 cooperado, gerando baseado na quantidade solicitada  
     */
    private List gerarmatriculaPOST(Integer pardigito, Integer parquantidade) {
        //pesquisa a ultima matrícula gerada, digito define se tercerizado ou cooperado
        String hql = "SELECT max(matricula) FROM Gerador WHERE digito = :pardigito";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("pardigito", pardigito);
        String matricula = (String) query.getSingleResult();
        Integer digito = pardigito;
        Integer quantidade = parquantidade;
        Integer contador = 0;
        // retorna desde o primeiro digito até o sexto;
        String prefixoString = matricula.substring(0, 6);
        // retorna o último digito independente do tamanho da string
        String sufixoString = matricula.substring(matricula.length() - 1);
        // convertemos prefixo de string para int para poder incrementá-lo
        // caso sufixoString seja igual a "X"
        int prefixoInt = Integer.parseInt(prefixoString);
        //intera a quantidade de matrículas solicitadas  
        List<String> list = new ArrayList<>();
        while (contador < quantidade) {
            contador++;
            switch (sufixoString) {
                case "9":
                    sufixoString = "X";
                    break;
                case "X":
                    sufixoString = "0";
                    prefixoInt += 1;
                    break;
                default:
                    // convertemos sufixoString para int para poder somá-lo com um
                    // e após a soma o transformamos de novo em string
                    int sufixoInt = Integer.parseInt(sufixoString);
                    sufixoString = String.valueOf(sufixoInt + 1);
                    break;
            }
            String novaMatricula = prefixoInt + "" + digito + "" + sufixoString;
            //retorna a lista de matrícula
            list.add(novaMatricula);
        }
        return list;
    }

    private List<String> PesqFuncNome(String parnome) {
        String hql = ("SELECT w.nome, w.id FROM "
                + "Funcionario w "
                + "WHERE w.nome "
                + "LIKE :parnome "
                + "ORDER BY w.nome");
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("parnome", "%" + parnome.toUpperCase() + "%");
        List<String> lista = query.getResultList();
        return lista;
    }

    public List<String> PesqSigla(String parsigla) {
        String hql = "SELECT s.sigla, s.id FROM "
                + "Organograma s "
                + "WHERE s.sigla "
                + "LIKE :parsigla "
                + "ORDER BY s.sigla";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("parsigla", "%" + parsigla.toUpperCase() + "%");
        List<String> lista = query.getResultList();
        return lista;
    }

    public List<String> PesqMatricula(String parmatricula) {
        String hql = "SELECT s.matricula, s.id FROM "
                + "FuncionarioContrato s "
                + "WHERE s.matricula "
                + "LIKE :parmatricula "
                + "ORDER BY s.matricula";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("parmatricula", "%" + parmatricula.toUpperCase() + "%");
        List<String> lista = query.getResultList();
        return lista;
    }
}
