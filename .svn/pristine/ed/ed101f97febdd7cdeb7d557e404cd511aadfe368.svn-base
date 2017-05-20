/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.FuncionarioContrato;
import br.gov.ce.saude.ponto.entidade.Horario;
import br.gov.ce.saude.ponto.entidade.HorarioItem;
import br.gov.ce.saude.ponto.vo.HorarioItemVO;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author esmayktillesse
 */
@Stateless
@Path("horario")
public class HorarioFacadeREST extends AbstractFacade<Horario, ServiceException> {

    public HorarioFacadeREST() {
        super(Horario.class);
    }

    @GET
    @Path("descricao")
    @Produces({"application/json"})
    public List<Horario> pesquisaHorario() {
        List<Horario> lista = super.findAll();
        return lista;
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    public Horario create(Horario entity) {
        Horario horario = preencheHorarioPOST(entity);
        return super.create(horario);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Horario edit(Horario entity) {
        Horario horario = preencheHorarioPUT(entity);
        return super.edit(horario);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigInteger id) {
        super.remove(super.find(id));
    }

//    @GET
//    @Path("procura/")
//    @Produces({"application/json"})
//    public List<Horario> procuraAll() {
//        List<Horario> lista = super.findAll();
//        return lista;
//    }
    @GET
    @Override
    @Produces({"application/json"})
    public List<Horario> findAll() {
        List<Horario> lista = super.findAll();

        for (Horario lista1 : lista) {
            List<HorarioItemVO> horario = preencheHorarioGET(lista1);
            lista1.setHorarioItemVoCollection(horario);
        }

        return lista;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Horario getHorario(@PathParam("id") BigInteger id) {
        return super.find(id);
    }

    private List<HorarioItemVO> preencheHorarioGET(Horario horario) {

        List<HorarioItemVO> horarioList = new ArrayList<>();

        for (HorarioItem horarioItem : horario.getHorarioItemCollection()) {

            HorarioItemVO horarioVO = new HorarioItemVO();

            horarioVO.setId(horarioItem.getId());
            horarioVO.setHorario_fk(horarioItem.getHorarioFk().getId());
            horarioVO.setDia_semana(horarioItem.getDiaSemana());
            horarioVO.setVira_dia(horarioItem.getViraDia());
            horarioVO.setCod_horario(horarioItem.getCodHorario());
            horarioVO.setEnt1(timeToString(horarioItem.getEntrada1()));
            horarioVO.setSai1(timeToString(horarioItem.getSaida1()));
            horarioVO.setEnt2(timeToString(horarioItem.getEntrada2()));
            horarioVO.setSai2(timeToString(horarioItem.getSaida2()));

            horarioList.add(horarioVO);
        }

        return horarioList;

    }

    private Horario preencheHorarioPUT(Horario horario) {

        Horario horario1 = getBusiness().procurarPorId(horario);

        horario1.setDescricao(horario.getDescricao());
        horario1.setAtivo(horario.getAtivo());
        horario1.setCodHorario(horario.getCodHorario());
        horario1.setOrganogramaFk(horario.getOrganogramaFk());
        horario1.setTurno(horario.getTurno());
        horario1.setHorarioItemVoCollection(horario.getHorarioItemVoCollection());

        List<HorarioItem> itensNovos = new ArrayList<HorarioItem>();
        for (HorarioItemVO horarioItemVO : horario.getHorarioItemVoCollection()) {

            boolean editou = false;
            for (HorarioItem horarioItem : horario1.getHorarioItemCollection()) {
                if (horarioItemVO.getId()!=null && horarioItemVO.getId().equals(horarioItem.getId())) {

                    horarioItem.setId(horarioItemVO.getId());
                    horarioItem.setEntrada1(timeToData(horarioItemVO.getEnt1()));
                    horarioItem.setSaida1(timeToData(horarioItemVO.getSai1()));
                    horarioItem.setEntrada2(timeToData(horarioItemVO.getEnt2()));
                    horarioItem.setSaida2(timeToData(horarioItemVO.getSai2()));
                    horarioItem.setDiaSemana(horarioItemVO.getDia_semana());
                    horarioItem.setViraDia(horarioItemVO.getVira_dia());
                    horarioItem.setCodHorario(horarioItemVO.getCod_horario());
                    horarioItem.setHorarioFk(horario);
                    editou = true;
                }
            }
            if (editou == false) {
                HorarioItem itemNovo = new HorarioItem();
                itemNovo.setId(horarioItemVO.getId());
                itemNovo.setEntrada1(timeToData(horarioItemVO.getEnt1()));
                itemNovo.setSaida1(timeToData(horarioItemVO.getSai1()));
                itemNovo.setEntrada2(timeToData(horarioItemVO.getEnt2()));
                itemNovo.setSaida2(timeToData(horarioItemVO.getSai2()));
                itemNovo.setDiaSemana(horarioItemVO.getDia_semana());
                itemNovo.setViraDia(horarioItemVO.getVira_dia());
                itemNovo.setCodHorario(horarioItemVO.getCod_horario());
                itemNovo.setHorarioFk(horario);
                
                itensNovos.add(itemNovo);
            }

        }

        horario1.getHorarioItemCollection().addAll(itensNovos);
        return horario1;

    }

    private Horario preencheHorarioPOST(Horario horario) {

        horario.setHorarioItemCollection(new ArrayList<HorarioItem>());

        for (HorarioItemVO horarioItemVO : horario.getHorarioItemVoCollection()) {

            HorarioItem horarioItem = new HorarioItem();

            horarioItem.setEntrada1(timeToData(horarioItemVO.getEnt1()));
            horarioItem.setSaida1(timeToData(horarioItemVO.getSai1()));
            horarioItem.setEntrada2(timeToData(horarioItemVO.getEnt2()));
            horarioItem.setSaida2(timeToData(horarioItemVO.getSai2()));
            horarioItem.setDiaSemana(horarioItemVO.getDia_semana());
            horarioItem.setViraDia(horarioItemVO.getVira_dia());
            horarioItem.setCodHorario(horarioItemVO.getCod_horario());
            horarioItem.setHorarioFk(horario);

            horario.getHorarioItemCollection().add(horarioItem);

        }
        return horario;

    }

    private String timeToString(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if (data != null) {
            return sdf.format(data);
        }
        return "";
    }

    private Date timeToData(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dataFormatada = null;
        try {
            dataFormatada = formato.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(HorarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataFormatada;

    }

    @GET
    @Path("matricula/{matricula}")
    @Produces({"application/json"})
    public Horario getHorarioDaMatricula(@PathParam("matricula") String matricula) {

        Query queryContrato = getEntityManager().
                createQuery("SELECT a.horario FROM FuncionarioHorario a WHERE a.funcionarioContrato.matricula =:matricula "
                        + " AND a.ativo=true");
        queryContrato.setParameter("matricula", matricula);
        Horario horario = null;
        try {
            horario = (Horario) queryContrato.getSingleResult();
        } catch (Exception e) {
            getBusiness().addErrorMessage(null, "Usuário não possui horário cadastrado.");
            getBusiness().lancarException();
        }

        return horario;
    }
}
