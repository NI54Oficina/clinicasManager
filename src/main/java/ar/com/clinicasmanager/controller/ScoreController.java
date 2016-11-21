package ar.com.clinicasmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.controller.form.ArtritisForm;
import ar.com.clinicasmanager.controller.form.QuickDashForm;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Score;
import ar.com.clinicasmanager.entity.enums.ScoreType;
import ar.com.clinicasmanager.ficha.FichaArtritis;
import ar.com.clinicasmanager.ficha.FichaMovilidadDedos;
import ar.com.clinicasmanager.service.ConsultaService;
import ar.com.clinicasmanager.service.ScoreService;

@Controller
@RequestMapping(value = "/consulta")
public class ScoreController {

	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private ConsultaService consultaService;
	
	@RequestMapping(value = "/{id}/score/michigan", method = RequestMethod.GET, produces = "text/html")
	public String getQuickDash(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		Score michigan = consulta.retrieveScore(ScoreType.MICHIGAN);

		uiModel.addAttribute(consulta);
		uiModel.addAttribute("michigan", michigan == null ? new QuickDashForm() : michigan);
		
		return "score/michigan";
	}
	
	@RequestMapping(value = "/{id}/score/quickDash", method = RequestMethod.GET, produces = "text/html")
	public String getMichigan(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		Score quickDash = consulta.retrieveScore(ScoreType.QUICK_DASH);

		uiModel.addAttribute(consulta);
		uiModel.addAttribute("quickDash", quickDash == null ? new QuickDashForm() : quickDash);
		
		return "score/quickDash";
	}	
	
	@RequestMapping(value = "/{id}/score/escalaDeDolor", method = RequestMethod.GET, produces = "text/html")
	public String getEscalaDeDolor(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		Score escalaDeDolor = consulta.retrieveScore(ScoreType.ESCALA_DE_DOLOR);

		uiModel.addAttribute(consulta);
		if(escalaDeDolor != null){
			uiModel.addAttribute("escalaDeDolorScore", escalaDeDolor.getScore());
		}
		
		return "score/escalaDeDolor";
	}	
	
	@RequestMapping(value = "/{id}/score/artritis", method = RequestMethod.GET, produces = "text/html")
	public String getArtritis(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		FichaArtritis artritis = consulta.getFichaArtritis();
		
		uiModel.addAttribute(consulta);
		uiModel.addAttribute("artritis", artritis == null ? new FichaArtritis() : artritis);
		
		return "score/artritis";
	}	
	
	@RequestMapping(value = "/{id}/score/movilidadDedos", method = RequestMethod.GET, produces = "text/html")
	public String getMovilidadDedos(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		
		uiModel.addAttribute(consulta);
		uiModel.addAttribute("fichaMovilidadDedos", new FichaMovilidadDedos());
		uiModel.addAttribute("fichasAnteriores", consulta.getFichasMovilidadDedos());
		
		return "score/movilidadDedos";
	}	
	
	@RequestMapping(value = "/{id}/score/movilidadDedos/{id2}", method = RequestMethod.GET, produces = "text/html")
	public String getMovilidadDedos(@PathVariable("id") Long id, @PathVariable("id2") Long id2, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		
		uiModel.addAttribute(consulta);
		for (FichaMovilidadDedos fichaMovilidadDedos : consulta.getFichasMovilidadDedos()) {
			if(fichaMovilidadDedos.getId().equals(id2)){
				uiModel.addAttribute("fichaMovilidadDedos", fichaMovilidadDedos);
			}
		}
		
		uiModel.addAttribute("fichasAnteriores", consulta.getFichasMovilidadDedos());
		
		return "score/movilidadDedos";
	}	
	
	@RequestMapping(value = "/{id}/score/quickDash", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Double completarQuickDash(@PathVariable("id") Long id, QuickDashForm quickDashForm){
		return scoreService.completarQuickDash(id, quickDashForm.getRespuestasInteger());
	}
	
	@RequestMapping(value = "/{id}/score/michigan", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Double completarMichigan(@PathVariable("id") Long id, QuickDashForm quickDashForm){
		return scoreService.completarMichigan(id, quickDashForm.getRespuestas());
	}	
	
	@RequestMapping(value = "/{id}/score/escalaDeDolor", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Integer completarEscalaDeDolor(@PathVariable("id") Long id, @RequestParam("value") Integer value){
		scoreService.completarEscalaDeDolor(id, value);
		return value;
	}
	
	@RequestMapping(value = "/{id}/score/artritis", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void completarArtritis(@PathVariable("id") Long id, ArtritisForm artritisForm){
		scoreService.completarArtrits(id, artritisForm.getRespuestas());
	}	
	
	@RequestMapping(value = "/{id}/score/movilidadDedos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void completarMovilidadDedos(@PathVariable("id") Long id, ArtritisForm artritisForm){
		scoreService.completarMovilidadDedos(id, artritisForm.getRespuestas());
	}	
	
	@RequestMapping(value = "/{id}/score/resumen", method = RequestMethod.GET, produces = "text/html")
	public String resumen(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		uiModel.addAttribute(consulta);
		return "score/resumen";
	}	
	
	@RequestMapping(value = "/{id}/score", method = RequestMethod.GET, produces = "text/html")
	public String list(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		uiModel.addAttribute(consulta);
		return "score/list";
	}	
}
