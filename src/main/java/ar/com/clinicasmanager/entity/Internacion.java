package ar.com.clinicasmanager.entity;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import ar.com.clinicasmanager.entity.enums.EstadoInternacion;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Internacion {

	@Enumerated
	private EstadoInternacion estado = EstadoInternacion.PROGRAMADA;
	
	@Lob
	private String motivo;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyy")
	private Date fechaEntrada;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyy")
	private Date fechaSalida;
}
