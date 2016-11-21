package ar.com.clinicasmanager.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.dao.ConfiguracionFichaDAO;
import ar.com.clinicasmanager.dao.FichaDAO;
import ar.com.clinicasmanager.entity.ConfiguracionFicha;
import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.entity.enums.Miembro;
import ar.com.clinicasmanager.service.NodoDiagnosticoService;

@Controller
public class ConfiguracionFichaController {
	
	@Autowired
	private FichaDAO fichaDAO; 
	
	@Autowired
	private ConfiguracionFichaDAO configuracionFichaDAO;
	
	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;	
	
	@RequestMapping(value = "/ficha/{id}/configurar", produces = "text/html")
	public String formConfiguration(@PathVariable("id") Long id, Model uiModel){
		
		ConfiguracionFicha configuracionFicha = new ConfiguracionFicha(fichaDAO.findOne(id));
		
		uiModel.addAttribute("miembros", Arrays.asList(Miembro.values()));
		uiModel.addAttribute("configuracionFicha", configuracionFicha);
		
		return "ficha/configurar";
	}		
	
	@RequestMapping(value = "/ficha/configurar", method = RequestMethod.POST, produces = "text/html")
	public String saveConfiguration(ConfiguracionFicha configuracionFichaBase, @RequestParam("nodosSeleccionados") String idNodos, Model uiModel){
		
		List<NodoDiagnostico> nodos = new ArrayList<NodoDiagnostico>();
		for (String id : idNodos.split(" ")) {
			nodos.add(nodoDiagnosticoService.getNodo(Long.valueOf(id)));
		}
		
		Ficha ficha = fichaDAO.findOne(configuracionFichaBase.getFicha().getId());
		
		for (NodoDiagnostico nodoDiagnostico : nodos) {
			ConfiguracionFicha configuracionFicha = new ConfiguracionFicha();
			configuracionFicha.setDiagnostico(nodoDiagnostico);
			configuracionFicha.setFicha(ficha);
			configuracionFicha.setPeriodoSemanas(configuracionFichaBase.getPeriodoSemanas());
			configuracionFicha.setFullName(nodoDiagnosticoService.getFullName(nodoDiagnostico));
			configuracionFichaDAO.save(configuracionFicha);
		}
		
		return "redirect:/ficha";
	}			
	
	@RequestMapping(value = "/ficha/{id}/configuraciones", produces = "text/html")
	public String listConfigurations(@PathVariable("id") Long id, Model uiModel){
		
		uiModel.addAttribute("configuraciones",configuracionFichaDAO.findByFicha(fichaDAO.findOne(id)));
		
		return "ficha/configuraciones";
	}	
	
	@RequestMapping(value = "/ficha/configuracion/{id}", method = RequestMethod.DELETE, produces = "text/html")
	@ResponseBody
	public void delete(@PathVariable("id") Long id){
		
		configuracionFichaDAO.delete(id);
		
	}		
}
