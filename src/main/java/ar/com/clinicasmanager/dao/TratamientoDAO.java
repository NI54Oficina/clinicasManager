package ar.com.clinicasmanager.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Tratamiento;

@RooJpaRepository(domainType = Tratamiento.class)
public interface TratamientoDAO {

}
