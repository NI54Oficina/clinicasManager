package ar.com.clinicasmanager.dao;
import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.EstadoConsulta;
import ar.com.clinicasmanager.entity.Paciente;

@RooJpaRepository(domainType = Consulta.class)
public interface ConsultaDAO {

	List<Consulta> findByPaciente(Paciente paciente);
	
	List<Consulta> findByPacienteAndEstado(Paciente paciente, EstadoConsulta estado);
}
