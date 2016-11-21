package ar.com.clinicasmanager.entity;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity
public class ConfiguracionFicha {

	@ManyToOne
	private Ficha ficha;
	
	@OneToOne
	private NodoDiagnostico diagnostico;
	
	private String fullName;
	
	@NotNull
	private Integer periodoSemanas;
	
	public ConfiguracionFicha(Ficha ficha) {
		this.ficha = ficha;
	}
}
