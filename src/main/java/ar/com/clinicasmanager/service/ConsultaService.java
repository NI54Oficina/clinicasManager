package ar.com.clinicasmanager.service;

import java.util.List;
import java.util.Set;

import ar.com.clinicasmanager.entity.Alta;
import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Evolucion;
import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.Internacion;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.Tratamiento;

public interface ConsultaService {

	Consulta save(Consulta consulta);

	Consulta findOne(Long id);
	
	void deleteConsulta(Long id);
	
	void deleteConsultas(List<Consulta> consulta);

	List<Consulta> findByPaciente(Paciente paciente);

	void darAlta(Consulta consulta, Alta alta);

	Cirugia getCirugiaPendiente(Consulta consulta);

	List<Evolucion> getEvoluciones(Consulta consulta, int maxResult);

	Tratamiento getTratamiento(Consulta consulta, Long id2);

	Internacion getInternacion(Consulta consulta, Long id2);

	Set<Ficha> getFichasPendientes(Consulta consulta);

	List<Tratamiento> getTratamientoAndCirugiasOrderedByDate(Consulta consulta);

	Cirugia getUltimaCirugiaRealizada(Consulta consulta);

}
