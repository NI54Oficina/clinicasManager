package ar.com.clinicasmanager.service;

import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.Miembro;

public interface TratamientoService {

	void save(Tratamiento tratamiento);

	void delete(Long id);

	Tratamiento createTratamiento(Long id, Miembro miembro);

	Tratamiento createTratamiento(Long id);
}
