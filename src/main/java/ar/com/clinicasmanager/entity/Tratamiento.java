package ar.com.clinicasmanager.entity;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import ar.com.clinicasmanager.entity.enums.Miembro;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Tratamiento{

	@ManyToOne
	private NodoTratamiento tratamiento;
	
	@Enumerated
	private Miembro miembro;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="-M")
	private Date fechaInicioTratamiento = new Date();
	
	private String fullName;
	
	public Tratamiento(){
	}

	public Tratamiento(NodoTratamiento nodo, String fullName) {
		this.tratamiento = nodo;
		this.fullName = fullName;
	}

	public Tratamiento(NodoTratamiento nodo, Miembro miembro, String fullName) {
		this.tratamiento = nodo;
		this.fullName = fullName;
		this.miembro = miembro;
	}
	
}
