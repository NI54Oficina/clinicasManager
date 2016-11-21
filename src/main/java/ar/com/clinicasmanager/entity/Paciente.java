package ar.com.clinicasmanager.entity;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Ocupacion;
import ar.com.clinicasmanager.entity.enums.Sexo;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Paciente {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "DNI", unique = true)
	private String dni;

	private String nombre;

	private Integer edad;
	
	private String telefono;

	@Enumerated
	private Sexo sexo;

	@Enumerated
	private Cobertura cobertura;
	
	private String obraSocial;
	
	private String numeroSocio;

	@Enumerated
	private Ocupacion ocupacion;
	
	public Paciente(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public String getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(String numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

}
