package ar.com.clinicasmanager.service;
import org.springframework.roo.addon.layers.service.RooService;

import ar.com.clinicasmanager.entity.Paciente;

@RooService(domainTypes = { ar.com.clinicasmanager.entity.Paciente.class })
public interface PacienteService {
	
	Paciente findByDni(String dni);

}
