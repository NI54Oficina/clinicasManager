package ar.com.clinicasmanager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import ar.com.clinicasmanager.entity.enums.PermisosUsuario;

@RooJavaBean
@RooJpaEntity(versionField = "")
@RooToString
public class Grupo {

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<PermisosUsuario> permisosUsuarios = new HashSet<PermisosUsuario>();

	@NotBlank
	@Column(unique = true)
	private String nombre;

	@NotNull
	private boolean enabled = true;

}
