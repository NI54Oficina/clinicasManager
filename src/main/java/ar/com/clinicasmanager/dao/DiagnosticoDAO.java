package ar.com.clinicasmanager.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Diagnostico;

@RooJpaRepository(domainType = Diagnostico.class)
public interface DiagnosticoDAO {
}
