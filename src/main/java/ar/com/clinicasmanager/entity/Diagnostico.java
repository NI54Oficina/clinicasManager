package ar.com.clinicasmanager.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import com.google.gson.annotations.Expose;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Diagnostico {

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	private Set<Lesion> lesiones;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="-M")
	private Date fechaDiagnostico = new Date();
	
	@Expose
	@Lob
	private String resumen; 
	
	public void addLesion(Lesion lesion) {
		lesiones.add(lesion);
	}

	public void removeLesion(Lesion lesion) {
		lesiones.remove(lesion);
	}
}
