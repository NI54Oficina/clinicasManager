package ar.com.clinicasmanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Ficha {
	
	@NotBlank
	private String nombre;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@OrderBy(value="id ASC")
	@Valid
	private List<FichaPregunta> preguntas;
}
