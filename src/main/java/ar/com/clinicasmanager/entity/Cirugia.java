package ar.com.clinicasmanager.entity;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import ar.com.clinicasmanager.entity.enums.EstadoCirugia;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Cirugia{

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date fechaCirugia; 
	
	@Lob
	private String descripcion;

	private String lugar;
	
	private String googleEventId;
	
	private String googleCalendarId;
	
	private String fullName;
	
	@ManyToOne
	@NotNull
	private NodoTratamiento tratamiento;
	
	@Enumerated
	private EstadoCirugia estado = EstadoCirugia.PENDIENTE;

}
