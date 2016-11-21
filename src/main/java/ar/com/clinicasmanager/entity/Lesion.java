package ar.com.clinicasmanager.entity;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import ar.com.clinicasmanager.entity.enums.Miembro;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Lesion {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Enumerated
	private Miembro miembro;
	
	@ManyToOne
	private NodoDiagnostico patologia;
	
	@Lob
	private String fullName;
	
	public Lesion() {
	}
	
	public Lesion(Miembro miembro, NodoDiagnostico nodo, String fullName) {
		this.miembro = miembro;
		this.patologia = nodo;
		this.fullName = fullName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((miembro == null) ? 0 : miembro.hashCode());
		result = prime * result
				+ ((patologia == null) ? 0 : patologia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesion other = (Lesion) obj;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (miembro != other.miembro)
			return false;
		if (patologia == null) {
			if (other.patologia != null)
				return false;
		} else if (!patologia.equals(other.patologia))
			return false;
		return true;
	}	

}
