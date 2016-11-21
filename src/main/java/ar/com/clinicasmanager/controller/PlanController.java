package ar.com.clinicasmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Plan;
import ar.com.clinicasmanager.service.ConsultaService;

@RequestMapping(value = "/consulta")
@Controller
public class PlanController {
	
	@Autowired
	private ConsultaService consultaService;

	@RequestMapping(value = "/{id}/plan", params = "form", produces = "text/html")
	public String createForm(@PathVariable("id") Long id, Model uiModel){
		
		uiModel.addAttribute("form", new Plan());
		uiModel.addAttribute("idConsulta", id);
		
		return "consultas/planForm";
	}
	
	@RequestMapping(value = "/{idConsulta}/guardarPlan", method = RequestMethod.POST, produces = "text/html")
	public String save(@PathVariable("idConsulta") Long idConsulta, Plan plan, Model uiModel){
		
		Consulta consulta = consultaService.findOne(idConsulta);
		consulta.addPlan(plan);
		
		consultaService.save(consulta);
		
		return "redirect:/home";
	}	
}
