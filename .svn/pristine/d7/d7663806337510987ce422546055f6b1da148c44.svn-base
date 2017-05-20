/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.servico.relatorio;

import br.gov.ce.autenticacao.service.AbstractFacade;
import br.gov.ce.saude.autenticacao.exception.ServiceException;
import br.gov.ce.saude.ponto.entidade.BatidaRel;
import br.gov.ce.saude.ponto.vo.HoraTrabalhadaRelVO;
import br.gov.ce.saude.ponto.vo.HorasTrabalhadaVO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author esmayktillesse
 */
@Stateless
@Path("horatrabalhada")
public class RelatorioHoraTrabalhadaFacadeREST extends AbstractFacade<BatidaRel, ServiceException> {

    public RelatorioHoraTrabalhadaFacadeREST() {
        super(BatidaRel.class);
    }

    @POST
    @Path("hora")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<HorasTrabalhadaVO> horasTrabalhada(HoraTrabalhadaRelVO ht) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date inicio = new Date(sdf.parse(ht.data_inicio).getTime());
        Date data_fim = new Date(sdf.parse(ht.data_fim).getTime());
        
        
        String consulta = "SELECT  \n"
                + "     p.nome,\n"
                + "     t_funcao.descricao funcao,\n"
                + "     t_organograma.sigla AS setor,\n"
                + "     TO_CHAR(batida_1, 'dd-MM-yyyy') AS data,\n"
                + "     (SELECT EXTRACT( DOW FROM batida_1)+1) dia_semana,\n"
                + "     h.descricao as horario,\n"
                + "     batida_1, \n"
                + "     batida_2,\n"
                + "\n"
                + "     CASE  WHEN (SELECT EXTRACT( DOW FROM batida_1)+1) IN (1,7) THEN 'FDS'\n"
                + "           ELSE 'SEMANA'\n"
                + "     END AS tipo_plantao,\n"
                + "\n"
                + "     TO_CHAR (CASE  WHEN (horas_batida >= horas_cumprir)\n"
                + "                  THEN horas_cumprir\n"
                + "                  WHEN horas_batida IS NULL \n"
                + "                  THEN '00:00:00'\n"
                + "                  ELSE horas_batida\n"
                + "              END, 'HH24:mm:ss') AS hora, \n"
                + "\n"
                + "     CASE  WHEN TO_CHAR(batida_1,  'HH24:mm:ss') < '19:00:00'\n"
                + "           THEN 'DIURNO' \n"
                + "           WHEN TO_CHAR(batida_1,  'HH24:mm:ss') >= '19:00:00'\n"
                + "           THEN 'NOTURNO'\n"
                + "           ELSE 'AUSENTE'\n"
                + "     END AS plantao\n"
                + "     \n"
                + "FROM t_batida_rel_escala e\n"
                + "LEFT JOIN t_escala_item ei ON (escala_item_fk = escala_item_pk),\n"
                + "     t_funcionario_horario fh,\n"
                + "     t_funcionario_contrato fc\n"
                + "LEFT JOIN t_funcao ON (funcao_fk = funcao_pk),\n"
                + "     e_base.t_pessoa p\n"
                + "LEFT JOIN e_base.t_empresa ON (empresa_fk = empresa_pk),\n"
                + "     t_horario h,\n"
                + "     e_base.t_organograma\n"
                + "              \n"
                + "WHERE \n"
                + "    funcionario_horario_fk = funcionario_horario_pk AND\n"
                + "    t_organograma.organograma_pk = fc.unidade_funcional_fk AND\n"
                + "    funcionario_contrato_fk = funcionario_contrato_pk AND\n"
                + "    fc.pessoa_fk = p.pessoa_pk AND\n"
                + "    fh.horario_fk = h.horario_pk AND\n"
                + "    batida_1 between :inicio and :fim";

        if ((ht.setor != null )) {
            consulta += "  AND  fc.unidade_funcional_fk = :setor \n";
        }

        if ((ht.funcao != null)) {
            consulta += "  AND  fc.funcao_fk = :funcao \n";
        } else {
            consulta += "  AND  fc.funcao_fk  IS NOT NULL \n";
        }

        if ((ht.empresa != null)) {
            consulta += "  AND  p.empresa_fk = :empresa \n";
        } else {
            consulta += "  AND  p.empresa_fk  IS NULL \n";
        }
        consulta += " ORDER BY\n"
                + "  p.nome ASC";

        Query query = getEntityManager().createNativeQuery(consulta, "horasTrabalhada");

        query.setParameter("inicio", inicio);
        query.setParameter("fim", data_fim);

        if ((ht.setor != null)) {
            query.setParameter("setor", ht.setor);
        }
        if ((ht.funcao != null)) {
            query.setParameter("funcao", ht.funcao);
        }
        if ((ht.empresa != null)) {
            query.setParameter("empresa", ht.empresa);
        }
       
        List<HorasTrabalhadaVO> lista = query.getResultList();

        return lista;
    }

}
