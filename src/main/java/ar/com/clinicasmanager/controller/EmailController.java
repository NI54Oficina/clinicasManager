package ar.com.clinicasmanager.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.clinicasmanager.email.EmailSender;
import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Diagnostico;
import ar.com.clinicasmanager.entity.Evolucion;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.service.ConsultaService;

import com.google.common.collect.Iterables;

@Controller
@RequestMapping(value = "/consulta")
public class EmailController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private EmailSender emailSender;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@RequestMapping(value = "/{id}/crearEmail", produces = "text/html", method = RequestMethod.GET)
	public String crearEmail(@PathVariable("id") Long id, Model uiModel){
		
		Consulta consulta = consultaService.findOne(id);
		
		String emailContent = crearEmailContentBasico(consulta).toString();
		
		uiModel.addAttribute("emailContent", emailContent.replaceAll("<br>", ""));
		uiModel.addAttribute(consulta);
		
		return "consultas/crearEmail";
	}
	
	@RequestMapping(value = "/{id}/crearEmail/full", produces = "text/html", method = RequestMethod.GET)
	public String crearEmailFull(@PathVariable("id") Long id, Model uiModel){
		
		Consulta consulta = consultaService.findOne(id);
		StringBuilder builder = crearEmailContentBasico(consulta);
		
		if(consulta.getDiagnostico() != null){
			builder.append("Diagnósticos:" + System.lineSeparator());
			builder.append(dateFormat.format(consulta.getDiagnostico().getFechaDiagnostico()) + " - " + consulta.getDiagnostico().getResumen());
			for (Diagnostico diagnostico : consulta.getDiagnosticosAnteriores()) {
				builder.append(dateFormat.format(diagnostico.getFechaDiagnostico()) + " - " + diagnostico.getResumen() + System.lineSeparator());
			}
			builder.append(System.lineSeparator());
		}
		
		if(!consulta.getTratamientos().isEmpty() || !consulta.getCirugias().isEmpty()){
			List<Tratamiento> tratamientos = consultaService.getTratamientoAndCirugiasOrderedByDate(consulta);
			builder.append("Tratamientos:" + System.lineSeparator());
			for (Tratamiento tratamiento : tratamientos) {
				builder.append(dateFormat.format(tratamiento.getFechaInicioTratamiento()) + " - " + tratamiento.getFullName() + System.lineSeparator());
			}
			builder.append(System.lineSeparator());
		}
		
		if(!consulta.getEvoluciones().isEmpty()){
			builder.append("Evoluciones:" + System.lineSeparator());
			for (Evolucion evolucion : consulta.getEvoluciones()) {
				builder.append(dateFormat.format(evolucion.getFecha()) + " - " + evolucion.getTexto() + System.lineSeparator());
			}
			builder.append(System.lineSeparator());
		}
		
		if(consulta.getAlta() != null){
			builder.append("Alta: " + dateFormat.format(consulta.getAlta().getFecha()) + System.lineSeparator());
			builder.append(consulta.getAlta().getTexto());
		}
		
		uiModel.addAttribute("emailContent", builder.toString().replaceAll("<br>", ""));
		uiModel.addAttribute(consulta);
		
		return "consultas/crearEmail";
	}
	
	@RequestMapping(value = "/enviarEmail", method = RequestMethod.POST)
	@ResponseBody
	public void enviarEmail(@RequestParam(value = "destino") String destino, 
						    @RequestParam(value = "asunto") String asunto,
						    @RequestParam(value = "mensaje") String mensaje){
		
		emailSender.enviarMail(destino, asunto, mensaje);
		
	}
	
	private StringBuilder crearEmailContentBasico(Consulta consulta){
		StringBuilder builder = new StringBuilder();
		
		if(consulta.getEstado().getSegundaVez()){
			builder.append("Nombre: " + consulta.getPaciente().getNombre() + System.lineSeparator());
			builder.append("Dni: " + consulta.getPaciente().getDni() + System.lineSeparator());
			
			if(!consulta.getEvoluciones().isEmpty()){
				builder.append("Última evolución:" + System.lineSeparator() + Iterables.getLast(consulta.getEvoluciones()).getTexto() + System.lineSeparator());
			}
			
			if(!consulta.getTratamientos().isEmpty() || !consulta.getCirugias().isEmpty()){
				builder.append("Último tratamiento:" + System.lineSeparator());
				
				Tratamiento ultimoTratamiento = Iterables.getLast(consulta.getTratamientos(), null);
				Cirugia ultimaCirugia = Iterables.getLast(consulta.getCirugias(), null);
				
				if(ultimoTratamiento != null  && ultimaCirugia != null) {
					if(ultimoTratamiento.getFechaInicioTratamiento().after(ultimaCirugia.getFechaCirugia())) {
						builder.append(ultimoTratamiento.getFullName() + System.lineSeparator());
					}
					else {
						builder.append(ultimaCirugia.getFullName() + System.lineSeparator());
					}
				}
				else if(ultimoTratamiento != null ) {
					builder.append(ultimoTratamiento.getFullName() + System.lineSeparator());
				}
				else {
					builder.append(ultimaCirugia.getFullName() + System.lineSeparator());
				}
			}
			
			builder.append(System.lineSeparator());
		}
		else{
			String fecha = consulta.getDatosIniciales().getFechaAccidente() != null ? dateFormat.format(consulta.getDatosIniciales().getFechaAccidente()) : "Sin Fecha";
			
			builder.append("Nombre: " + consulta.getPaciente().getNombre() + System.lineSeparator());
			builder.append("Dni: " + consulta.getPaciente().getDni() + System.lineSeparator());
			builder.append("Edad: " + consulta.getPaciente().getEdad() + System.lineSeparator());
			builder.append("Sexo: " + consulta.getPaciente().getSexo() + System.lineSeparator());
			builder.append("Fecha de accidente: " + fecha + System.lineSeparator());
			builder.append("Fecha de primer consulta: " + consulta.getFechaPrimerConsulta() + System.lineSeparator());
			builder.append("Interrogatorio:" + System.lineSeparator() + consulta.getDatosIniciales().getInterrogatorio() + System.lineSeparator());
			
			if(consulta.getDiagnostico() != null){
				builder.append("Diagnóstico:" + System.lineSeparator() + consulta.getDiagnostico().getResumen() + System.lineSeparator());
			}
			
			if(!consulta.getTratamientos().isEmpty()){
				builder.append("Último tratamiento:" + System.lineSeparator() + Iterables.getLast(consulta.getTratamientos()).getFullName() + System.lineSeparator());
			}
			
			builder.append(System.lineSeparator());
		}
		
		return builder;
	}
}
