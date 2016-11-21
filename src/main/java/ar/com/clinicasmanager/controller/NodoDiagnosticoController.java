package ar.com.clinicasmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.service.NodoDiagnosticoService;

@RequestMapping(value = "/nodoDiagnostico")
@Controller
public class NodoDiagnosticoController {
	
	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list() {
		return "nodoDiagnostico/list";
	}
	
	@RequestMapping(value = "/reload", method = RequestMethod.GET, produces = "text/html")
	public void reloadRoot() {
		nodoDiagnosticoService.reloadTreeRoot();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
	public String view(@PathVariable("id") Long id, Model uiModel) {
		NodoDiagnostico nodo = nodoDiagnosticoService.getNodo(id);
		
		uiModel.addAttribute("nodo", nodo);
		
		return "nodoDiagnostico/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "text/html")
	public String save(@PathVariable("id") Long id, NodoDiagnostico nodo, Model uiModel) {
		NodoDiagnostico dbNodo = nodoDiagnosticoService.getNodo(id);
		
		dbNodo.setDisplayInSumamry(nodo.getDisplayInSumamry());
		dbNodo.setLabel(nodo.getLabel());
		nodoDiagnosticoService.save(dbNodo);
		
		uiModel.addAttribute("nodo", dbNodo);
		
		return "nodoDiagnostico/list";
	}
}
