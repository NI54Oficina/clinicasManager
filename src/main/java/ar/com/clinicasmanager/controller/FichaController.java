package ar.com.clinicasmanager.controller;

import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.util.Strings;

import ar.com.clinicasmanager.dao.FichaDAO;
import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.FichaPregunta;

@Controller
public class FichaController {

	@Autowired
	private FichaDAO fichaDAO; 
	
	@RequestMapping(value = "/ficha", params = "form", produces = "text/html")
	public String createForm(Model uiModel){
		
		uiModel.addAttribute("form", new Ficha());
		
		return "ficha/create";
	}
	
	@RequestMapping(value = "/ficha", method = RequestMethod.POST , produces = "text/html")
	public String save(@Valid Ficha ficha,  Model uiModel){
		
		for (Iterator<FichaPregunta> iterator = ficha.getPreguntas().iterator(); iterator.hasNext(); ) {
			FichaPregunta pregunta = iterator.next();
		    if (Strings.isNullOrEmpty(pregunta.getPregunta())) {
		        iterator.remove();
		    }
		}
		
		fichaDAO.save(ficha);
		
		return "redirect:/ficha";
	}
	
	@RequestMapping(value = "/ficha/{id}", produces = "text/html")
	public String view(@PathVariable("id") Long id, Model uiModel){
		
		uiModel.addAttribute("ficha", fichaDAO.findOne(id));
		
		return "ficha/view";
	}	
	
	@RequestMapping(value = "/ficha", produces = "text/html")
	public String list(Model uiModel){
		
		uiModel.addAttribute("fichas", fichaDAO.findAll());
		
		return "ficha/list";
	}	
	
	@RequestMapping(value = "/ficha/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id){
		
		fichaDAO.delete(id);
		
		return "redirect:/ficha";
	}	
	
	@RequestMapping(value = "/ficha/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Model uiModel){
		
		Ficha ficha = fichaDAO.findOne(id);
		
		//TODO: repensar esto en javascript, que es un asco
		// Sirve para arreglar el ConcurrentException que da el JSTL
		for (FichaPregunta fichaPregunta : ficha.getPreguntas()) {
			
			int size = fichaPregunta.getOpciones().size() % 3;
			
			if(size == 1){
				fichaPregunta.getOpciones().add("");
				fichaPregunta.getOpciones().add("");
			}
			if(size == 2){
				fichaPregunta.getOpciones().add("");
			}
		}
		
		uiModel.addAttribute("form", ficha);
		
		return "ficha/create";
	}	
	
}
