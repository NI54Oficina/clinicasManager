package ar.com.clinicasmanager.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.NodoTratamiento;

@RooJpaRepository(domainType = NodoTratamiento.class)
public interface NodoTratamientoDAO {
}
