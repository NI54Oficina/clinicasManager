package ar.com.clinicasmanager.entity;

import java.util.Date;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Plan {

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="-M")
	private Date date = new Date();
	
	@Lob
	@NotEmpty
	private String texto;
}
