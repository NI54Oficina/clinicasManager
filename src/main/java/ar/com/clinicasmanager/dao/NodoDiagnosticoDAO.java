package ar.com.clinicasmanager.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.NodoDiagnostico;

@RooJpaRepository(domainType = NodoDiagnostico.class)
public interface NodoDiagnosticoDAO {

	NodoDiagnostico findByLabel(String label);
}
