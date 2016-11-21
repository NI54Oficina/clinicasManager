package ar.com.clinicasmanager.controller.form;

import ar.com.clinicasmanager.search.FechasSearch;
import ar.com.clinicasmanager.search.OperadorLogico;
import ar.com.clinicasmanager.search.PacienteSearch;

public class BuscarConsultaForm {
	
	private OperadorLogico operadorLesiones;
	
	private OperadorLogico operadorTratamientos;

	private String idDiagnostico;
	
	private String idTratamiento;
	
	private PacienteSearch paciente;

	private FechasSearch fechas;

	public String getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(String idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public String getIdDiagnostico() {
		return idDiagnostico;
	}

	public void setIdDiagnostico(String idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}

	public OperadorLogico getOperadorTratamientos() {
		return operadorTratamientos;
	}

	public void setOperadorTratamientos(OperadorLogico operadorTratamientos) {
		this.operadorTratamientos = operadorTratamientos;
	}

	public OperadorLogico getOperadorLesiones() {
		return operadorLesiones;
	}

	public void setOperadorLesiones(OperadorLogico operadorLesiones) {
		this.operadorLesiones = operadorLesiones;
	}

	public PacienteSearch getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteSearch paciente) {
		this.paciente = paciente;
	}

	public FechasSearch getFechas() {
		return fechas;
	}
	
	public void setFechas(FechasSearch fechas) {
		this.fechas = fechas;
	}

	public boolean pacienteIsEmpty() {
		return getPaciente() == null || getPaciente().isEmpty();
	}
	
	public boolean fechasIsEmpty() {
		return getFechas() == null || getFechas().isEmpty();
	}
}
