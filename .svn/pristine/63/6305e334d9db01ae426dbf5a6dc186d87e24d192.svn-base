/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Funcionario;
import br.gov.ce.saude.ponto.entidade.FuncionarioContrato;
import br.gov.ce.saude.ponto.entidade.FuncionarioHorario;
import br.gov.ce.saude.ponto.entidade.Regime;
import br.gov.ce.saude.ponto.entidade.Vinculo;
import br.gov.ce.saude.ponto.util.TipoRegime;
import br.gov.ce.saude.ponto.vo.ContratoVO;
import br.gov.ce.saude.ponto.vo.FuncionarioRegimeVO;
import br.gov.ce.saude.ponto.vo.PaginacaoVO;
import java.math.BigInteger;
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
@Path("contrato")
public class FuncionarioContratoFacadeREST extends AbstractFacade<FuncionarioContrato, ServiceException> {

    public FuncionarioContratoFacadeREST() {
        super(FuncionarioContrato.class);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public FuncionarioContrato create(FuncionarioContrato entity) {

        // Verifica matricula repetida 
        if (entity.getMatricula() != null && matriculaJaCadastrada(entity.getMatricula())) {
            getBusiness().addErrorMessage(null, "A matrícula '" + entity.getMatricula() + "' já esta em uso.");
            getBusiness().lancarException();
        }

        return super.create(entity);
    }

    private Boolean matriculaJaCadastrada(String matricula) {

        if (matricula != null && matricula.replaceAll(" ", "").length() > 0) {
            String consulta = "SELECT matricula FROM e_pontows.t_funcionario_contrato where matricula = :matricula";
            Query query = getEntityManager().createNativeQuery(consulta);
            List matriculaUsada = query.setParameter("matricula", matricula).getResultList();
            if (matriculaUsada != null && !matriculaUsada.isEmpty()) {
                return true; // Matricula esta usada
            }
        }
        return false;
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public FuncionarioContrato edit(FuncionarioContrato entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigInteger id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{cod}")
    @Produces({"application/json"})
    public List<FuncionarioContrato> buscaContrato(@PathParam("cod") BigInteger cod) {
        FuncionarioContrato fcon = new FuncionarioContrato();
        fcon.setFuncionarioFk(new Funcionario());
        fcon.getFuncionarioFk().setId(cod);
        fcon.setVinculoFk(new Vinculo());
        //fcon.getVinculoFk().setId(new BigInteger("12"));//Só traz os tercerizados.
        List<FuncionarioContrato> buscafunc = null;
        buscafunc = getBusiness().listarPorExemplo(fcon, 0);

        return buscafunc;
    }

    @POST
    @Path("/salvaregime")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public FuncionarioHorario create(FuncionarioRegimeVO funcRegime) {
        FuncionarioHorario funcionarioHorario = null;

        try {
            if (funcRegime.getFuncionarioContratoPk() != null) {
                Query query = getEntityManager().
                        createQuery("FROM FuncionarioHorario WHERE funcionarioContrato.id =:funcontrato AND ativo=true");
                query.setParameter("funcontrato", funcRegime.getFuncionarioContratoPk());

                try {
                    funcionarioHorario = (FuncionarioHorario) query.getSingleResult();
                } catch (Exception e) {
                }

                Query queryContrato = getEntityManager().
                        createQuery("FROM FuncionarioContrato WHERE id =:funcontrato");
                queryContrato.setParameter("funcontrato", funcRegime.getFuncionarioContratoPk());
                FuncionarioContrato funcionarioContrato = (FuncionarioContrato) queryContrato.getSingleResult();

                BigInteger codigoRegime = TipoRegime.getId(funcRegime.getDiarista(), funcRegime.getPlantonista(), funcRegime.getExtra());
                Query queryRegime = getEntityManager().createQuery("FROM Regime WHERE id =:idregime");
                queryRegime.setParameter("idregime", codigoRegime);
                Regime regime = (Regime) queryRegime.getSingleResult();

                if (funcionarioHorario == null) {
                    funcionarioHorario = new FuncionarioHorario();
                }
                funcionarioHorario.setAtivo(true);
                funcionarioHorario.setFuncionarioContrato(funcionarioContrato);
                funcionarioHorario.setRegimeFk(regime);

                getBusiness().getSesaDao().getEntityManager().persist(funcionarioHorario);
            }

        } catch (Exception e) {
            getBusiness().addErrorMessage(null, e.getMessage());
            getBusiness().lancarException();
        }

        return funcionarioHorario;
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("funcionario/{idPessoa}")
    @Produces({"application/json"})
    public PaginacaoVO buscaFuncionario(@QueryParam("start") int start,
            @QueryParam("length") int length, @QueryParam("search[value]") String nome,
            @QueryParam("draw") int draw, @PathParam("idPessoa") BigInteger idPessoa) {

        List<ContratoVO> data = null; // Somente os dados que serão mostrados
        PaginacaoVO paginacaoVO = new PaginacaoVO();

        Query queryTodosRegistros = null; // A qtd de todos os registros 
        List<ContratoVO> recordsTotal = null;

        Query query = null; // 
        List<ContratoVO> recordsFiltered = null; // O total dos filtrados 

        String consulta = ""
                + "SELECT B.*\n"
                + "FROM\n"
                + "(SELECT \n"
                + "  DISTINCT(funcionario_contrato_fk), \n"
                + "  funcionario_horario_pk, \n"
                + "  horario_fk, \n"
                + "  nome, \n"
                + "  matricula, \n"
                + "  nme_fantasia as sigla, \n"
                + "  \n"
                + "  diarista,\n"
                + "  plantonista,\n"
                + "  horaExtra\n"
                + "FROM \n"
                + "(SELECT \n"
                + "  t_funcionario_horario.funcionario_contrato_fk, \n"
                + "  t_funcionario_horario.funcionario_horario_pk, \n"
                + "  t_funcionario_horario.horario_fk, \n"
                + "  v_funcionarios_unidade_organica.nome, \n"
                + "  v_funcionarios_unidade_organica.matricula, \n"
                + "  v_funcionarios_unidade_organica.nme_fantasia,   \n"
                + "  CASE \n"
                + "     WHEN t_regime.regime_pk=1 OR t_regime.regime_pk=4 OR t_regime.regime_pk=5 OR t_regime.regime_pk=7 THEN 'X' \n"
                + "     ELSE ''\n"
                + "END as diarista, \n"
                + "CASE \n"
                + "     WHEN t_regime.regime_pk=2 OR t_regime.regime_pk=4 OR t_regime.regime_pk=6 OR t_regime.regime_pk=7 THEN 'X' \n"
                + "     ELSE '' \n"
                + "END as plantonista,\n"
                + "CASE \n"
                + "     WHEN t_regime.regime_pk=3 OR t_regime.regime_pk=5 OR t_regime.regime_pk=6 OR t_regime.regime_pk=7 THEN 'X' \n"
                + "     ELSE ''\n"
                + "END as horaExtra\n"
                + "FROM \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_hierarquia, \n"
                + "  e_pontows.t_unidade_organica, \n"
                + "  e_pontows.t_unidade_organica_item, \n"
                + "  e_pontows.v_funcionarios_unidade_organica, \n"
                + "  e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_regime\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.situacao_funcional_fk = 1 AND "
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_hierarquia.funcionario_contrato_fk AND\n"
                + "  t_hierarquia.organograma_fk = t_unidade_organica.organograma_fk AND\n"
                + "  t_unidade_organica_item.unidade_organica_fk = t_unidade_organica.unidade_organica_pk AND\n"
                + "  v_funcionarios_unidade_organica.organograma_pk = t_unidade_organica_item.organograma_fk AND\n"
                + "  t_funcionario_horario.funcionario_contrato_fk = v_funcionarios_unidade_organica.funcionario_contrato_pk AND\n"
                + "  t_funcionario_horario.regime_fk = t_regime.regime_pk AND\n"
                + "  t_funcionario_horario.ativo = true AND \n"
                + "  t_funcionario_contrato.pessoa_fk = :idPessoa AND \n"
                + "  t_hierarquia.tipo IN ('G', 'U')) AS A\n"
                + "UNION\n"
                + "(SELECT \n"
                + "  t_funcionario_horario.funcionario_contrato_fk, \n"
                + "  t_funcionario_horario.funcionario_horario_pk, \n"
                + "  t_funcionario_horario.horario_fk, \n"
                + "  t_pessoa.nome, \n"
                + "  t_funcionario_contrato.matricula, \n"
                + "  t_organograma.sigla,   \n"
                + "  CASE \n"
                + "     WHEN t_regime.regime_pk=1 OR t_regime.regime_pk=4 OR t_regime.regime_pk=5 OR t_regime.regime_pk=7 THEN 'X' \n"
                + "     ELSE ''\n"
                + "END as diarista, \n"
                + "CASE \n"
                + "     WHEN t_regime.regime_pk=2 OR t_regime.regime_pk=4 OR t_regime.regime_pk=6 OR t_regime.regime_pk=7 THEN 'X' \n"
                + "     ELSE '' \n"
                + "END as plantonista,\n"
                + "CASE \n"
                + "     WHEN t_regime.regime_pk=3 OR t_regime.regime_pk=5 OR t_regime.regime_pk=6 OR t_regime.regime_pk=7 THEN 'X' \n"
                + "     ELSE ''\n"
                + "END as horaExtra\n"
                + "FROM \n"
                + "  e_pontows.t_hierarquia, \n"
                + "  e_pontows.t_funcionario_contrato gestor, \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_regime, \n"
                + "  e_base.t_pessoa, \n"
                + "  e_base.t_organograma\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.situacao_funcional_fk = 1 AND "
                + "  gestor.funcionario_contrato_pk = t_hierarquia.funcionario_contrato_fk AND\n"
                + "  t_funcionario_contrato.unidade_funcional_fk = t_hierarquia.organograma_fk AND\n"
                + "  t_funcionario_contrato.pessoa_fk = t_pessoa.pessoa_pk AND\n"
                + "  t_funcionario_contrato.unidade_funcional_fk = t_organograma.organograma_pk AND\n"
                + "  t_funcionario_horario.funcionario_contrato_fk = t_funcionario_contrato.funcionario_contrato_pk AND\n"
                + "  t_funcionario_horario.regime_fk = t_regime.regime_pk AND\n"
                + "  t_funcionario_horario.ativo = true AND\n"
                + "  t_hierarquia.tipo = 'S' AND   \n"
                + "  gestor.pessoa_fk = :idPessoa) \n"
                + "  ) B\n"
                + "  WHERE 1=1 ";

        // Consulta itens total sem filtro
        queryTodosRegistros = getEntityManager().createNativeQuery(consulta, "funciorioContrato");
        recordsTotal = queryTodosRegistros.setParameter("idPessoa", idPessoa).
                getResultList(); // qtd de itens total

        if (nome != null && !nome.equals("")) {
            consulta += " AND nome like :nome";
        }

        consulta += " ORDER BY\n"
                + "   nome ASC";

        query = getEntityManager().createNativeQuery(consulta, "funciorioContrato");

        if (nome != null && !nome.equals("")) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }

        recordsFiltered = query.setParameter("idPessoa", idPessoa).getResultList(); // qtd de itens filtrados

        query.setMaxResults(length);
        query.setFirstResult(start);

        data = query.setParameter("idPessoa", idPessoa).getResultList();

        paginacaoVO.setDraw(draw++);
        paginacaoVO.setRecordsTotal(recordsTotal.size());
        paginacaoVO.setRecordsFiltered(recordsFiltered.size());
        paginacaoVO.setData(data);

        return paginacaoVO;
    }

    /**
     * Lista funcionarios e seu numero de ocorrencias
     *
     */
    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("justificativagestor/{idPessoa}/{periodo}")
    @Produces({"application/json"})
    public PaginacaoVO funcJustificativaGestor2(@QueryParam("start") int start,
            @QueryParam("length") int length, @QueryParam("search[value]") String nome,
            @PathParam("idPessoa") BigInteger idPessoa, @QueryParam("draw") int draw,
            @PathParam("periodo") String periodo, @QueryParam("idUnidade") BigInteger idUnidade, 
            @QueryParam("idVinculo") BigInteger idVinculo, @QueryParam("idFuncao") BigInteger idFuncao, 
            @QueryParam("idFuncional") BigInteger idFuncional ) {

        List data = null; // Somente os dados que serão mostrados
        PaginacaoVO paginacaoVO = new PaginacaoVO();

        Query queryTodosRegistros = null; // A qtd de todos os registros         
        Query query = null; // 
        
        // Verifica se o usuario é Geral   :primeiroDia  and  :ultimoDia
        String consultaGlobal = "select count(*) from Hierarquia h \n"
                + " where h.tipo = 'G' and \n"
                + " h.funcionarioContrato.funcionarioFk.id = :idPessoa ";

        Query query1 = getEntityManager().createQuery(consultaGlobal);
        query1.setParameter("idPessoa", idPessoa);
        Long qtdGlobal = (Long) query1.getSingleResult();

        String consulta = ""
                + "select funcionario_contrato_pk,\n"
                + "matricula, nome, unidade,ocorrencia,diarista, plantonista, horaExtra,\n"
                + "(select descricao FROM e_pontows.t_vinculo WHERE vinculo_pk = vinculo_fk) as vinculo,\n"
                + "(select descricao FROM e_pontows.t_funcao WHERE funcao_pk = funcao_fk) as funcao\n"
                + "FROM \n"
                + "( select funcionario_contrato as funcionario_contrato_pk, \n"
                + "  (select vinculo_fk FROM e_pontows.t_funcionario_contrato WHERE funcionario_contrato_pk = funcionario_contrato) as vinculo_fk,\n"
                + "  (select funcao_fk FROM e_pontows.t_funcionario_contrato WHERE funcionario_contrato_pk = funcionario_contrato) as funcao_fk,\n"
                + "  matricula, nome, unidade, ocorrencia,diarista, plantonista, horaExtra, "
                + "  id_unidade, unidade_funcional\n"
                + "  FROM\n"
                + "  e_pontows.vm_func_processado p WHERE to_char(p.periodo_inicial, 'MM/yyyy') = :periodo  \n"
                + "  and  to_char(p.periodo_final, 'MM/yyyy') = :periodo ) a\n"
                + "WHERE 1=1\n" 
                + ((idUnidade != null)?" AND id_unidade = "+idUnidade.intValue()+"\n":"\n")
                + ((idVinculo != null)?" AND vinculo_fk = "+idVinculo.intValue()+"\n":"\n")
                + ((idFuncao  != null)?" AND funcao_fk  = "+idFuncao.intValue()+ "\n":"\n")
                + ((idFuncional  != null)?" AND unidade_funcional = "+idFuncional.intValue()+ "\n":"\n");
        
        

        if (qtdGlobal.intValue() == 0) {
            // Inclui as unidades que o usuario tem acesso
            consulta += " AND ( id_unidade IN "
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
                    + "OR unidade_funcional IN\n"
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

        if (nome != null && !nome.equals("")) {
            consulta += " AND nome like :nome";
        }

        consulta += " ORDER BY nome, unidade";

        query = getEntityManager().
                createNativeQuery(consulta, "funciorioJustificativaGestor");
                        
        // Todos os registros
        queryTodosRegistros = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM ( " + consulta + " ) Q");
        
        
        
        if (nome != null && !nome.equals("")) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
            queryTodosRegistros.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }
        query.setParameter("periodo", periodo);
        queryTodosRegistros.setParameter("periodo", periodo);        
        
        
        if (qtdGlobal.intValue() == 0) {
            query.setParameter("idPessoa", idPessoa);
            queryTodosRegistros.setParameter("idPessoa", idPessoa);
        }
        
        
        query.setMaxResults(length);
        query.setFirstResult(start);
        
        // Dados da pesquisa
        data = query.getResultList();
        BigInteger filtrados = (BigInteger) queryTodosRegistros.getSingleResult();
        
        data = query.getResultList();

        paginacaoVO.setDraw(draw++);
        paginacaoVO.setRecordsTotal(filtrados.intValue());
        paginacaoVO.setRecordsFiltered(filtrados.intValue());
        paginacaoVO.setData(data);

        return paginacaoVO;
    }

    /**
     * Lista as hierarquias do tipo setor
     *
     * @param hierarquias
     * @return
     */
    private String hierarquiaSetor(List<Object[]> hierarquias) {

        StringBuffer h = new StringBuffer();

        for (Object[] hierarquia : hierarquias) {
            h = h.append(hierarquia[1].toString() + ",");
        }

        if (h.length() > 0) {
            h = h.deleteCharAt(h.lastIndexOf(","));
        }

        return h.toString();
    }

    @GET
    @Path("id/{id}")
    @Produces({"application/json"})
    public FuncionarioContrato getFuncionarioContrato(@PathParam("id") BigInteger id) {
        return super.find(id);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("matricula/{matricula}")
    @Produces({"application/json"})
    public FuncionarioContrato getFuncionarioMatricula(@PathParam("matricula") String matricula) {
        Query queryContrato = getEntityManager().
                createQuery("FROM FuncionarioContrato WHERE matricula =:matricula");
        queryContrato.setParameter("matricula", matricula);
        FuncionarioContrato funcionarioContrato = (FuncionarioContrato) queryContrato.getSingleResult();

        return funcionarioContrato;
    }
    
    @GET
    @Path("funcionariofk/{funcionarioFk}")
    @Produces({"application/json"})
    public List<FuncionarioContrato> getFuncionarioPessoaFk(@PathParam("funcionarioFk") BigInteger funcionarioFk) {
        Query queryContrato = getEntityManager().
                createQuery("FROM FuncionarioContrato WHERE funcionarioFk.id =:funcionarioFk");
        queryContrato.setParameter("funcionarioFk", funcionarioFk);
        List<FuncionarioContrato> lista = queryContrato.getResultList();

        return lista;
    }
}
