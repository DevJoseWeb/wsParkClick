/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.BatidaRel;
import br.gov.ce.saude.ponto.vo.BatidaPontorelVO;
import br.gov.ce.saude.ponto.vo.BatidaUnidadeVO;
import br.gov.ce.saude.ponto.vo.BatidaVO;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author joao
 */
@Stateless
@Path("batida")
public class BatidaRelFacadeREST extends AbstractFacade<BatidaRel, ServiceException> {

    public static final SimpleDateFormat dma = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");

    public BatidaRelFacadeREST() {
        super(BatidaRel.class);
    }

    @GET
    @Path("{batidaPk}")
    @Produces("application/json")
    public BatidaRel find(@PathParam("batidaPk") BigInteger batidaPk) {
        return super.find(batidaPk);
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("HGCCO/{diaini}/{mesini}/{anoini}/{diafim}/{mesfim}/{anofim}")
    @Produces("application/json")
    public List<BatidaUnidadeVO> findBatidaUnidadeHGCCO(@PathParam("diaini") int diaini,
            @PathParam("mesini") int mesini, @PathParam("anoini") int anoini,
            @PathParam("diafim") int diafim, @PathParam("mesfim") int mesfim,
            @PathParam("anofim") int anofim) throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(anoini, mesini - 1, diaini);
        Date dataini = c.getTime();
        c.set(anofim, mesfim - 1, diafim);
        Date datafim = c.getTime();
        return getBatidas(12416, dataini, datafim);
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("HIAS/{diaini}/{mesini}/{anoini}/{diafim}/{mesfim}/{anofim}")
    @Produces("application/json")
    public List<BatidaUnidadeVO> findBatidaUnidadeHIAS(@PathParam("diaini") int diaini,
            @PathParam("mesini") int mesini, @PathParam("anoini") int anoini,
            @PathParam("diafim") int diafim, @PathParam("mesfim") int mesfim,
            @PathParam("anofim") int anofim) throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(anoini, mesini - 1, diaini);
        Date dataini = c.getTime();
        c.set(anofim, mesfim - 1, diafim);
        Date datafim = c.getTime();
        return getBatidas(12455, dataini, datafim);
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("HCAS/{diaini}/{mesini}/{anoini}/{diafim}/{mesfim}/{anofim}")
    @Produces("application/json")
    public List<BatidaUnidadeVO> findBatidaUnidadeHCAS(@PathParam("diaini") int diaini,
            @PathParam("mesini") int mesini, @PathParam("anoini") int anoini,
            @PathParam("diafim") int diafim, @PathParam("mesfim") int mesfim,
            @PathParam("anofim") int anofim) throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(anoini, mesini - 1, diaini);
        Date dataini = c.getTime();
        c.set(anofim, mesfim - 1, diafim);
        Date datafim = c.getTime();
        return getBatidas(12370, dataini, datafim);
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("{matricula}/{mes}/{ano}")
    @Produces("application/json")
    public List<BatidaVO> find(@PathParam("matricula") String matricula,
            @PathParam("mes") String mes,
            @PathParam("ano") String ano) throws Exception {

        List<BatidaVO> batidaVOs = new ArrayList<BatidaVO>();
        List<Date> feriados = feriado(primeiroDiaMes(mes, ano), ultimoDiaMesAtual(mes, ano));

        String consulta = "select \n"
                + "rel.data,\n"
                + "hor.dia_semana,\n"
                + "rel.batida_1, rel.batida_2, rel.batida_3, rel.batida_4,\n"
                + "hor.entrada_1, hor.saida_1, hor.entrada_2, hor.saida_2,\n"
                + "rel.justificativa, \n"
                + "(SELECT CASE \n"
                + "        WHEN br.justificativa_gestor_fk IS NOT NULL THEN TRUE\n"
                + "        WHEN br.ocorrencia_processamento_fk IS NOT NULL THEN FALSE\n"
                + "        END \n"
                + " FROM e_pontows.t_batida_rel br\n"
                + " WHERE br.batida_rel_pk = rel.batida_rel_pk) as justificado_chefe, \n"
                + " (SELECT j.descricao\n"
                + " FROM e_pontows.t_batida_rel br, e_pontows.t_justificativa j\n"
                + " WHERE br.batida_rel_pk = rel.batida_rel_pk AND \n"
                + " br.justificativa_gestor_fk=j.justificativa_pk) as tipo_justificativa_chefe,\n"
                + "(SELECT t_justificativa.descricao\n"
                + "FROM e_pontows.t_ponto_processado, e_pontows.t_justificativa \n"
                + "WHERE t_ponto_processado.batida_rel_fk = rel.batida_rel_pk AND \n"
                + "t_ponto_processado.justificativa_fk=t_justificativa.justificativa_pk AND situacao ='F' limit 1) as tipo_justificativa_chefe, "
                + "(select descricao from e_pontows.t_justificativa where justificativa_pk = rel.tipo_justificativa_fk) "
                + "as tipo_justificativa,\n"
                + "rel.batida_rel_pk  \n"
                + "from\n"
                + "(select \n"
                + "data,\n"
                + "mes.dia_semana,\n"
                + "matricula1 as matricula, \n"
                + "null as batida_1, null as batida_2, null as batida_3, null as batida_4,\n"
                + "(SELECT t_horario_item.entrada_1 FROM \n"
                + "  e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_horario_item\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_horario_item.horario_fk = t_funcionario_horario.horario_fk AND\n"
                + "  t_funcionario_contrato.matricula = :matricula AND \n"
                + "  t_funcionario_horario.inicio <= data AND \n"
                + "  t_horario_item.dia_semana = EXTRACT( DOW FROM data)+1 order by t_funcionario_horario.inicio desc limit 1) AS entrada_1 ,\n"
                + "(SELECT t_horario_item.saida_1 FROM \n"
                + "  e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_horario_item\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_horario_item.horario_fk = t_funcionario_horario.horario_fk AND\n"
                + "  t_funcionario_contrato.matricula = :matricula AND \n"
                + "  t_funcionario_horario.inicio <= data AND \n"
                + "  t_horario_item.dia_semana = EXTRACT( DOW FROM data)+1 order by t_funcionario_horario.inicio desc limit 1) AS saida_1 ,\n"
                + "  \n"
                + "(SELECT t_horario_item.entrada_2 FROM \n"
                + "  e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_horario_item\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_horario_item.horario_fk = t_funcionario_horario.horario_fk AND\n"
                + "  t_funcionario_contrato.matricula = :matricula AND \n"
                + "  t_funcionario_horario.inicio <= data AND \n"
                + "  t_horario_item.dia_semana = EXTRACT( DOW FROM data)+1 order by t_funcionario_horario.inicio desc limit 1) AS entrada_2 ,\n"
                + "  \n"
                + "(SELECT t_horario_item.saida_2 FROM \n"
                + "  e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_horario_item\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_horario_item.horario_fk = t_funcionario_horario.horario_fk AND\n"
                + "  t_funcionario_contrato.matricula = :matricula AND \n"
                + "  t_funcionario_horario.inicio <= data AND \n"
                + "  t_horario_item.dia_semana = EXTRACT( DOW FROM data)+1 order by t_funcionario_horario.inicio desc limit 1) AS saida_2 ,\n"
                + "null as justificativa, \n"
                + "null as batida_rel_pk \n"
                + "from\n"
                + "(select cast(:matricula as text) as matricula1, b.generate_series as data, \n"
                + "cast(to_char(b.generate_series, 'D') as integer) as dia_semana from \n"
                + "(SELECT * FROM generate_series(  :primeiroDia ,  :ultimoDia ,INTERVAL '1 day')) b) mes left join\n"
                + "(SELECT t_funcionario_contrato.matricula, t_horario_item.dia_semana, \n"
                + "        t_horario_item.entrada_1, t_horario_item.saida_1, \n"
                + "  t_horario_item.entrada_2, t_horario_item.saida_2\n"
                + "FROM \n"
                + "  e_pontows.t_funcionario_horario, e_pontows.t_funcionario_contrato, e_pontows.t_horario_item\n"
                + "WHERE \n"
                + "  t_funcionario_horario.horario_fk = t_horario_item.horario_fk AND\n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_funcionario_contrato.matricula = :matricula  AND t_funcionario_horario.ativo=true\n"
                + "  ) horario on (mes.dia_semana = horario.dia_semana)\n"
                + "order by 1) hor,\n"
                + "\n"
                + "(select DISTINCT ON (bat.data) data,\n"
                + "bat.matricula, bat.batida_1,\n"
                + "bat.batida_2,bat.batida_3,\n"
                + "bat.batida_4,bat.justificativa,\n"
                + "batida_rel_pk, bat.tipo_justificativa_fk \n"
                + " from \n"
                + "(select matricula, data, \n"
                + "batida_1,batida_2,batida_3,\n"
                + "batida_4, justificativa, batida_rel_pk, tipo_justificativa_fk\n"
                + "from \n"
                + "e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_batida_rel\n"
                + "where \n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_funcionario_contrato.matricula = :matricula AND\n"
                + "  t_batida_rel.funcionario_horario_fk = t_funcionario_horario.funcionario_horario_pk AND\n"
                + " data between  :primeiroDia  and  :ultimoDia   \n"
                + "union\n"
                + "select null as matricula, b.generate_series,\n"
                + "       null as batida_1, null as batida_2,\n"
                + "       null as batida_3, null as batida_4,  \n"
                + "       null as justificativa, null as batida_rel_pk, null as tipo_justificativa_fk\n"
                + "from \n"
                + "(SELECT * FROM generate_series( :primeiroDia , :ultimoDia,INTERVAL'1 day')) b\n"
                + "order by 2,1) bat\n"
                + ") rel\n"
                + "where rel.data = hor.data\n"
                + "order by 1,2";

        Query query = getEntityManager().createNativeQuery(consulta, "batidaPonto");
        query.setParameter("matricula", matricula);
        query.setParameter("primeiroDia", primeiroDiaMes(mes, ano));
        query.setParameter("ultimoDia", ultimoDiaMesAtual(mes, ano));

        List<BatidaVO> batidaRels = query.getResultList();

        for (BatidaVO batida : batidaRels) {
            if (diaDaSemana(batida.getDataBatida()).equals(batida.getDiaDaSemana())) {

                BatidaVO batidaVO = preencheBatida(batida);
                batidaVO.setBatidaPk(batida.getBatidaPk());
                batidaVO.setData(dateToString(batida.getDataBatida()));
                batidaVO.setDiaDaSemanaStr(pesquisarDiaSemana(batida.getDiaDaSemana()));

                batidaVO.setBatida1(timeToString(batida.getB1()));
                batidaVO.setBatida2(timeToString(batida.getB2()));
                batidaVO.setBatida3(timeToString(batida.getB3()));
                batidaVO.setBatida4(timeToString(batida.getB4()));

                Long deveTrabalhar1 = tempoTrabalhadoFixo(batida.getSaida1(), batida.getEntrada1()); // Entrada 1 - Saida 1
                Long deveTrabalhar2 = tempoTrabalhadoFixo(batida.getSaida2(), batida.getEntrada2());

                Long horasTrabalhadas1 = null; 

                if (batida.getB1() != null && batida.getSaida1() != null
                        && hms.parse(hms.format(batida.getB1())).after(hms.parse(hms.format(batida.getSaida1())))) { // Se a batida1 for superior a primeira saida
                    horasTrabalhadas1 = tempoTrabalhado2(batida.getB2(), batida.getB1(), batida.getSaida2(), batida.getEntrada2());  // Batida 4 - Batida 3
                } else {
                    horasTrabalhadas1 = tempoTrabalhado1(batida.getB2(), batida.getB1(), batida.getSaida1(), batida.getEntrada1(), deveTrabalhar2); // Batida 2 - Batida 1
                }

                Long horasTrabalhadas2 = tempoTrabalhado2(batida.getB4(), batida.getB3(), batida.getSaida2(), batida.getEntrada2());  // Batida 4 - Batida 3

                // Entrada 2 - Saida 2
                Long total = horasTrabalhadas1 + horasTrabalhadas2;

                Long saldo = null;
                if ((deveTrabalhar1 + deveTrabalhar2) == 0) {
                    saldo = Long.valueOf("0");
                } else {
                    saldo = total - (deveTrabalhar1 + deveTrabalhar2);
                }

                batidaVO.setTotal(longToDate(total));
                batidaVO.setSaldo(longToDate(saldo));
                batidaVO.setTipo_justificativa(batida.getTipo_justificativa());
                batidaVO.setJustificado_chefe(batida.getJustificado_chefe());
                batidaVO.setTipo_justificativa_chefe(batida.getTipo_justificativa_chefe());
                if (batida.getJustificativa() != null && batida.getJustificativa().length() > 1 || batida.getTipo_justificativa() != null) {
                    batidaVO.setJustificado(true);
                    batidaVO.setJustificativa(batida.getJustificativa());
                    batidaVO.setTipo_justificativa(batida.getTipo_justificativa());
                }

                if (contemData(feriados, batida.getDataBatida())) {
                    batidaVO.setTotal("00:00:00");
                    batidaVO.setSaldo("00:00:00");
                }

                batidaVOs.add(batidaVO);
            }
        }

        return batidaVOs;
    }

//    *************************************************RELATÓRIO LISTA DE BATIDAS*********** //
    @TransactionAttribute(NOT_SUPPORTED)
    @GET
    @Path("geralbatidas/{matricula}/{mesini}/{anoini}/")
    @Produces("application/json")
    public List<BatidaPontorelVO> findx(@PathParam("matricula") String matricula,
            @PathParam("mesini") String mesini,
            @PathParam("anoini") String anoini) throws Exception {

        String consulta = "SELECT \n"
                + "  t_batida.data_batida, \n"
                + "  t_funcionario_contrato.funcionario_contrato_pk\n"
                + "FROM \n"
                + "  e_pontows.t_funcionario_contrato, \n"
                + "  e_pontows.t_funcionario_horario, \n"
                + "  e_pontows.t_batida\n"
                + "WHERE \n"
                + "  t_funcionario_contrato.funcionario_contrato_pk = t_funcionario_horario.funcionario_contrato_fk AND\n"
                + "  t_funcionario_horario.funcionario_horario_pk = t_batida.funcionario_horario_fk AND \n"
                + "   CAST(data_batida as date) BETWEEN :primeiroDia AND :ultimoDia AND \n"
                + "   matricula = :matricula\n"
                + "ORDER BY data_batida";

        Query query = getEntityManager().createNativeQuery(consulta, "batidaPontoind");
        query.setParameter("matricula", matricula);
        query.setParameter("primeiroDia", primeiroDiaMes(mesini, anoini));
        query.setParameter("ultimoDia", ultimoDiaMesAtual(mesini, anoini));
        List<BatidaPontorelVO> batidaRels = query.getResultList();
        return batidaRels;
    }

    /**
     * Compara se uma data esta na lista de datas
     *
     * @param lista
     * @param data
     * @return
     */
    private boolean contemData(List<Date> lista, Date data) {
        for (Date dataDaLista : lista) {
            if (lista != null && !lista.isEmpty() && data != null) {
                if (dma.format(dataDaLista).equals(dma.format(data))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Listas os feriados do mês para serem abatidos
     */
    private List<Date> feriado(Date inicio, Date fim) {

        String consulta = "select data FROM FeriadoItem where data>= :inicio AND data<= :fim";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);

        return query.getResultList();
    }

    private BatidaVO preencheBatida(BatidaVO batida) {
        BatidaVO batidaVO = new BatidaVO();
        batidaVO.setBatidaPk(batida.getBatidaPk());
        batidaVO.setData(dateToString(batida.getDataBatida()));
        batidaVO.setDiaDaSemanaStr(pesquisarDiaSemana(batida.getDiaDaSemana()));

        batidaVO.setBatida1(timeToString(batida.getB1()));
        batidaVO.setBatida2(timeToString(batida.getB2()));
        batidaVO.setBatida3(timeToString(batida.getB3()));
        batidaVO.setBatida4(timeToString(batida.getB4()));

        if (batida.getJustificativa() != null && batida.getJustificativa().length() > 1) {
            batidaVO.setJustificado(true);
            batidaVO.setJustificativa(batida.getJustificativa());
        }

        return batidaVO;
    }

    private Date ultimoDiaMesAtual(String mes, String ano) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");

        Calendar cal = GregorianCalendar.getInstance();
        try {
            if (mes.length() == 1) {
                mes = "0" + mes;
            }
            cal.setTime(sdf.parse(mes + "/" + ano));
        } catch (ParseException ex) {
            Logger.getLogger(BatidaRelFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        int dia = 28;
        if (sdf.format(new Date()).equals(mes + "/" + ano)) {
            Calendar calAtual = Calendar.getInstance();
            calAtual.setTime(new Date());
            dia = calAtual.get(Calendar.DAY_OF_MONTH);
        } else {
            dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        String data = ano + "-" + mes + "-" + dia;

        SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
        return sdfData.parse(data);
    }

    private Date ultimoDiaMes(String mes, String ano) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");

        Calendar cal = GregorianCalendar.getInstance();
        try {
            cal.setTime(sdf.parse(mes + "/" + ano));
        } catch (ParseException ex) {
            Logger.getLogger(BatidaRelFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String data = ano + "-" + mes + "-" + dia;

        SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
        return sdfData.parse(data);
    }

    private Date primeiroDiaMes(String mes, String ano) throws ParseException {
        String data = ano + "-" + mes + "-01";
        SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
        return sdfData.parse(data);
    }

    private String timeToString(Date data) {
        if (data != null) {
            return hms.format(data);
        }
        return "";
    }

    private String dateToString(Date data) {
        if (data != null) {
            return dma.format(data);
        }
        return "";
    }

    /**
     * Realiza a subtração do trabalho realizado
     *
     * @param horaFinal
     * @param horaInicial
     * @return
     */
    private Long tempoTrabalhadoFixo(Date horaFinal, Date horaInicial) {
        if (horaFinal == null || horaInicial == null) {
            return (long) 0;
        }

        return horaFinal.getTime() - horaInicial.getTime();
    }

    private Date tolerancia15Min(Date horaInicial, Date deveEntrar) throws ParseException {

        if (horaInicial != null && deveEntrar != null) {
            horaInicial = hms.parse(hms.format(horaInicial));
            deveEntrar = hms.parse(hms.format(deveEntrar));

            long intervalo = (horaInicial.getTime() - deveEntrar.getTime()) / 1000;
            if (intervalo > 0 && intervalo < 16 * 60) {// Se passou a tolerancia de 15 min
                return deveEntrar;
            }
        }

        return horaInicial;
    }

    /**
     * Realiza a subtração do trabalho realizado
     *
     * @param horaFinal
     * @param horaInicial
     * @return
     */
    private Long tempoTrabalhado1(Date horaFinal, Date horaInicial,
            Date deveSair, Date deveEntrar, Long deveTrabalhar2) throws ParseException {
        Long tempoTrabalhado = new Long("0");

        if (deveSair != null && deveEntrar != null
                && horaFinal != null && horaInicial != null
                && hms.parse(hms.format(horaInicial)).before(hms.parse(hms.format(deveSair)))) {
            // Se tem horario a ser cumprido

            horaInicial = tolerancia15Min(horaInicial, deveEntrar);

            horaFinal = hms.parse(hms.format(horaFinal));
            horaInicial = hms.parse(hms.format(horaInicial));
            deveSair = hms.parse(hms.format(deveSair));
            deveEntrar = hms.parse(hms.format(deveEntrar));

            Date hora1 = null;
            Date hora2 = null;

            Calendar deveBaterInicial = Calendar.getInstance();
            deveBaterInicial.setTime(hms.parse(hms.format(deveEntrar)));
            deveBaterInicial.add(Calendar.HOUR, -1);

            //deveEntrar = hms.parse(hms.format(deveBaterInicial.getTime())); // REMOVE REGRA DA 1 HORA
            //if (horaInicial.before(deveEntrar)) {
            //    hora1 = deveEntrar;
            //} else {
            //    hora1 = horaInicial;
            //}
            if (horaFinal.after(deveSair) && horaInicial.before(deveSair) && deveTrabalhar2!=0) {
                hora2 = deveSair;
            } else {
                hora2 = horaFinal;
            }

            hora1 = horaInicial;
//            hora2 = horaFinal;

            tempoTrabalhado = hora2.getTime() - hora1.getTime();
        }

        return tempoTrabalhado;
    }

    /**
     * Realiza a subtração do trabalho realizado
     *
     * @param horaFinal
     * @param horaInicial
     * @return
     */
    private Long tempoTrabalhado2(Date horaFinal, Date horaInicial, Date deveSair, Date deveEntrar) throws ParseException {
        Long tempoTrabalhado = new Long("0");

        if (deveSair != null && deveEntrar != null
                && horaFinal != null && horaInicial != null
                //&& horaFinal.after(deveEntrar)) { // Se tem horaio a ser cumprido
                && hms.parse(hms.format(horaFinal)).after(hms.parse(hms.format(deveEntrar)))) {

            horaFinal = hms.parse(hms.format(horaFinal));
            horaInicial = hms.parse(hms.format(horaInicial));
            deveSair = hms.parse(hms.format(deveSair));
            deveEntrar = hms.parse(hms.format(deveEntrar));

            Date hora1 = null;
            Date hora2 = null;

            Calendar deveBaterFinal = Calendar.getInstance();
            deveBaterFinal.setTime(hms.parse(hms.format(deveSair)));
            deveBaterFinal.add(Calendar.HOUR, 1);

            //deveSair = hms.parse(hms.format(deveBaterFinal.getTime())); // REMOVE REGRA DA 1 HORA
            if (horaInicial.before(deveEntrar)) {
                hora1 = deveEntrar;
            } else {
                hora1 = horaInicial;
            }

            //if (horaFinal.after(deveSair)) {
            //    hora2 = deveSair;
            //} else {
            //    hora2 = horaFinal;
            //}
//            hora1 = horaInicial;
            hora2 = horaFinal;

            tempoTrabalhado = hora2.getTime() - hora1.getTime();
        }

        return tempoTrabalhado;
    }

    /**
     * Realiza a subtração do trabalho realizado
     *
     * @param horaFinal
     * @param horaInicial
     * @return
     */
    private Long tempoTrabalhado(Date horaFinal, Date horaInicial, Date deveSair, Date deveEntrar, int turno) throws ParseException {
        Long tempoTrabalhado = new Long("0");

        if (deveSair != null && deveEntrar != null) { // Se tem horaio a ser cumprido
            if (horaFinal != null && horaInicial != null
                    && hms.parse(hms.format(horaInicial)).before(hms.parse(hms.format(deveSair)))) {
                Calendar batidoFinal = Calendar.getInstance();
                Calendar deveBaterFinal = Calendar.getInstance();

                batidoFinal.setTime(horaFinal);
                deveBaterFinal.setTime(deveSair);

                if (hms.parse(hms.format(horaFinal)).after(hms.parse(hms.format(deveSair)))) { // para tratar hora do almoço                    
                    if (turno == 2 && hms.parse(hms.format(horaInicial)).before(hms.parse(hms.format(deveEntrar)))) {
                        tempoTrabalhado = hms.parse(hms.format(deveSair)).getTime() - hms.parse(hms.format(deveEntrar)).getTime();
                    } else {
                        tempoTrabalhado = hms.parse(hms.format(deveSair)).getTime() - hms.parse(hms.format(horaInicial)).getTime();
                    }
                } else if (turno == 2 && hms.parse(hms.format(horaInicial)).before(hms.parse(hms.format(deveEntrar)))) {
                    tempoTrabalhado = hms.parse(hms.format(horaFinal)).getTime() - hms.parse(hms.format(deveEntrar)).getTime();
                } else {
                    tempoTrabalhado = hms.parse(hms.format(horaFinal)).getTime() - hms.parse(hms.format(horaInicial)).getTime();
                }
            }
        }

        return tempoTrabalhado;
    }

    /**
     * Retorna String de horas e minutos
     *
     * @param diferenca
     * @return
     */
    public static String longToDate(Long diferenca) {

        try {
            diferenca = diferenca / 1000;
            long horas = diferenca / 3600;
            long minutos = (diferenca - horas * 3600) / 60;
            long segundos = (diferenca - (horas * 3600 + minutos * 60));

            String saldo = ((horas < 0 || minutos < 0 || segundos < 0) ? "-" : "") + (Math.abs(horas) < 10 ? "0" : "") + Math.abs(horas) + ":"
                    + (Math.abs(minutos) < 10 ? "0" : "") + Math.abs(minutos) + ":" + (Math.abs(segundos) < 10 ? "0" : "") + Math.abs(segundos);
            return saldo;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * Domingo:1 Sabado:7 Como a data do java e a do banco diferem é preciso
     * ajustar a do java ao do banco assim como no calculo realizado abaixo.
     *
     * @param data
     * @return
     */
    private Integer diaDaSemana(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        //return (cal.get(Calendar.DAY_OF_WEEK) - 1) % 7;
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Domingo:1 Segundo:2 Terça:3 Quarta:4 Quinta:5 Sexta:6 Sabado:7;
     *
     * @param diaSemana
     * @return
     */
    public String retornarDiaSemana(int ano, int mes, int dia) {
        Calendar calendario = new GregorianCalendar(ano, mes - 1, dia);
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        return pesquisarDiaSemana(diaSemana);
    }

    public String pesquisarDiaSemana(int _diaSemana) {
        String diaSemana = null;
        switch (_diaSemana) {
            case 1: {
                diaSemana = "Dom";
                break;
            }
            case 2: {
                diaSemana = "Seg";
                break;
            }
            case 3: {
                diaSemana = "Ter";
                break;
            }
            case 4: {
                diaSemana = "Qua";
                break;
            }
            case 5: {
                diaSemana = "Qui";
                break;
            }
            case 6: {
                diaSemana = "Sex";
                break;
            }
            case 7: {
                diaSemana = "Sáb";
                break;
            }

        }
        return diaSemana;

    }

    private List<BatidaUnidadeVO> getBatidas(Integer unidade, int mes, int ano) {
        Date ini;
        Date fim;
        String consulta = "SELECT fc.matricula,\n"
                + "      (select nome \n"
                + "      from e_base.t_pessoa p           \n"
                + "      where p.pessoa_pk = fc.pessoa_fk) nome,\n"
                + "    b.data_batida as data,\n"
                + "     (select sigla \n"
                + "         from e_base.t_organograma\n"
                + "        where organograma_pk= fc.unidade_organica_fk) as unidade,\n"
                + "    b.macaddress\n"
                + "   FROM e_pontows.t_batida b, \n"
                + "        e_pontows.t_funcionario_horario fh, \n"
                + "        e_pontows.t_funcionario_contrato fc    \n"
                + "  WHERE fc.funcionario_contrato_pk = fh.funcionario_contrato_fk\n"
                + "    and funcionario_horario_pk = b.funcionario_horario_fk\n"
                + "    and data_batida between :dataini and :datafim"
                + "    and unidade_organica_fk = :unidade\n"
                + " order by 2,3\n";
        Query query = getEntityManager().createNativeQuery(consulta, "batidaUnidade");
        Calendar c = Calendar.getInstance();
        //Jan é zero, Dezembro é 11
        c.set(ano, mes - 1, 1);
        ini = c.getTime();
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        fim = c.getTime();
        query.setParameter("unidade", unidade);
        query.setParameter("dataini", ini);
        query.setParameter("datafim", fim);
        List<BatidaUnidadeVO> lista = query.getResultList();
        return lista;
    }

    private List<BatidaUnidadeVO> getBatidas(Integer unidade, Date dataIni, Date dataFim) {
        Date ini;
        Date fim;
        String consulta = " SELECT b.batida_pk as id, \n"
                + "  fc.matricula,\n"
                + "      (select nome \n"
                + "      from e_base.t_pessoa p           \n"
                + "      where p.pessoa_pk = fc.pessoa_fk) nome,\n"
                + "    b.data_batida as data,\n"
                + "     (select sigla \n"
                + "         from e_base.t_organograma\n"
                + "        where organograma_pk= fc.unidade_organica_fk) as unidade,\n"
                + "    b.macaddress\n"
                + "   FROM e_pontows.t_batida b, \n"
                + "        e_pontows.t_funcionario_horario fh, \n"
                + "        e_pontows.t_funcionario_contrato fc    \n"
                + "  WHERE fc.funcionario_contrato_pk = fh.funcionario_contrato_fk\n"
                + "    and funcionario_horario_pk = b.funcionario_horario_fk\n"
                + "    and cast(data_batida as date) between :dataini and :datafim"
                + "    and unidade_organica_fk = :unidade\n"
                + " order by 1,4\n";
        Query query = getEntityManager().createNativeQuery(consulta, "batidaUnidade");
        Calendar c = Calendar.getInstance();
        //Jan é zero, Dezembro é 11
        query.setParameter("unidade", unidade);
        query.setParameter("dataini", dataIni);
        query.setParameter("datafim", dataFim);
        List<BatidaUnidadeVO> lista = query.getResultList();
        return lista;
    }
}
