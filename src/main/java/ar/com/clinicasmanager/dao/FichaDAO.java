package ar.com.clinicasmanager.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Ficha;

@RooJpaRepository(domainType = Ficha.class)
public interface FichaDAO { 

}
