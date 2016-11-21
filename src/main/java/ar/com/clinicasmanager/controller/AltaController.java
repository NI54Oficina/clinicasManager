package ar.com.clinicasmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.clinicasmanager.entity.Alta;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.service.ConsultaService;

@RequestMapping(value = "/consulta")
@Controller
public class AltaController {
	
	@Autowired
	private ConsultaService consultaService; 

	@RequestMapping(value = "/{id}/alta", params = "form", produces = "text/html")
	public String createForm(@PathVariable("id") Long id, Model uiModel){	
		
		Consulta consulta = consultaService.findOne(id);
		uiModel.addAttribute("consulta", consulta);
		if(consulta.getAlta() == null){
			uiModel.addAttribute("form", new Alta());
		}
		else{
			uiModel.addAttribute("form", consulta.getAlta());
		}
		
		return "consultas/altaForm";
	}
	
	@RequestMapping(value = "/{id}/alta", method = RequestMethod.POST, produces = "text/html")
	public String save(@Valid Alta alta, @PathVariable("id") Long id,  Model uiModel){
		 
		Consulta consulta = consultaService.findOne(id);
		consultaService.darAlta(consulta, alta);
		
		return "redirect:/home";
	}	
}
