package ar.com.clinicasmanager.service;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Diagnostico;
import ar.com.clinicasmanager.entity.Lesion;

public interface DiagnosticoService {

	void save(Diagnostico diagnostico);
	
	void updateResumen(Diagnostico diagnostico);

	void createDiagnostico(Consulta consulta, Lesion lesion);

	void createSinDiagnostico(Consulta consulta);

	void delete(Long idDiagnostico);

	void addLesion(Consulta consulta, Lesion lesion);

	void deleteLesion(Consulta consulta, Long idLesion);
}
