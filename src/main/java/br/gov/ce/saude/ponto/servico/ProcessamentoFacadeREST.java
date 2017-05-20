/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.Afastamento;
import br.gov.ce.saude.ponto.entidade.BatidaEscalaRel;
import br.gov.ce.saude.ponto.entidade.BatidaMes;
import br.gov.ce.saude.ponto.entidade.BatidaRel;
import br.gov.ce.saude.ponto.entidade.EscalaItem;
import br.gov.ce.saude.ponto.entidade.FeriadoMes;
import br.gov.ce.saude.ponto.entidade.FuncionarioContrato;
import br.gov.ce.saude.ponto.entidade.FuncionarioHorario;
import br.gov.ce.saude.ponto.entidade.HorarioFuncNaoBateu;
import br.gov.ce.saude.ponto.entidade.HorarioMes;
import br.gov.ce.saude.ponto.entidade.LogProcessamento;
import br.gov.ce.saude.ponto.entidade.Ocorrencia;
import br.gov.ce.saude.ponto.entidade.Organograma;
import br.gov.ce.saude.ponto.entidade.PontoProcessado;
import br.gov.ce.saude.ponto.entidade.Processamento;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Cristiano Bandeira
 */
@Stateless
@Path("processamento")
public class ProcessamentoFacadeREST extends AbstractFacade<Processamento, ServiceException> {

    public ProcessamentoFacadeREST() {
        super(Processamento.class);
    }

    @POST
    @Path("iniciar")
    @Consumes({"application/json"})
    @TransactionAttribute(NOT_SUPPORTED)
    public void iniciar() {
        Processamento processamento = new Processamento();
        //Calendar c = Calendar.getInstance();
        //c.set(2016, Calendar.MARCH, 01);
        processamento.setPeriodoInicial(new Date("03/01/2016"));
        //c.set(2016, Calendar.MARCH, 20);
        processamento.setPeriodoFinal(new Date("03/28/2016"));
        List<Organograma> unidadesOrganicas = buscarUnidadesOrganicas();
        List<FeriadoMes> feriados = buscarFeriados(processamento.getPeriodoInicial(), processamento.getPeriodoFinal());
        List<BatidaMes> batidas = buscarBatidas(processamento.getPeriodoInicial(), processamento.getPeriodoFinal());
        List<HorarioMes> horarios = buscarHorarios(); //null; //
        List<Processamento> processamentos = new ArrayList<Processamento>();

        /*
         Passos lógicos para o processamento do ponto
         * pegar a relação de unidade de processamento de ponto - unidade organica
         * pegar de cada unidade organica os funcionarios.
         * pegar de cada funcionario  a suas batidas        
         * pegar de cada funcionario o seu horario
         * pegar a relação de feriados do mes
         * confrontar os horarios com as batidas, identificando faltas.
         */
        try {

            for (Organograma unidadeOrganica : unidadesOrganicas) {
                //Nesta primeira versão o tipo de processamento é manual.
                //processamento vai ser feito para cada unidade
//            if (unidadeOrganica.getId().equals(new BigInteger("12000"))) {

                processamento = new Processamento();
                processamento.setTipo('M');
                processamento.setData(new Date());
                processamento.setSituacao('P');
                processamento.setPeriodoInicial(new Date("03/01/2016"));
                processamento.setPeriodoFinal(new Date("03/28/2016"));
                processamento.setOrganogramaFk(unidadeOrganica);
                processamento.setPontoProcessadoCollection(new ArrayList<PontoProcessado>());
                processamento.setLogProcessamentoCollection(new ArrayList<LogProcessamento>());

                processarPonto(batidas, feriados, horarios, unidadeOrganica, processamento,
                        processamento.getPeriodoInicial(), processamento.getPeriodoFinal());
//            }
                if (unidadeOrganica.getId().equals(new BigInteger("12000"))) {
                    processamentos.add(processamento);
                }
            }
        } finally {
            unidadesOrganicas.clear();
            feriados.clear();
            batidas.clear();
            horarios.clear();
        }
        for (Processamento p : processamentos) {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(p);
            getEntityManager().getTransaction().commit();
        }
        getBusiness().getMessageFactory().addInfoMessage("Processamento realizado com sucesso!");
    }

    private void gravarLog() {

//        else {
//                    //verificar se o dia foi feriado
//                    for (FeriadoMes feriado : feriados) {
//                        if (feriado.getData().getDay() == horario.getEntrada1().getDay()) {
//                            //marcar o dia como feriado  
//                            pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "FRD"));
//                            break;
//                        }
//                    }
//
//                    /* Não houve batida no dia horario
//                     */
//                    if (pp.getOcorrenciaFk() == null) {
//                        b = pp.getBatidaRelFk();
//                        b.setId(null);
//                        //inserir linha em branco no batida_rel e marcar como falta
//                        b.setData(horario.getEntrada1());
//                        FuncionarioHorario fh = new FuncionarioHorario();
//                        fh.setId(horario.getFuncionarioHorario());
//                        b.setFuncionarioHorario(fh);
//                        pp.setBatidaRelFk(b);
//                        //falta, não bateu no dia previsto  
//                        pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "FAL"));
//                    }
//                }
    }

    private void processarPonto(List<BatidaMes> batidas,
            List<FeriadoMes> feriados,
            List<HorarioMes> horarios,
            Organograma unidadeOrganica,
            Processamento processamento,
            Date periodoInicial, Date PeriodoFinal) {
        List<Ocorrencia> ocorrencias = buscarOcorrencia();
        List<HorarioMes> horfunc = new ArrayList<>();
        //Processa todas as batidas realizadas durante o mês.
        for (BatidaMes batida : batidas) {
            if (batida.getUnidadeOrganica() != null && batida.getUnidadeOrganica().equals(unidadeOrganica.getId())
                    && batida.getData().compareTo(periodoInicial) >= 0
                    && batida.getData().compareTo(PeriodoFinal) <= 0) {
                if (batida.getEscalaItemFk() == null) {
                    if (horfunc.isEmpty()) {
                        horfunc = findHorarioFuncionario(horarios, batida.getFuncionarioHorarioFk());
                    } else if ((horfunc.size() > 0
                            && !horfunc.get(horfunc.size() - 1).getFuncionarioHorario().equals(batida.getFuncionarioHorarioFk()))) {
                        horfunc.clear();
                        horfunc = findHorarioFuncionario(horarios, batida.getFuncionarioHorarioFk());
                    }
                    processarPontoHorario(batida, horfunc, ocorrencias, processamento);
                } else {
                    horfunc = findHorarioFuncionario(horarios, batida.getFuncionarioHorarioFk());
                    processarPontoEscala(batida, horfunc, ocorrencias, processamento);
                }
            }
            gravarLog();
        }
        //Verificar que não realizou nenhuma batida durante o Mes
        processarPtoNaoBatido(batidas, feriados, horarios, ocorrencias, processamento);
    }

    private List<HorarioMes> findHorarioFuncionario(List<HorarioMes> horarios, BigInteger idFunc) {
        List<HorarioMes> retorno = new ArrayList<HorarioMes>();
        //buscarHorariosFunc(idFunc);
        for (HorarioMes h1 : horarios) {
            if (h1.getFuncionarioHorario().equals(idFunc)) {
                retorno.add(h1);
            }
        }
        return retorno;
    }

    private void processarPtoNaoBatido(List<BatidaMes> batidas,
            List<FeriadoMes> feriados,
            List<HorarioMes> horarios,
            List<Ocorrencia> ocorrencias, Processamento processamento) {
        List<HorarioMes> listaHor = null;
        Boolean achou = false;
//        List<BigInteger> lstFuncHorario = new ArrayList<>();
        List<Afastamento> afastamentos = buscaAfastamento(processamento.getPeriodoInicial(), processamento.getPeriodoFinal());
        List<HorarioFuncNaoBateu> NaoBateram = buscarFuncNaoBateu();
//        for (HorarioMes horario : horarios) {
//            achou = false;
//            for (BatidaMes b : batidas) {
//                if (horario.getFuncionarioHorario().equals(b.getFuncionarioHorarioFk()) 
//                        && b.getData().getDate() == horario.getEntrada1().getDate()) {
//                    achou = true;
//                    break;
//                }
//            }
//            if (!achou) {
//                lstFuncHorario.add(horario.getFuncionarioHorario());
//            }
//        }
        //Gravar o processamento para os dias que não foram batidos
        for (HorarioFuncNaoBateu func : NaoBateram) {
//        for (BigInteger big : lstFuncHorario) {
            //procura os horarios do funcionario
//            listaHor = findHorarioFuncionario(horarios, func.getFuncionarioHorario());
//            for (HorarioMes horario : listaHor) {
            PontoProcessado pp = new PontoProcessado();
            pp.setProcessamentoFk(processamento);
            pp.setSituacao('F');
            LogProcessamento lp = new LogProcessamento();
            //System.out.println(System.currentTimeMillis());
            if (func.getEscalaItem() == null) {
                //Pelo horario não é uma escala
                try {
                    BatidaRel b = new BatidaRel();
                    b.setData(func.getEntrada1());
                    FuncionarioHorario fh = new FuncionarioHorario();
                    fh = getEntityManager().find(fh.getClass(), func.getFuncionarioHorario());
                    //fh.setId(horario.getFuncionarioHorario());
                    b.setFuncionarioHorario(fh);
                    pp.setBatidaRelFk(b);
                } catch (Exception e) {
                    lp.setProcessamentoFk(processamento);
                    lp.setLog(e.getMessage());
                    lp.setBatidaRelFk(pp.getBatidaRelFk());
                    processamento.getLogProcessamentoCollection().add(lp);
                }

            } else {
                try {
                    BatidaEscalaRel be = new BatidaEscalaRel();
                    EscalaItem ei = new EscalaItem();
                    ei = getEntityManager().find(ei.getClass(), func.getEscalaItem());
                    be.setEscalaItemFk(ei);
                    pp.setBatidaRelEscalaFk(be);
                } catch (Exception e) {
                    lp.setProcessamentoFk(processamento);
                    lp.setLog(e.getMessage());
                    lp.setBatidaEscalaRelFk(pp.getBatidaRelEscalaFk());
                    processamento.getLogProcessamentoCollection().add(lp);
                }
            }

            //Identificar a razão de não ter batido no dia previsto  
            pp.setOcorrenciaFk(findRazaoAusencia(ocorrencias, func.getEntrada1(), func.getFuncionarioHorario(), feriados, afastamentos));
            //pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "FAL"));
            processamento.getPontoProcessadoCollection().add(pp);
        }
    }

    private Ocorrencia procurarOcorrenciaAfastamento(Date data,
            BigInteger funcionarioHorario, List<Afastamento> afastamentos) {
        Ocorrencia retorno = null;
        FuncionarioContrato func = buscarFuncionarioContrato(funcionarioHorario);
        for (Afastamento afastamento : afastamentos) {
            if (afastamento.getFuncionarioContratoFk().equals(func)
                    && afastamento.getDataFinal().equals(data)) {
                retorno = afastamento.getOcorrenciaFk();
            }
        }
        return retorno;
    }

    private Ocorrencia ocorrenciaAfastado(Date data,
            BigInteger funcionarioHorario, Ocorrencia Falta,
            List<Afastamento> afastamentos) {
        Ocorrencia retorno = procurarOcorrenciaAfastamento(data, funcionarioHorario,
                afastamentos);
        if (retorno == null || retorno.getId() == null) {
            retorno = Falta;
        }
        return retorno;
    }

    private Ocorrencia findRazaoAusencia(List<Ocorrencia> ocorrencias,
            Date data, BigInteger funcionarioHorario,
            List<FeriadoMes> feriados, List<Afastamento> afastamentos) {
        //Por principio, não tem justificativa, logo é Falta
        Ocorrencia retorno = findOcorrencia(ocorrencias, "FAL");
        //Identifica se foi afastado de licença ou ferias
        Ocorrencia oc = ocorrenciaAfastado(data, funcionarioHorario, retorno,
                afastamentos);
        //Se Afastado por alguma razão diferente de Falta, fica valendo o afastamento
        if (!oc.getId().equals(retorno.getId())) {
            retorno = oc;
            //Se não foi Afastado, identifcar se foi feriado    
        } else {
            for (FeriadoMes feriado : feriados) {
                if (feriado.getData().equals(data)) {
                    retorno = findOcorrencia(ocorrencias, "FRD");
                }
            }
        }
        return retorno;
    }

    private Ocorrencia findOcorrencia(List<Ocorrencia> ocorrencias, String abrevOcorrencia) {
        Ocorrencia retorno = null;
        for (Ocorrencia ocorrencia : ocorrencias) {
            if (ocorrencia.getAbreviatura().equalsIgnoreCase(abrevOcorrencia)) {
                retorno = ocorrencia;
            }
        }
        return retorno;
    }

    private void processarPontoHorario(BatidaMes batida,
            List<HorarioMes> horarios, List<Ocorrencia> ocorrencias,
            Processamento processamento) {
        for (HorarioMes horario : horarios) {
            if (horario.getFuncionarioHorario().equals(batida.getFuncionarioHorarioFk())) {
                //houve batida no mesmo dia do horario
                if (horario.getEntrada1().getDate() == batida.getData().getDate()) {
                    PontoProcessado pp = new PontoProcessado();
                    LogProcessamento lp = new LogProcessamento();
                    //BatidaRel b = new BatidaRel();
                    BatidaRel b = getEntityManager().find(BatidaRel.class, batida.getBatidaRelPk());
                    pp.setBatidaRelFk(b);
                    pp.setProcessamentoFk(processamento);
                    b = pp.getBatidaRelFk();
                    b.setId(batida.getBatidaRelPk());
                    pp.setBatidaRelFk(b);
                    pp.setSituacao('F');
                    //identificar se tem 2 horarios ou 4
                    //tem 2 expedientes
                    try {

                        if (horario.getSaida2() != null) {
                            if (batida.getBatida1() == null && batida.getBatida2() == null
                                    && batida.getBatida3() == null && batida.getBatida4() == null) {
                                //falta  
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "FAL"));
                            } else if (batida.getBatida1() == null) {
                                //meia falta
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida2() == null) {
                                //meia falta
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida3() == null) {
                                //meia falta
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida4() == null) {
                                //meia falta
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida1().after(horario.getEntrada1())) {
                                //atraso
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                            } else if (batida.getBatida1().after(horario.getSaida1())) {
                                //meia falta 
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida1().after(horario.getEntrada2())) {
                                // meia falta 
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida2().after(horario.getEntrada2())) {
                                // meia falta 
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida2().before(horario.getSaida1())) {
                                //atraso 
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                            } else if (batida.getBatida3().after(horario.getEntrada2())) {
                                //atraso
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                            } else if (batida.getBatida4().before(horario.getEntrada2())) {
                                //meia falta
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida4().before(horario.getSaida2())) {
                                //atraso
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                            }
                            //tem 1 expediente   
                        } else {
                            if (batida.getBatida1() == null && batida.getBatida2() == null) {
                                //falta  
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "FAL"));
                            } else if (batida.getBatida1() == null && batida.getBatida2() != null) {
                                //meia falta
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida1() != null && batida.getBatida2() == null) {
                                //meia falta 
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida1().after(horario.getEntrada1())) {
                                //atraso  
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                            } else if (batida.getBatida1().after(horario.getSaida1())) {
                                // meia falta
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                            } else if (batida.getBatida2().before(horario.getSaida1())) {
                                //atraso  
                                pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                            }
                        }
                    } catch (Exception e) {
                        lp.setProcessamentoFk(processamento);
                        lp.setLog(e.getMessage());
                        lp.setBatidaRelFk(b);
                        processamento.getLogProcessamentoCollection().add(lp);
                    }
                    //só adiciona se tiver ocorrencia
                    if (pp.getOcorrenciaFk() != null && pp.getOcorrenciaFk().getId() != null) {
                        processamento.getPontoProcessadoCollection().add(pp);
                    }
                }
            }
        }
    }

    private void processarPontoEscala(BatidaMes batida,
            List<HorarioMes> horarios, List<Ocorrencia> ocorrencias,
            Processamento processamento) {
        for (HorarioMes horario : horarios) {
            if (horario.getFuncionarioHorario().equals(batida.getFuncionarioHorarioFk())) {
                //houve batida no mesmo dia do horario
                if (horario.getEntrada1().getDate() == batida.getData().getDate()) {
                    PontoProcessado pp = new PontoProcessado();
                    //BatidaRel b = new BatidaRel();
                    LogProcessamento lp = new LogProcessamento();
                    BatidaEscalaRel b = getEntityManager().find(BatidaEscalaRel.class, batida.getBatidaRelEscalaPk());
                    pp.setProcessamentoFk(processamento);
                    pp.setBatidaRelEscalaFk(b);
                    pp.setSituacao('F');
                    try {

                        if (batida.getBatida1() == null && batida.getBatida2() == null) {
                            //falta  
                            pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "FAL"));
                        } else if (batida.getBatida1() == null && batida.getBatida2() != null) {
                            //meia falta
                            pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                        } else if (batida.getBatida1() != null && batida.getBatida2() == null) {
                            //meia falta 
                            pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                        } else if (batida.getBatida1().after(horario.getEntrada1())) {
                            //atraso  
                            pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                        } else if (batida.getBatida1().after(horario.getSaida1())) {
                            // meia falta
                            pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "MEI"));
                        } else if (batida.getBatida2().before(horario.getSaida1())) {
                            //atraso  
                            pp.setOcorrenciaFk(findOcorrencia(ocorrencias, "ATR"));
                        }
                    } catch (Exception e) {
                        lp.setProcessamentoFk(processamento);
                        lp.setLog(e.getMessage());
                        lp.setBatidaEscalaRelFk(b);
                        processamento.getLogProcessamentoCollection().add(lp);
                    }

                    if (pp.getOcorrenciaFk() != null && pp.getOcorrenciaFk().getId() != null) {
                        processamento.getPontoProcessadoCollection().add(pp);
                    }
                }
            }
        }

    }

    private List<BatidaMes> buscarBatidas(Date periodoInicial, Date periodoFinal) {
        String hql = "SELECT f FROM BatidaMes f "
                + " WHERE f.data between :inicial and :final ";
        //+ " and escalaItemFk in (select ei.id from EscalaItem ei where ei.funcionarioHorario= 4703)";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("inicial", periodoInicial);
        q.setParameter("final", periodoFinal);
        List<BatidaMes> lista = q.getResultList();
        return lista;
    }

    private List<HorarioMes> buscarHorarios() {
        //       String hql = "SELECT f FROM HorarioMes f";
        List<HorarioMes> retorno = null;
        Session s = (Session) getEntityManager().getDelegate();
        Criteria cr = s.createCriteria(HorarioMes.class).setFetchSize(30);
        retorno = cr.list();
        return retorno;
//        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<HorarioMes> q = builder.createQuery(HorarioMes.class);
//        Root<HorarioMes> r = q.from(HorarioMes.class);
//        q.select(builder.construct(null, slctns))
//        Criteria criteria = getEntityManager().' createCriteria(HorarioMes.class);
//    .setFetchSize(100)
//    .setMaxResults(100)
//    .addOrder(Order.desc("acc
//        Query q = getEntityManager().createQuery(hql);
//        List<HorarioMes> lista = q.getResultList();
//        return lista;
    }

//    private List<Afastamento> OcorrenciaAfastado(Date periodoInicial, Date periodoFinal) {
//        //Ocorrencia retorno = null;
//        String hql = "SELECT f FROM Afastamento f "
//                + "  where  coalesce(f.dataFinal, :final) between :inicial and :final";
//        //f.funcionarioContratoFk.id in (select h.funcionarioContrato.id from FuncionarioHorario h where h.id = :func)
//        Query q = getEntityManager().createQuery(hql);
////        q.setParameter("func", idFunc);
//        q.setParameter("inicial", periodoInicial);
//        q.setParameter("final", periodoFinal);
//        List<Afastamento> lista = q.getResultList();
////        if (lista.size() > 0) {
////            retorno = lista.get(lista.size() - 1).getOcorrenciaFk();
////        }
//        return lista;
//
//    }
    private List<Organograma> buscarUnidadesOrganicas() {
        String hql = "SELECT o FROM Organograma o WHERE id in (12000,12076,12111,"
                + "12116,12132,12145,12153,12158,12179,12190,12205,12213,12216,"
                + "12219,12220,12221,12228,12231,12234,12237,12246,12256,12263,"
                + "12324,12351,12370,12416,12455,12528,12531,12544,12545,12569,12470)";
        Query q = getEntityManager().createQuery(hql);
        List<Organograma> lista = q.getResultList();
        return lista;
    }

    private List<FeriadoMes> buscarFeriados(Date periodoInicial, Date periodoFinal) {
        String hql = "SELECT f FROM FeriadoMes f "
                + " WHERE f.data between :inicial and :final";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("inicial", periodoInicial);
        q.setParameter("final", periodoFinal);
        List<FeriadoMes> lista = q.getResultList();
        return lista;
    }

    private List<Ocorrencia> buscarOcorrencia() {
        String hql = "SELECT f FROM Ocorrencia f ";
        Query q = getEntityManager().createQuery(hql);
        List<Ocorrencia> lista = q.getResultList();
        return lista;
    }

    private List<Afastamento> buscaAfastamento(Date periodoInicial, Date periodoFinal) {
        String hql = "SELECT f FROM Afastamento f "
                + " WHERE coalesce(f.dataFinal, :final) between :inicial and :final";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("inicial", periodoInicial);
        q.setParameter("final", periodoFinal);
        List<Afastamento> lista = q.getResultList();
        return lista;

    }

    private FuncionarioContrato buscarFuncionarioContrato(BigInteger funcHorario) {
        String hql = "SELECT f.funcionarioContrato FROM FuncionarioHorario f where f.id = :func ";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("func", funcHorario);
        FuncionarioContrato func = (FuncionarioContrato) q.getSingleResult();
        return func;

    }

    private List<HorarioMes> buscarHorariosFunc(BigInteger funcHorario) {
        String hql = "SELECT f FROM HorarioMes f where f.funcionarioHorario = :func";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("func", funcHorario);
        List<HorarioMes> lista = q.getResultList();
        return lista;
    }

    private List<HorarioFuncNaoBateu> buscarFuncNaoBateu() {
        String hql = "SELECT f FROM HorarioFuncNaoBateu f ";
        Query q = getEntityManager().createQuery(hql);
        List<HorarioFuncNaoBateu> lista = q.getResultList();
        return lista;
    }

}
