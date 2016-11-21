package ar.com.clinicasmanager.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Evolucion;

@RooJpaRepository(domainType = Evolucion.class)
public interface EvolucionDAO {
	
//	@Query("SELECT e FROM Consulta c INNER JOIN c.evoluciones e WHERE c = ?1 ORDER BY e.fecha")
//	public Page<Evolucion> findEvolucionesFromConsulta(Consulta consulta, Pageable pageable);
}
