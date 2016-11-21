package ar.com.clinicasmanager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.clinicasmanager.dao.CirugiaDAO;
import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.EstadoCirugia;
import ar.com.clinicasmanager.exception.CirugiaProgramadaException;
import ar.com.clinicasmanager.service.ConsultaService;
import ar.com.clinicasmanager.service.GoogleCalendarService;
import ar.com.clinicasmanager.service.TratamientoService;

import com.google.common.collect.Iterables;

@RequestMapping(value = "/consulta")
@Controller
public class CirugiaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CirugiaController.class);

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private GoogleCalendarService googleCalendarService;
	
	@Autowired
	private CirugiaDAO cirugiaDAO;
	
	@Autowired
	private TratamientoService tratamientoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}	
	
	@RequestMapping(value = "/{id}/cirugia", params = "form", produces = MediaType.TEXT_HTML_VALUE)
	public String createForm(@PathVariable("id") Long id, Model uiModel) throws Exception{	
		
		uiModel.addAttribute(consultaService.findOne(id));
		uiModel.addAttribute(new Cirugia());
		uiModel.addAttribute("calendarios", googleCalendarService.getCalendarios());
		
		return "consultas/cirugiaForm";
	}
	
	@RequestMapping(value = "/{idConsulta}/cirugia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Long save(@PathVariable("idConsulta") Long idConsulta, @Valid Cirugia cirugia, Model uiModel) {		
		
		Assert.notNull(cirugia.getTratamiento().getId(), "Se debe seleccionar un tratamiento");
		
		Consulta consulta = consultaService.findOne(idConsulta);
		if(consulta.getEstado().getConCirugiaPendiente()) {
			throw new CirugiaProgramadaException();
		}
		
		Tratamiento tempTratamiento = tratamientoService.createTratamiento(cirugia.getTratamiento().getId());
		cirugia.setTratamiento(tempTratamiento.getTratamiento());
		cirugia.setFullName(tempTratamiento.getFullName());
		
		consulta.getEstado().setConCirugiaPendiente(true);
		consulta.addCirugia(cirugia);
		
		consultaService.save(consulta);
		
		return Iterables.getLast(consulta.getCirugias()).getId();
		
	}
	
	@RequestMapping(value = "/{id}/cirugia/{id2}", produces = MediaType.TEXT_HTML_VALUE)
	public String updateForm(@PathVariable("id") Long id, @PathVariable("id2") Long id2, Model uiModel){	
		
		uiModel.addAttribute(consultaService.findOne(id));
		uiModel.addAttribute(cirugiaDAO.findOne(id2));
		
		return "consultas/cirugiaForm";
	}
	
	@RequestMapping(value = "/{idConsulta}/cirugia/{id}", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public void update(@PathVariable("idConsulta") Long idConsulta, @PathVariable("id") Long id, @Valid Cirugia cirugia, Model uiModel) throws Exception{		

		Consulta consulta = consultaService.findOne(idConsulta);
		Cirugia cirugiaDb = cirugiaDAO.findOne(cirugia.getId());
		
		cirugiaDb.setDescripcion(cirugia.getDescripcion());
		cirugiaDb.setFechaCirugia(cirugia.getFechaCirugia());
		cirugiaDb.setLugar(cirugia.getLugar());
		
		Assert.notNull(cirugia.getTratamiento().getId(), "Se debe seleccionar un tratamiento");
		
		if(!cirugia.getTratamiento().getId().equals(cirugiaDb.getTratamiento().getId())){
			Tratamiento tempTratamiento = tratamientoService.createTratamiento(cirugia.getTratamiento().getId());
			cirugiaDb.setTratamiento(tempTratamiento.getTratamiento());
			cirugiaDb.setFullName(tempTratamiento.getFullName());
		}
		
		if(cirugiaDb.getGoogleEventId() != null){
			String eventoId = googleCalendarService.editarEvento(consulta, cirugiaDb);
			cirugiaDb.setGoogleEventId(eventoId);
		}
		
		cirugiaDAO.save(cirugiaDb);
		
	}	
	
	@RequestMapping(value = "/{id}/cirugia/{id2}/confirmar", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String confirmar(@PathVariable("id") Long id, @PathVariable("id2") Long id2, Model uiModel){		
		
		Cirugia cirugia = cirugiaDAO.findOne(id2);
		
		cirugia.setEstado(EstadoCirugia.REALIZADA);
		
		cirugiaDAO.save(cirugia);
		
		Consulta consulta = consultaService.findOne(id);
		consulta.getEstado().setConCirugiaPendiente(false);
		consultaService.save(consulta);
		
		return "redirect:/consulta/" + id ;
		
	}	
	
	@RequestMapping(value = "/{idConsulta}/cirugia/{id}/crearEvento", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public void crearEvento(@PathVariable("idConsulta") Long idConsulta, @PathVariable("id") Long id, @RequestParam("calendarId") String calendarId, Model uiModel) throws Exception{		
		
		Consulta consulta = consultaService.findOne(idConsulta);
		Cirugia cirugia = cirugiaDAO.findOne(id);
		String eventId = googleCalendarService.crearEvento(consulta, cirugia, calendarId);
		
		cirugia.setGoogleEventId(eventId);
		cirugia.setGoogleCalendarId(calendarId);
		
		cirugiaDAO.save(cirugia);
	}
	
	@RequestMapping(value = "/{idConsulta}/cirugia/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("idConsulta") Long idConsulta, @PathVariable("id") Long id, Model uiModel) throws Exception{		
		
		Cirugia cirugia = cirugiaDAO.findOne(id);
		
		if(cirugia.getGoogleEventId() != null) {
			try{
				googleCalendarService.eliminarEvento(cirugia.getGoogleCalendarId(), cirugia.getGoogleEventId());
			}
			catch(Exception e) {
				LOGGER.error("Error eliminando evento del calendario", e);
			}
		}
		
		cirugiaDAO.delete(id);
		
		if(cirugia.getEstado().equals(EstadoCirugia.PENDIENTE)) {
			Consulta consulta = consultaService.findOne(idConsulta);
			consulta.getEstado().setConCirugiaPendiente(false);
			consultaService.save(consulta);
		}
	}	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Valores ingresados no válidos")
	@ExceptionHandler(IllegalArgumentException.class)
	public void conflict() {
		// Nothing to do
	}
		
}
