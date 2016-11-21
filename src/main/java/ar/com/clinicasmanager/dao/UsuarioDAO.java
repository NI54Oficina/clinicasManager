package ar.com.clinicasmanager.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.Usuario;

@RooJpaRepository(domainType = Usuario.class)
public interface UsuarioDAO {

	Usuario findByUsername(String username);
}
