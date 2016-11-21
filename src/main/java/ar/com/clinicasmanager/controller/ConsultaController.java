package ar.com.clinicasmanager.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.dao.UsuarioDAO;
import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.DatosInicialesConsulta;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.enums.LugarAccidente;
import ar.com.clinicasmanager.entity.enums.Mecanismo;
import ar.com.clinicasmanager.entity.enums.Miembro;
import ar.com.clinicasmanager.locking.ConsultaLockingManager;
import ar.com.clinicasmanager.predicate.ConsultaActivaPredicate;
import ar.com.clinicasmanager.service.ConsultaService;
import ar.com.clinicasmanager.service.PacienteService;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@RequestMapping(value = "/consulta")
@Controller
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ConsultaLockingManager lockingManager;
	
	@RequestMapping(value = "/crearConsulta")
	public String create(@RequestParam(value = "dni") String dni, Model uiModel) {

		Paciente paciente = pacienteService.findByDni(dni);
		
		Consulta consulta = new Consulta();
		consulta.setPaciente(paciente);
		consulta.setUsuarioCreador(usuarioDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
		consultaService.save(consulta);

		return "redirect:/consulta/" + consulta.getId() + "/datosIniciales?form";
	}
	
	@RequestMapping(value = "/{id}/datosIniciales", params = "form", produces = "text/html")
	public String createForm(@PathVariable("id") Long id, Model uiModel){
		
		Consulta consulta = consultaService.findOne(id);
		
		uiModel.addAttribute(consulta);
		populatePrimeraVezForm(consulta.getDatosIniciales() == null ? new DatosInicialesConsulta() : consulta.getDatosIniciales(), uiModel);
		uiModel.addAttribute("fichasPendientes", consultaService.getFichasPendientes(consulta));
		
		return "consultas/create";
	}


	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	@ResponseBody
	public Long save(@PathVariable("id") Long id, @Valid DatosInicialesConsulta datosIniciales, Model uiModel){
		
		Consulta consulta = consultaService.findOne(id);
		
		if(!datosIniciales.getTraumatico()){
			datosIniciales.setLugarAccidente(null);
			datosIniciales.setMecanismo(null);
		}
		
		consulta.getEstado().setIncompleta(false);
		consulta.setDatosIniciales(datosIniciales);
		
		consultaService.save(consulta);
		return consulta.getId();
	}
	
	@RequestMapping(value = "/{id}/datosIniciales", method = RequestMethod.GET, produces = "text/html")
	public String verDatosIniciales(@PathVariable("id") Long id, Model uiModel) {
		
		Consulta consulta = consultaService.findOne(id);	
		
		uiModel.addAttribute("datosIniciales", consulta.getDatosIniciales());
		uiModel.addAttribute("consulta", consulta);
		uiModel.addAttribute("paciente", consulta.getPaciente());
		
		return "consultas/verDatosIniciales";
	}	
	
	@RequestMapping(value = "/{id}/fichasPendientes", method = RequestMethod.GET, produces = "text/html")
	public String fichasPendientes(@PathVariable("id") Long id, Model uiModel) {
		Consulta consulta = consultaService.findOne(id);
		
		uiModel.addAttribute(consulta);
		uiModel.addAttribute("fichasPendientes", consultaService.getFichasPendientes(consulta));
		
		return "consultas/fichaModal";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
	public String view(@PathVariable("id") Long id, Model uiModel){
		
		Consulta consulta = consultaService.findOne(id);
		
		if(consulta.getEstado().getIncompleta()){
			return "redirect:/consulta/" + consulta.getId() + "/datosIniciales?form";
		}
		
		uiModel.addAttribute("conBotonera", true);
		uiModel.addAttribute(consulta);
		
		Integer cantidadFichas = consulta.getRespuestasFichas().size();
		cantidadFichas = consulta.getFichaArtritis() == null ? cantidadFichas : cantidadFichas + 1;
		cantidadFichas = consulta.getFichasMovilidadDedos().isEmpty() ? cantidadFichas : cantidadFichas + 1;
		
		uiModel.addAttribute("cantidadFichas", cantidadFichas );
		
		uiModel.addAttribute("tratamientos", consultaService.getTratamientoAndCirugiasOrderedByDate(consulta));
		
		//TODO: revisar
		Cirugia cirugiaPendiente = consultaService.getCirugiaPendiente(consulta); 
		if(cirugiaPendiente != null){
			uiModel.addAttribute(cirugiaPendiente.getFechaCirugia().before(new Date()) ? "cirugiaPendiente" : "cirugiaProgramada", cirugiaPendiente);
		}
			
		uiModel.addAttribute("sinDiagnostico", consulta.getEstado().getSinDiagnostico());
		uiModel.addAttribute("fichasPendientes", consultaService.getFichasPendientes(consulta));
		uiModel.addAttribute("internacionesList", consulta.getInternaciones());
		
		if(consulta.getEstado().getConInternacion()){
			uiModel.addAttribute("internacion", Iterables.getLast(consulta.getInternaciones()));
		}
			
		if(!consulta.getPlanes().isEmpty()){
			uiModel.addAttribute("plan", Iterables.getFirst(consulta.getPlanes(), null));
		}
		
		if(consulta.getDatosIniciales().getFechaAccidente() != null){
			uiModel.addAttribute("tiempoTranscurrido1", 
				new Duration(consulta.getDatosIniciales().getFechaAccidente().getTime(), new Date().getTime()).toStandardDays().getDays());
		}
		uiModel.addAttribute("tiempoTranscurrido2", 
				new Duration(consulta.getFechaPrimerConsulta().getTime(), new Date().getTime()).toStandardDays().getDays());
		
		Cirugia ultimaCirugia = consultaService.getUltimaCirugiaRealizada(consulta);
		if(ultimaCirugia != null){
			uiModel.addAttribute("fechaUltimaCirugia", ultimaCirugia.getFechaCirugia());
			uiModel.addAttribute("tiempoTranscurrido3", 
					new Duration(ultimaCirugia.getFechaCirugia().getTime(), new Date().getTime()).toStandardDays().getDays());
		}
		
		if(!consulta.getEstado().getSegundaVez()){
			consulta.getEstado().setSegundaVez(true);
			consultaService.save(consulta);
		}
		
		return "consultas/view";
	}
	
	@RequestMapping(value = "/verConsultas", produces = "text/html")
	public String list(@RequestParam("dni") String dni, Model uiModel){
		Paciente paciente = pacienteService.findByDni(dni);
		List<Consulta> consultas = consultaService.findByPaciente(paciente);
		List<Consulta> consultasActivas =  Lists.newArrayList(Iterables.filter(consultas, new ConsultaActivaPredicate()));
		
		if(consultasActivas.size() == 1){
			return "redirect:/consulta/" + Iterables.getOnlyElement(consultasActivas).getId();
		}
		else{
			uiModel.addAttribute("consultas", consultas);
			uiModel.addAttribute("paciente", paciente);
			return "consultas/list";
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id){
		consultaService.deleteConsulta(id);
	}
	
	@RequestMapping(value = "{id}/liberar")
	@ResponseBody
	public void liberar(@PathVariable("id") Long id) {
		lockingManager.freeLockOnConsulta(id);
	}	
	
	@RequestMapping(value = "{id}/keepAlive")
	@ResponseBody
	public void keepAlive(@PathVariable("id") Long id) {
		lockingManager.getLockOnConsulta(id);
	}
	
	private void populatePrimeraVezForm(DatosInicialesConsulta datosIniciales, Model uiModel){
		uiModel.addAttribute("form", datosIniciales);
		uiModel.addAttribute("mecanismos", Arrays.asList(Mecanismo.values()));
		uiModel.addAttribute("lugares", Arrays.asList(LugarAccidente.values()));
		uiModel.addAttribute("miembros", Arrays.asList(Miembro.values()));
	}

}
