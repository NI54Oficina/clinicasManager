package ar.com.clinicasmanager.dao;

import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.ConfiguracionFicha;
import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.NodoDiagnostico;

@RooJpaRepository(domainType = ConfiguracionFicha.class)
public interface ConfiguracionFichaDAO {

	List<ConfiguracionFicha> findByFicha(Ficha ficha);

	List<ConfiguracionFicha> findByDiagnosticoIn(List<NodoDiagnostico> set);

}
