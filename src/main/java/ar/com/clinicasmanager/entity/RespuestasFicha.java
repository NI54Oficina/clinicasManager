package ar.com.clinicasmanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity
public class RespuestasFicha {

	private Date date;
	
	@ManyToOne
	private Ficha ficha;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="RESPUESTA_FICHA_ID")
	@Valid
	private List<RespuestaFichaPregunta> respuestasPregunta;
	
	public RespuestasFicha(Ficha ficha) {
		this.ficha = ficha;
	}
}
