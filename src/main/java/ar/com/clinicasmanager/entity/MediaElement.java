package ar.com.clinicasmanager.entity;

import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RooJavaBean
@RooJpaEntity(versionField = "")
@JsonIgnoreProperties(value = "media")
public class MediaElement {

	@Lob
	@NotEmpty
	private byte[] media;
	
	@Lob
	private String descripcion;
	
}
