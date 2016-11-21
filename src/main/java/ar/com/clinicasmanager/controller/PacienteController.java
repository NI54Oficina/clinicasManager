package ar.com.clinicasmanager.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.clinicasmanager.dao.UsuarioDAO;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Ocupacion;
import ar.com.clinicasmanager.entity.enums.Sexo;
import ar.com.clinicasmanager.service.ConsultaService;

@RequestMapping("/pacientes")
@Controller
@RooWebScaffold(path = "pacientes", formBackingObject = Paciente.class)
public class PacienteController {
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping(value = "nuevoPaciente", produces = "text/html")
	public String nuevoPaciente(@RequestParam String dni, Model uiModel) {
		Paciente paciente = pacienteService.findByDni(dni); 
		
		if(paciente != null){
			return "redirect:/consulta/verConsultas?dni=" + dni;
		} 
		else {
			Paciente newPaciente = new Paciente(dni);
			populateEditForm(uiModel, newPaciente);
			return "pacientes/create";
		}
	}

	@RequestMapping(value = "/{dni}", produces = "text/html")
	public String show(@PathVariable String dni, Model uiModel) {
		Paciente paciente = pacienteService.findByDni(dni);
		if (paciente == null) {
			return "redirect:/pacientes?form&dni=" + dni;
		}
		uiModel.addAttribute("paciente", paciente);
		uiModel.addAttribute("consultas", consultaService.findByPaciente(paciente));
		return "pacientes/show";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Paciente paciente, Model uiModel) {
		String url;
		
//		Usuario usuario = usuarioDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if (paciente.getId() == null) {
			pacienteService.savePaciente(mergePropiedadesPaciente(paciente, new Paciente()));
			
			url = "redirect:/consulta/crearConsulta?dni=" + paciente.getDni();
		} else {
			pacienteService.savePaciente(
					mergePropiedadesPaciente(paciente, pacienteService.findPaciente(paciente.getId())));
			
			url = "redirect:/pacientes/" + paciente.getDni();
		}		
		
		return url;
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, Model uiModel) {
        Paciente paciente = pacienteService.findPaciente(id);
        consultaService.deleteConsultas(consultaService.findByPaciente(paciente));
        pacienteService.deletePaciente(paciente);
        return "redirect:/pacientes";
    }	

	private Paciente mergePropiedadesPaciente(Paciente fromPaciente, Paciente targetPaciente) {
		targetPaciente.setDni(fromPaciente.getDni());
		targetPaciente.setCobertura(fromPaciente.getCobertura());
		targetPaciente.setEdad(fromPaciente.getEdad());
		targetPaciente.setNombre(fromPaciente.getNombre());
		targetPaciente.setOcupacion(fromPaciente.getOcupacion());
		targetPaciente.setSexo(fromPaciente.getSexo());
		targetPaciente.setTelefono(fromPaciente.getTelefono());
		targetPaciente.setObraSocial(fromPaciente.getObraSocial());
		targetPaciente.setNumeroSocio(fromPaciente.getNumeroSocio());
		return targetPaciente;
	}

	private void populateEditForm(Model uiModel, Paciente paciente) {
		uiModel.addAttribute("paciente", paciente);
		uiModel.addAttribute("coberturas", Arrays.asList(Cobertura.values()));
		uiModel.addAttribute("ocupaciones", Arrays.asList(Ocupacion.values()));
		uiModel.addAttribute("sexos", Arrays.asList(Sexo.values()));
	}
}
