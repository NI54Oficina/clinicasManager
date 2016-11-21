package ar.com.clinicasmanager.service;

import java.util.List;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.search.FechasSearch;
import ar.com.clinicasmanager.search.OperadorLogico;
import ar.com.clinicasmanager.search.PacienteSearch;

public interface BuscadorService {

	List<Paciente> searchPacientes(Paciente paciente);

	List<Consulta> searchConsultas(OperadorLogico operadorLesiones,
			List<Long> lesiones, OperadorLogico operadorTratamientos,
			List<Long> tratamientos, PacienteSearch paciente,
			FechasSearch fechas);
	
}
