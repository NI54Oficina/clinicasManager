package ar.com.clinicasmanager.entity;

import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity
public class RespuestaFichaPregunta {

	@ManyToOne
	private FichaPregunta fichaPregunta;
	
	@NotBlank
	private String respuesta;
}
