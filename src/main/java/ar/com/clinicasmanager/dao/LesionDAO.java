package ar.com.clinicasmanager.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Lesion;

@RooJpaRepository(domainType = Lesion.class)
public interface LesionDAO {
}
