package ar.com.clinicasmanager.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Cirugia;

@RooJpaRepository(domainType = Cirugia.class)
public interface CirugiaDAO {
}
