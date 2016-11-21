package ar.com.clinicasmanager.entity;

import java.util.Date;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Evolucion {

	@Lob
	private String texto;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="-M")
	private Date fecha;
	
	public Evolucion(String evolucion) {
		this.texto = evolucion;
		this.fecha = new Date();
	}
}
