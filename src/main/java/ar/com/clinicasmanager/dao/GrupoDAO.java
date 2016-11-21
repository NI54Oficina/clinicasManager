package ar.com.clinicasmanager.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Grupo;

@RooJpaRepository(domainType = Grupo.class)
public interface GrupoDAO {
	
}
