package ar.com.clinicasmanager.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.RespuestasFicha;

@RooJpaRepository(domainType = RespuestasFicha.class)
public interface RespuestasFichaDAO { 

}
