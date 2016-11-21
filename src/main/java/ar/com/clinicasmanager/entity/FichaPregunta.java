package ar.com.clinicasmanager.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJpaEntity(versionField = "")
@RooJavaBean
public class FichaPregunta {

	private String pregunta;
	
	@ElementCollection
	@CollectionTable(name = "FICHA_OPCIONES", joinColumns=@JoinColumn(name="FICHA_PREGUNTA_ID"))
	private List<String> opciones;
	
	private Boolean multiplesRespuestas;

	public Boolean getMultiplesRespuestas() {
		return multiplesRespuestas;
	}

	public void setMultiplesRespuestas(Boolean multiplesRespuestas) {
		this.multiplesRespuestas = multiplesRespuestas;
	}
}
