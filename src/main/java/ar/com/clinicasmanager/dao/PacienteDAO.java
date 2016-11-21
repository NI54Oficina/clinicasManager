package ar.com.clinicasmanager.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Paciente;

@RooJpaRepository(domainType = Paciente.class)
public interface PacienteDAO {

	Paciente findByDni(String dni);

}
