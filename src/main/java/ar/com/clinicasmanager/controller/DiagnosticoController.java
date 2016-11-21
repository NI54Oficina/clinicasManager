package ar.com.clinicasmanager.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Lesion;
import ar.com.clinicasmanager.entity.enums.Miembro;
import ar.com.clinicasmanager.service.ConsultaService;
import ar.com.clinicasmanager.service.DiagnosticoService;
import ar.com.clinicasmanager.service.NodoDiagnosticoService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping(value = "/consulta")
@Controller
public class DiagnosticoController {
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private DiagnosticoService diagnosticoService;
	
	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;
	
	private Gson jsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@RequestMapping(value = "/{id}/diagnostico", params = "form", produces = "text/html")
	public String diagnosticoForm(@PathVariable("id") Long id, Model uiModel){		
		
		Consulta consulta = consultaService.findOne(id);
		uiModel.addAttribute(consulta);
		uiModel.addAttribute(new Lesion());
		uiModel.addAttribute("miembros", Arrays.asList(Miembro.values()));
			
		return "diagnostico/form";
	}
	
	@RequestMapping(value = "/{idConsulta}/diagnostico/edit", produces = "text/html")
	public String edit(@PathVariable("idConsulta") Long idConsulta, Model uiModel){		
		
		Consulta consulta = consultaService.findOne(idConsulta);
		uiModel.addAttribute(consulta);
			
		return "diagnostico/edit";
	}	
	
	@RequestMapping(value = "/{idConsulta}/diagnostico", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@PathVariable("idConsulta") Long idConsulta, Lesion lesion, Model uiModel){		
		Consulta consulta = consultaService.findOne(idConsulta);
		
		if(nodoDiagnosticoService.isNodoSinDiagnostico(lesion.getPatologia().getId())) {
			diagnosticoService.createSinDiagnostico(consulta);
		} else {
			diagnosticoService.createDiagnostico(consulta, lesion);
		}
		
		consultaService.save(consulta);
		
		return jsonBuilder.toJson(consulta.getDiagnostico());
	}
	
	@RequestMapping(value = "/{idConsulta}/diagnostico/lesion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addLesion(@PathVariable("idConsulta") Long idConsulta, Lesion lesion, Model uiModel){		
		Consulta consulta = consultaService.findOne(idConsulta);
		
		if(nodoDiagnosticoService.isNodoSinDiagnostico(lesion.getPatologia().getId())) {
			diagnosticoService.createSinDiagnostico(consulta);
		} else {
			diagnosticoService.addLesion(consulta, lesion);
		}
		
		consultaService.save(consulta);
		
		return jsonBuilder.toJson(consulta.getDiagnostico());
	}
	
	@RequestMapping(value = "/{idConsulta}/diagnostico/{idLesion}", method = RequestMethod.DELETE, produces = "text/html")
	@ResponseBody
	public void deleteLesion(@PathVariable("idConsulta") Long idConsulta, @PathVariable("idLesion") Long idLesion, Model uiModel){		
		Consulta consulta = consultaService.findOne(idConsulta);
		
		diagnosticoService.deleteLesion(consulta, idLesion);
		
		consultaService.save(consulta);
	}	
	
	@RequestMapping(value = "/{id}/diagnostico", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
			
		diagnosticoService.createSinDiagnostico(consulta);
		consultaService.save(consulta);
		
		uiModel.addAttribute(consulta);
		
		return "diagnostico/list";
	}	
	
	@RequestMapping(value = "/{id}/diagnostico/anterior/{id2}", method = RequestMethod.DELETE, produces = "text/html")
	public String deleteDiagnosticoAnterior(@PathVariable("id") Long id, @PathVariable("id2") Long idDiagnostico, Model uiModel){

		diagnosticoService.delete(idDiagnostico);
		
		uiModel.addAttribute(consultaService.findOne(id));
		return "diagnostico/list";
	}	
	
	@RequestMapping(value = "/{id}/diagnostico", method = RequestMethod.GET, produces = "text/html")
	public String list(@PathVariable("id") Long id, Model uiModel){
		uiModel.addAttribute(consultaService.findOne(id));		
		
		return "diagnostico/list";
	}
	
	@RequestMapping(value = "/diagnostico/tree", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tree(){
		return jsonBuilder.toJson(nodoDiagnosticoService.getTreeRoot().getChildren());
	}

}
