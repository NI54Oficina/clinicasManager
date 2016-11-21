package ar.com.clinicasmanager.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.dao.FichaDAO;
import ar.com.clinicasmanager.dao.RespuestasFichaDAO;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.FichaPregunta;
import ar.com.clinicasmanager.entity.RespuestaFichaPregunta;
import ar.com.clinicasmanager.entity.RespuestasFicha;
import ar.com.clinicasmanager.predicate.PreguntaTieneMismoIdPredicate;
import ar.com.clinicasmanager.service.ConsultaService;

import com.google.common.collect.Iterables;

@Controller
public class RespuestasFichaController {
	
	@Autowired
	private FichaDAO fichaDAO; 
	
	@Autowired
	private RespuestasFichaDAO respuestasFichaDAO;
	
	@Autowired
	private ConsultaService consultaService; 

	@RequestMapping(value = "/consulta/{idConsulta}/ficha/{id}/responder", produces = "text/html")
	public String createForm(@PathVariable("idConsulta") Long idConsulta, @PathVariable("id") Long id, Model uiModel){
		
		uiModel.addAttribute("consulta", consultaService.findOne(idConsulta));
		uiModel.addAttribute("respuestaFicha", new RespuestasFicha(fichaDAO.findOne(id)));
		
		return "ficha/responder";
	}		
	
	@RequestMapping(value = "/consulta/{idConsulta}/ficha/responder", method = RequestMethod.POST, produces = "text/html")
	public String save(@PathVariable("idConsulta") Long idConsulta, @Valid RespuestasFicha respuestasFicha, Model uiModel){
		
		Ficha ficha = fichaDAO.findOne(respuestasFicha.getFicha().getId());
		respuestasFicha.setFicha(ficha);
		respuestasFicha.setDate(new Date());
		
		for (RespuestaFichaPregunta respuesta : respuestasFicha.getRespuestasPregunta()) {
			respuesta.setFichaPregunta(findById(ficha.getPreguntas(), respuesta.getFichaPregunta().getId()));
		}
		
		Consulta consulta = consultaService.findOne(idConsulta);
		consulta.addRespuestasFicha(respuestasFicha);
		consultaService.save(consulta);
		
		return "redirect:/consulta/" + idConsulta + "/fichaRespuesta";
	}	
	
	@RequestMapping(value = "/consulta/{idConsulta}/fichaRespuesta/{id}", produces = "text/html")
	public String view(@PathVariable("idConsulta") Long idConsulta, @PathVariable("id") Long id, Model uiModel){
		uiModel.addAttribute("respuestasFicha", respuestasFichaDAO.findOne(id));
		uiModel.addAttribute("consulta", consultaService.findOne(idConsulta));
		
		return "ficha/viewRespuestas";
	}	
	
	@RequestMapping(value = "/consulta/{idConsulta}/fichaRespuesta", produces = "text/html")
	public String list(@PathVariable("idConsulta") Long idConsulta, Model uiModel){
		Consulta consulta = consultaService.findOne(idConsulta);
		
		List<Ficha> fichas = fichaDAO.findAll();
		
		uiModel.addAttribute("fichas", fichas); 
		uiModel.addAttribute(consulta);
		
		return "ficha/respuestasList";
	}	
	
	@RequestMapping(value = "/consulta/{idConsulta}/fichaRespuesta/{id}", method = RequestMethod.DELETE, produces = "text/html")
	@ResponseBody
	public void delete(@PathVariable("idConsulta") Long idConsulta, @PathVariable("id") Long id, Model uiModel){
		respuestasFichaDAO.delete(id);
	}		
	
	private FichaPregunta findById(List<FichaPregunta> preguntas, final Long id){
		return Iterables.find(preguntas, new PreguntaTieneMismoIdPredicate(id));
	}	
}
