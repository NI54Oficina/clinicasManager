package ar.com.clinicasmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Evolucion;
import ar.com.clinicasmanager.service.ConsultaService;

@RequestMapping(value = "/consulta")
@Controller
public class EvolucionController {

	@Autowired
	private ConsultaService consultaService;
	
	@RequestMapping(value = "/{id}/evolucion", params = "maxResult", method = RequestMethod.GET, produces = "text/html")
	public String list(@PathVariable("id") Long id, @RequestParam(value="maxResult") Integer maxResult, Model uiModel){
		
		Consulta consulta = consultaService.findOne(id);
		
		uiModel.addAttribute("evoluciones", consultaService.getEvoluciones(consulta, maxResult));
		uiModel.addAttribute(consulta);
		
		return "consultas/evolucionList";
	}
	
	@RequestMapping(value = "/{id}/evolucion", method = RequestMethod.POST)
	@ResponseBody
	public void create(@PathVariable("id") Long id, @RequestParam(value="evolucion") String evolucion, Model uiModel) {
		
		Consulta consulta = consultaService.findOne(id);
		consulta.addEvolucion(new Evolucion(evolucion));
		
		consultaService.save(consulta);
	}
}
