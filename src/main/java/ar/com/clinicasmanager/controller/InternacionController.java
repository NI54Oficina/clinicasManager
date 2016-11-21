package ar.com.clinicasmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Internacion;
import ar.com.clinicasmanager.entity.enums.EstadoInternacion;
import ar.com.clinicasmanager.service.ConsultaService;

@RequestMapping(value = "/consulta")
@Controller
public class InternacionController {
	
	@Autowired
	private ConsultaService consultaService;
	
	@RequestMapping(value = "/{id}/internacion", params = "form", produces = "text/html")
	public String internacionForm(@PathVariable("id") Long id, Model uiModel){	
		
		uiModel.addAttribute(consultaService.findOne(id));
		uiModel.addAttribute(new Internacion());
		
		return "consultas/internacionForm";
	}
	
	@RequestMapping(value = "/{idConsulta}/internacion", method = RequestMethod.POST, produces = "text/html")
	public String save(@PathVariable("idConsulta") Long idConsulta, @Valid Internacion internacion, Model uiModel){	
		
		Consulta consulta = consultaService.findOne(idConsulta);
		
		if(consulta.getEstado().getConInternacion()){
			throw new RuntimeException("Ya hay una internación en curso");
		}
		
		consulta.addInternacion(internacion);
		consulta.getEstado().setConInternacion(true);
		
		consultaService.save(consulta);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/{idConsulta}/internacion/{idInternacion}", method = RequestMethod.POST, produces = "text/html")
	@ResponseBody
	public void update(@PathVariable("idConsulta") Long idConsulta, @PathVariable("idInternacion") Long idInternacion, @Valid Internacion internacion, Model uiModel){	
		
		Consulta consulta = consultaService.findOne(idConsulta);
		consulta.getEstado().setConInternacion(false);
		
		Internacion internacionDb = consultaService.getInternacion(consulta, idInternacion);
		internacionDb.setFechaSalida(internacion.getFechaSalida());
		internacionDb.setEstado(EstadoInternacion.FINALIZADA);
		
		consultaService.save(consulta);
		
	}	
}
