package ar.com.clinicasmanager.entity;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

import ar.com.clinicasmanager.entity.enums.LugarAccidente;
import ar.com.clinicasmanager.entity.enums.Mecanismo;
import ar.com.clinicasmanager.entity.enums.Miembro;

@RooJavaBean
@Embeddable
public class DatosInicialesConsulta {

	@Lob
	@NotBlank
	private String interrogatorio;
	
	@Enumerated
	@NotNull
	private Miembro miembro;
	
	private Boolean traumatico; 
	
	@Enumerated
	private Mecanismo mecanismo;
	
	@Enumerated
	private LugarAccidente lugarAccidente;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyy")
	private Date fechaAccidente;
	
	@Lob
	private String internacionesPrevias;
}
