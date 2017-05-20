/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Funcionario;
import br.gov.ce.saude.ponto.vo.AcompanhamentoVO;
import br.gov.ce.saude.ponto.vo.DadosUsuario;
import br.gov.ce.saude.ponto.vo.FuncionarioVO;
import br.gov.ce.saude.ponto.vo.PaginacaoVO;
import br.gov.ce.saude.ponto.vo.RelatorioOcorrenciasVO;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author esmayktillesse
 */
@Stateless
@Path("funcionario")
public class FuncionarioFacadeREST extends AbstractFacade<Funcionario, ServiceException> {

    public FuncionarioFacadeREST() {
        super(Funcionario.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Funcionario create(Funcionario entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Funcionario edit(Funcionario entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigInteger id) {
        super.remove(super.find(id));
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Funcionario> findAll() {
        List<Funcionario> lista = super.findAll();
        return lista;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Funcionario getFuncionario(@PathParam("id") BigInteger id) {
        return super.find(id);
    }

    @GET
    @Path("cpf/{cpf}")
    @Produces({"application/json"})
    public List<Funcionario> buscaCpf(@PathParam("cpf") String cpf) {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(cpf);
        List<Funcionario> buscaCpf = getBusiness().listarPorExemplo(funcionario, 0);

        return buscaCpf;
    }

    @GET
    @Path("filtro/{idPessoa}")
    @Produces({"application/json"})
    public FuncionarioVO findRange(@QueryParam("start") int start,
            @QueryParam("length") int length, @QueryParam("search[value]") String nome,
            @QueryParam("draw") int draw, @PathParam("idPessoa") BigInteger idPessoa) {
        FuncionarioVO funcionarioVO = new FuncionarioVO();
        buscaFuncionario(nome, start, length, draw, funcionarioVO, idPessoa);

        return funcionarioVO;
    }

//    @GET
//    @Path("buscaFuncPorUnidade/{idSetor}")
//    @Produces({"application/json"})
//    public BatePontoVO buscaFuncPorUnidade(@PathParam("idSetor") BigInteger idSetor,
//            @QueryParam("start") int start,
//            @QueryParam("length") int length,
//            @QueryParam("search[value]") String nome,
//            @QueryParam("draw") int draw) {
//        BatePontoVO batePontoVO = new BatePontoVO();
//        buscaFuncPorUnidade(nome, idSetor, start, length, draw, batePontoVO);
//
//        return batePontoVO;
//    }
//
//    @GET
//    @Path("buscaFuncPorUnidade")
//    @Produces({"application/json"})
//    public BatePontoVO buscaFuncPorUnidade(@QueryParam("start") int start,
//            @QueryParam("length") int length,
//            @QueryParam("search[value]") String nome,
//            @QueryParam("draw") int draw) {
//        BatePontoVO batePontoVO = new BatePontoVO();
//        BigInteger idSetor = null;
//        buscaFuncPorUnidade(nome, idSetor, start, length, draw, batePontoVO);
//
//        return batePontoVO;
//    }
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    private void buscaFuncionario(String nome, int start,
            int length, int draw, FuncionarioVO funcionarioVO,
            BigInteger idPessoa) {
        String consultaGlobal = "Select count(*) from Hierarquia h \n"
                + " where h.tipo = 'G' and \n"
                + " h.funcionarioContrato.funcionarioFk.id = :pessoa ";
        String cons = "SELECT f FROM Funcionario f\n";
        String count = "SELECT count(*) FROM Funcionario f\n";

        Query query1 = getEntityManager().createQuery(consultaGlobal);
        query1.setParameter("pessoa", idPessoa);
        Long qtdGlobal = (Long) query1.getSingleResult();
        String consulta;
        if (qtdGlobal > 0) {
            consulta = "WHERE 1= 1 and (f.id in\n"
                    + "(SELECT fc.funcionarioFk.id\n"
                    + "FROM FuncionarioContrato fc) or\n"
                    + " (f.id not in\n"
                    + "(SELECT fc1.funcionarioFk.id\n"
                    + "FROM FuncionarioContrato fc1))) \n";
        } else {
            consulta = "WHERE 1= 1 and (f.id in\n"
                    + "(SELECT fc.funcionarioFk.id\n"
                    + "FROM FuncionarioContrato fc \n"
                    + "where (fc.unidadeOrganicaFk.organogramaPk in (select h.organogramaFk.organogramaPk \n"
                    + "                                                 from Hierarquia h \n"
                    + "                                                where h.tipo = 'U' and \n"
                    + "h.funcionarioContrato.funcionarioFk.id = :pessoa)) or \n "
                    + "      (fc.unidadeFuncionalFk.organogramaPk in (select h1.organogramaFk.organogramaPk \n"
                    + "                                               from Hierarquia h1 \n"
                    + "                                                where h1.tipo = 'S' and \n"
                    + "h1.funcionarioContrato.funcionarioFk.id = :pessoa))  \n"
                    + ") or\n"
                    + " (f.id not in\n"
                    + "(SELECT fc1.funcionarioFk.id\n"
                    + "FROM FuncionarioContrato fc1))) \n";
        }

        // Consulta itens total sem filtro
        //      int recordsTotal; //= (Long) queryTodosRegistros.getSingleResult(); // qtd de itens total
        //      int recordsFilter; // = recordsTotal;
        if (nome != null && !nome.equals("")) {
            consulta += "AND f.nome LIKE :nome";
        }
        //obter os dados filtrados
        Query query = getEntityManager().createQuery(count + consulta);
        if (qtdGlobal == 0) {
            query.setParameter("pessoa", idPessoa);
        }

        if (nome != null && !nome.equals("")) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }

        Long recordsTotal = (Long) query.getSingleResult();

        consulta += " ORDER BY\n"
                + "f.nome";
        //obter a quantidade geral de registros        
        query = getEntityManager().createQuery(cons + consulta);
        if (qtdGlobal == 0) {
            query.setParameter("pessoa", idPessoa);
        }

        if (nome != null && !nome.equals("")) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }

        query.setMaxResults(length);
        query.setFirstResult(start);

        List<Funcionario> data = query.getResultList();

        //Integer recordsFilter = data.size(); // qtd de itens total
        funcionarioVO.setDraw(draw++);
        funcionarioVO.setRecordsTotal(recordsTotal.intValue());
        funcionarioVO.setRecordsFiltered(recordsTotal.intValue());
        funcionarioVO.setData(data);

    }

    /**
     *
     * @param idPessoa
     * @param idUnidade
     * @param start
     * @param length
     * @param nome
     * @param draw
     * @return
     */
    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("frequenciadiaria/{idPessoa}")
    @Produces({"application/json"})
    public PaginacaoVO listaOcorrencias(
            @PathParam("idPessoa") BigInteger idPessoa,
            @QueryParam("idUnidade") BigInteger idUnidade,
            @QueryParam("idFuncional") BigInteger idFuncional,
            @QueryParam("start") int start, @QueryParam("length") int length,
            @QueryParam("search[value]") String nome, @QueryParam("draw") int draw) {

        PaginacaoVO paginacaoVO = new PaginacaoVO();
        List<RelatorioOcorrenciasVO> data = null;

        String consulta = "SELECT funcionario_contrato_pk, matricula, nome, categoria,id_unidade, idsetor, unidade, setor\n"
                + "FROM \n"
                + "e_pontows.v_func_por_unidade\n"
                + "WHERE 1 = 1 \n "
                + ((nome != null) ? " AND nome like '%" + nome.toUpperCase() + "%'\n" : "\n")
                + ((idFuncional != null) ? " AND idsetor = " + idFuncional.intValue() + "\n" : "\n")
                + ((idUnidade != null) ? " AND id_unidade = :idUnidade\n" : "\n");

        // Verifica se o usuario é Geral
        String consultaGlobal = "select count(*) from Hierarquia h \n"
                + " where h.tipo = 'G' and \n"
                + " h.funcionarioContrato.funcionarioFk.id = :pessoa ";

        Query query1 = getEntityManager().createQuery(consultaGlobal);
        query1.setParameter("pessoa", idPessoa);

        Long qtdGlobal = (Long) query1.getSingleResult();

        if (qtdGlobal.intValue() == 0) {
            // Inclui as unidades que o usuario tem acesso
            consulta += "AND ( id_unidade IN "
                    + "(SELECT \n"
                    + "  t_unidade_organica_item.organograma_fk\n"
                    + "FROM \n"
                    + "  e_pontows.t_unidade_organica, \n"
                    + "  e_pontows.t_unidade_organica_item, \n"
                    + "  e_pontows.t_hierarquia, \n"
                    + "  e_pontows.t_funcionario_contrato\n"
                    + "WHERE \n"
                    + "  t_unidade_organica.unidade_organica_pk = t_unidade_organica_item.unidade_organica_fk AND\n"
                    + "  t_hierarquia.organograma_fk = t_unidade_organica.organograma_fk AND\n"
                    + "  t_hierarquia.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                    + "  t_funcionario_contrato.pessoa_fk = :idPessoa AND t_hierarquia.tipo='U')\n"
                    + " OR idsetor IN \n"
                    + "  (SELECT \n"
                    + "  t_hierarquia.organograma_fk\n"
                    + "FROM \n"
                    + "  e_pontows.t_hierarquia, \n"
                    + "  e_pontows.t_funcionario_contrato\n"
                    + "WHERE \n"
                    + "  t_hierarquia.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                    + "  t_funcionario_contrato.pessoa_fk = :idPessoa AND \n"
                    + "  t_hierarquia.tipo = 'S')"
                    + ")";
        }

        consulta += "ORDER BY 3,7,4";

        Query query = getEntityManager().createNativeQuery(consulta, "buscaFuncPorUnidade");
        //Query query = getEntityManager().createNativeQuery(consulta);

        Query queryTodosRegistros = null;
        if (idUnidade != null) {
            query.setParameter("idUnidade", idUnidade);
        }

        queryTodosRegistros = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM ( " + consulta + " ) Q");
        if (idUnidade != null) {
            queryTodosRegistros.setParameter("idUnidade", idUnidade);
        }

        if (qtdGlobal.intValue() == 0) {
            query.setParameter("idPessoa", idPessoa);
            queryTodosRegistros.setParameter("idPessoa", idPessoa);
        }

        query.setMaxResults(length);
        query.setFirstResult(start);

        data = query.getResultList();
        //List a = query.getResultList();

        BigInteger filtrados = (BigInteger) queryTodosRegistros.getSingleResult();

        paginacaoVO.setDraw(draw++);
        paginacaoVO.setRecordsTotal(filtrados.intValue()); //paginacaoVO.setRecordsTotal(recordsTotal.size());
        paginacaoVO.setRecordsFiltered(filtrados.intValue()); //paginacaoVO.setRecordsFiltered(recordsFiltered.size());
        paginacaoVO.setData(data);

        return paginacaoVO;
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("acompanhamento/{funcional}/{dia}/{mes}/{ano}")
    @Produces({"application/json"})
    public PaginacaoVO acompanhamentoDiarioPessoa(
            @PathParam("funcional") BigInteger funcional, @PathParam("dia") String dia, @PathParam("mes") String mes, @PathParam("ano") String ano,
            @QueryParam("start") int start, @QueryParam("length") int length,
            @QueryParam("search[value]") String nome, @QueryParam("draw") int draw) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt = dia + "/" + mes + "/" + ano;
        Date date = sdf.parse(dt);
        PaginacaoVO paginacaoVO = new PaginacaoVO();

        String consulta = "SELECT \n"
                + "  fc.funcionario_contrato_pk, \n"
                + "  fc.matricula, \n"
                + "  t_pessoa.nome, \n"
                + "  t_regime.descricao AS regime, \n"
                + "  funcao.descricao AS cargo, \n"
                + "  t_vinculo.descricao AS vinculo, \n"
                + "  t_batida_rel.data AS dt_dia, \n"
                + "  t_batida_rel.batida_1 AS b1, \n"
                + "  t_batida_rel.batida_2 AS b2, \n"
                + "  t_batida_rel.batida_3 AS b3, \n"
                + "  t_batida_rel.batida_4 AS b4  \n"
                + "FROM \n"
                + "  e_pontows.t_funcionario_contrato AS fc\n"
                + "LEFT JOIN \n"
                + "e_pontows.t_funcao funcao ON \n"
                + "funcao.funcao_pk = fc.funcao_fk,\n"
                + "  e_pontows.t_vinculo, \n"
                + "  e_base.t_pessoa,\n"
                + "  e_pontows.t_regime, \n"
                + "  e_pontows.t_batida_rel, \n"
                + "  e_pontows.t_funcionario_horario\n"
                + " \n"
                + "WHERE \n"
                + "  fc.vinculo_fk = t_vinculo.vinculo_pk AND\n"
                + "  fc.pessoa_fk = t_pessoa.pessoa_pk AND\n"
                + "  fc.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_regime.regime_pk = t_funcionario_horario.regime_fk AND\n"
                + "  t_funcionario_horario.funcionario_horario_pk = t_batida_rel.funcionario_horario_fk AND \n"
                + "  fc.unidade_funcional_fk = :funcional AND\n"
                + "  t_batida_rel.data = :date";

        if (nome != null && !nome.equals("")) {
            consulta += " AND t_pessoa.nome LIKE :nome";
        }

        Query query = getEntityManager().createNativeQuery(consulta, "acompanhamento");

        Query queryTodosRegistros = null;

        if (nome != null && !nome.equals("")) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }

        if (funcional != null) {
            query.setParameter("funcional", funcional);
            query.setParameter("date", date);
        }

        queryTodosRegistros = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM ( " + consulta + " ) F");

        if (nome != null && !nome.equals("")) {
            queryTodosRegistros.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }

        if (funcional != null) {
            queryTodosRegistros.setParameter("funcional", funcional);
            queryTodosRegistros.setParameter("date", date);
        }

        query.setMaxResults(length);
        query.setFirstResult(start);

        List<AcompanhamentoVO> data = query.getResultList();
        //List a = query.getResultList();

        BigInteger filtrados = (BigInteger) queryTodosRegistros.getSingleResult();

        paginacaoVO.setDraw(draw++);
        paginacaoVO.setRecordsTotal(filtrados.intValue()); //paginacaoVO.setRecordsTotal(recordsTotal.size());
        paginacaoVO.setRecordsFiltered(filtrados.intValue()); //paginacaoVO.setRecordsFiltered(recordsFiltered.size());
        paginacaoVO.setData(data);

        return paginacaoVO;
    }
//    private void buscaFuncPorUnidade(String nome, BigInteger idSetor, int start, int length, int draw, BatePontoVO batePontoVO) {
//
//        String consulta = "SELECT *\n"
//                + "FROM \n"
//                + "e_pontows.vm_func_por_unidade\n"
//                + "WHERE 1 = 1";
//
//        String todosRegistros = "SELECT COUNT(*)\n"
//                + "FROM \n"
//                + "e_pontows.vm_func_por_unidade\n"
//                + "WHERE 1 = 1";
//
//        BigInteger recordsFilter = null;
//
//        if (nome != null && !nome.equals("")) {
//            consulta += " AND nome LIKE :nome";
//            todosRegistros += " AND nome LIKE :nome";
//        }
//
//        Integer[] funcional = {
//            12001, 12002, 12005, 12006, 12009, 12010, 12011, 12013, 12014, 12023,
//            12029, 12030, 12031, 12033, 12034, 12035, 12036, 12038, 12039, 12041,
//            12044, 12045, 12046, 12047, 12049, 12052, 12053, 12056, 12059, 12061,
//            12062, 12065, 12068, 12071, 12072, 12073, 12074, 12075, 12524, 12529,
//            12546, 12547, 12548, 12550, 12551, 12554, 12555, 12556, 12557, 12558,
//            12559, 12567, 12568, 12573, 12574, 16467, 16501, 16502, 12086, 12097,
//            12102, 12107, 12122, 12127, 12140, 12164, 12169, 12174, 12184, 15909,
//            15394, 15806, 15856, 15693, 15748, 15569, 15460, 14731, 14677, 14348,
//            14391, 15158, 13267, 13314, 12921, 16470, 12026, 12027, 12028, 12196};
//
//        ArrayList<Integer> listaFuncional = new ArrayList<>(Arrays.asList(funcional));
//
//        Integer[] organograma = {
//            12000, 12076, 12111, 12116, 12132, 12145, 12153, 12158, 12179, 12190,
//            12205, 12213, 12216, 12219, 12220, 12221, 12228, 12231, 12234, 12237,
//            12246, 12256, 12263, 12324, 12351, 12370, 12416, 12455, 12528, 12531,
//            12544, 12545, 12569, 12470};
//
//        ArrayList<Integer> listaOrganograma = new ArrayList<>(Arrays.asList(organograma));
//
//        boolean achou = false;
//        int test = 0;
//        if (idSetor != null) {
//            for (Integer ltf : listaFuncional) {
//                if (ltf.equals(idSetor.intValue())) {
//                    achou = true;
//                    test = 1;
//                    break;
//                }
//            }
//            if (achou == false) {
//
//                for (Integer lto : listaOrganograma) {
//                    if (lto.equals(idSetor.intValue())) {
//                        achou = true;
//                        test = 2;
//                        break;
//                    }
//                }
//            }
//            if (idSetor != null && test == 1) {
//                consulta += " AND idSetor = :idSetor";
//                todosRegistros += " AND idSetor = :idSetor";
//            }
//            if (idSetor != null && test == 2) {
//                consulta += " AND id_unidade = :idSetor";
//                todosRegistros += " AND id_unidade = :idSetor";
//            }
//            consulta += " ORDER BY\n"
//                    + "nome";
//        }
//        Query query = getEntityManager().createNativeQuery(consulta, "buscaFuncPorUnidade");
//        Query queryTodosRegistros = getEntityManager().createNativeQuery(todosRegistros);
//
//        if (nome != null && !nome.equals("")) {
//            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
//            queryTodosRegistros.setParameter("nome", "%" + nome.toUpperCase() + "%");
//        }
//        if (idSetor != null) {
//            query.setParameter("idSetor", idSetor);
//            queryTodosRegistros.setParameter("idSetor", idSetor);
//        }
//        Object recordsTotal = queryTodosRegistros.getSingleResult(); // qtd de itens total
//        recordsFilter = (BigInteger) recordsTotal;
//
//        query.setMaxResults(length);
//        query.setFirstResult(start);
//
//        List<BatePontoVO> data = query.getResultList();
//
//        batePontoVO.setDraw(draw++);
//        batePontoVO.setRecordsTotal(recordsFilter.intValue());
//        batePontoVO.setRecordsFiltered(recordsFilter.intValue());
//        batePontoVO.setData(data);
//    }

    @GET
    @Path("dadosusuario/{funcContrato}")
    @Produces({"application/json"})
    public DadosUsuario listaDadosUsuario(@PathParam("funcContrato") BigInteger funcContrato
    ) {
        String consulta = "SELECT \n"
                + "  t_funcao.descricao AS cargo, \n"
                + "  t_vinculo.descricao AS vinculo, \n"
                + "  organica.sigla AS unid_org, \n"
                + "  funcional.sigla AS unid_func, \n"
                + "  t_pessoa.nome, \n"
                + "  t_funcionario_contrato.matricula\n"
                + "FROM \n"
                + "  (e_pontows.t_funcionario_contrato LEFT JOIN e_pontows.t_vinculo ON t_vinculo.vinculo_pk = t_funcionario_contrato.vinculo_fk)   \n"
                + "  LEFT JOIN e_pontows.t_funcao ON t_funcao.funcao_pk = t_funcionario_contrato.funcao_fk \n"
                + "  LEFT JOIN e_base.t_organograma funcional ON funcional.organograma_pk = t_funcionario_contrato.unidade_funcional_fk\n"
                + "  LEFT JOIN e_base.t_organograma organica ON organica.organograma_pk = t_funcionario_contrato.unidade_organica_fk, \n"
                + "  e_base.t_pessoa\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.pessoa_fk = t_pessoa.pessoa_pk AND\n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = :funcContrato";

        Query query = getEntityManager().
                createNativeQuery(consulta, "buscaDadosUsuario");
        query.setParameter("funcContrato", funcContrato);

        DadosUsuario dadosUsuari = (DadosUsuario) query.getSingleResult();

        return dadosUsuari;
    }
}
