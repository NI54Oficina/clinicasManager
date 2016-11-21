package ar.com.clinicasmanager.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Internacion;

@RooJpaRepository(domainType = Internacion.class)
public interface InternacionDAO {

}
