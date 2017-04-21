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

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

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
			builder.append("Diagnósticos:" + LINE_SEPARATOR);
			builder.append(dateFormat.format(consulta.getDiagnostico().getFechaDiagnostico()) + " - " + consulta.getDiagnostico().getResumen());
			for (Diagnostico diagnostico : consulta.getDiagnosticosAnteriores()) {
				builder.append(dateFormat.format(diagnostico.getFechaDiagnostico()) + " - " + diagnostico.getResumen() + LINE_SEPARATOR);
			}
			builder.append(LINE_SEPARATOR);
		}
		
		if(!consulta.getTratamientos().isEmpty() || !consulta.getCirugias().isEmpty()){
			List<Tratamiento> tratamientos = consultaService.getTratamientoAndCirugiasOrderedByDate(consulta);
			builder.append("Tratamientos:" + LINE_SEPARATOR);
			for (Tratamiento tratamiento : tratamientos) {
				builder.append(dateFormat.format(tratamiento.getFechaInicioTratamiento()) + " - " + tratamiento.getFullName() + LINE_SEPARATOR);
			}
			builder.append(LINE_SEPARATOR);
		}
		
		if(!consulta.getEvoluciones().isEmpty()){
			builder.append("Evoluciones:" + LINE_SEPARATOR);
			for (Evolucion evolucion : consulta.getEvoluciones()) {
				builder.append(dateFormat.format(evolucion.getFecha()) + " - " + evolucion.getTexto() + LINE_SEPARATOR);
			}
			builder.append(LINE_SEPARATOR);
		}
		
		if(consulta.getAlta() != null){
			builder.append("Alta: " + dateFormat.format(consulta.getAlta().getFecha()) + LINE_SEPARATOR);
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
			builder.append("Nombre: " + consulta.getPaciente().getNombre() + LINE_SEPARATOR);
			builder.append("Dni: " + consulta.getPaciente().getDni() + LINE_SEPARATOR);
			
			if(!consulta.getEvoluciones().isEmpty()){
				builder.append("Última evolución:" + LINE_SEPARATOR + Iterables.getLast(consulta.getEvoluciones()).getTexto() + LINE_SEPARATOR);
			}
			
			if(!consulta.getTratamientos().isEmpty() || !consulta.getCirugias().isEmpty()){
				builder.append("Último tratamiento:" + LINE_SEPARATOR);
				
				Tratamiento ultimoTratamiento = Iterables.getLast(consulta.getTratamientos(), null);
				Cirugia ultimaCirugia = Iterables.getLast(consulta.getCirugias(), null);
				
				if(ultimoTratamiento != null  && ultimaCirugia != null) {
					if(ultimoTratamiento.getFechaInicioTratamiento().after(ultimaCirugia.getFechaCirugia())) {
						builder.append(ultimoTratamiento.getFullName() + LINE_SEPARATOR);
					}
					else {
						builder.append(ultimaCirugia.getFullName() + LINE_SEPARATOR);
					}
				}
				else if(ultimoTratamiento != null ) {
					builder.append(ultimoTratamiento.getFullName() + LINE_SEPARATOR);
				}
				else {
					builder.append(ultimaCirugia.getFullName() + LINE_SEPARATOR);
				}
			}
			
			builder.append(LINE_SEPARATOR);
		}
		else{
			String fecha = consulta.getDatosIniciales().getFechaAccidente() != null ? dateFormat.format(consulta.getDatosIniciales().getFechaAccidente()) : "Sin Fecha";
			
			builder.append("Nombre: " + consulta.getPaciente().getNombre() + LINE_SEPARATOR);
			builder.append("Dni: " + consulta.getPaciente().getDni() + LINE_SEPARATOR);
			builder.append("Edad: " + consulta.getPaciente().getEdad() + LINE_SEPARATOR);
			builder.append("Sexo: " + consulta.getPaciente().getSexo() + LINE_SEPARATOR);
			builder.append("Fecha de accidente: " + fecha + LINE_SEPARATOR);
			builder.append("Fecha de primer consulta: " + consulta.getFechaPrimerConsulta() + LINE_SEPARATOR);
			builder.append("Interrogatorio:" + LINE_SEPARATOR + consulta.getDatosIniciales().getInterrogatorio() + LINE_SEPARATOR);
			
			if(consulta.getDiagnostico() != null){
				builder.append("Diagnóstico:" + LINE_SEPARATOR + consulta.getDiagnostico().getResumen() + LINE_SEPARATOR);
			}
			
			if(!consulta.getTratamientos().isEmpty()){
				builder.append("Último tratamiento:" + LINE_SEPARATOR + Iterables.getLast(consulta.getTratamientos()).getFullName() + LINE_SEPARATOR);
			}
			
			builder.append(LINE_SEPARATOR);
		}
		
		return builder;
	}
}
