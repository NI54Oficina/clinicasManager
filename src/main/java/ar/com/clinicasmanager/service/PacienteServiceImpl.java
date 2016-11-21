package ar.com.clinicasmanager.service;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.clinicasmanager.dao.PacienteDAO;
import ar.com.clinicasmanager.entity.Paciente;

public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteDAO pacienteDAO;

	@Override
	public Paciente findByDni(String dni) {
		return pacienteDAO.findByDni(dni);
	}
}
