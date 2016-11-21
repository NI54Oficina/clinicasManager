package ar.com.clinicasmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.service.ConsultaService;
import ar.com.clinicasmanager.service.NodoTratamientoService;
import ar.com.clinicasmanager.service.TratamientoService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping(value = "/consulta")
@Controller
public class TratamientoController {
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private TratamientoService tratamientoService;
	
	@Autowired
	private NodoTratamientoService nodoTratamientoService;	
	
	private Gson jsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	@RequestMapping(value = "/{id}/tratamiento", params = "form", produces = "text/html")
	public String create(@PathVariable("id") Long id, Model uiModel){		
		Consulta consulta = consultaService.findOne(id);
		uiModel.addAttribute(consulta);
		uiModel.addAttribute(new Tratamiento());
		
		return "tratamiento/create";
	}
	
	@RequestMapping(value = "/{idConsulta}/tratamiento", method = RequestMethod.POST, produces = "text/html")
	@ResponseBody
	public void save(@PathVariable("idConsulta") Long idConsulta, Tratamiento tratamiento, Model uiModel){		
		Consulta consulta = consultaService.findOne(idConsulta);
		
		consulta.addTratamiento(tratamientoService.createTratamiento(tratamiento.getTratamiento().getId(), tratamiento.getMiembro()));
		
		consultaService.save(consulta);
	}
	
	@RequestMapping(value = "{idConsulta}/tratamiento/{idTratamiento}", method = RequestMethod.DELETE, produces = "text/html")
	@ResponseBody
	public void delete(@PathVariable("idConsulta") Long idConsulta, @PathVariable("idTratamiento") Long idTratamiento, Model uiModel){		
		tratamientoService.delete(idTratamiento);
	}	
	
	@RequestMapping(value = "/{id}/tratamiento", method = RequestMethod.GET, produces = "text/html")
	public String list(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		uiModel.addAttribute(consulta);
		
		return "tratamiento/list";
	}
	
	@RequestMapping(value = "/tratamiento/tree", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String tree(){
		return jsonBuilder.toJson(nodoTratamientoService.getTreeRoot().getChildren());
	}
}
