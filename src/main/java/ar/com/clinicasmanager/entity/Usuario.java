package ar.com.clinicasmanager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaEntity(versionField = "")
@RooToString
public class Usuario {

	@NotBlank
	@Column(unique = true)
	private String username;
	
	private Boolean usuarioGoogle;

	@NotBlank
	private String nombre;

	@NotBlank
	private String email;

	@ManyToMany
	private Set<Grupo> grupos = new HashSet<Grupo>();

	private boolean enabled = true;

	@NotBlank
	private String password;
	
}
