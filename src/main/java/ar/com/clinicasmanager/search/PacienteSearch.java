package ar.com.clinicasmanager.search;

import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Sexo;

public class PacienteSearch {

	private Comparacion comparacionEdad;
	
	private Integer edad;
	
	private Integer edadMax;
	
	private Sexo sexo;

	private Cobertura cobertura;
	
	private String obraSocial;

	public Comparacion getComparacionEdad() {
		return comparacionEdad;
	}

	public void setComparacionEdad(Comparacion comparacionEdad) {
		this.comparacionEdad = comparacionEdad;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getEdadMax() {
		return edadMax;
	}

	public void setEdadMax(Integer edadMax) {
		this.edadMax = edadMax;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Cobertura getCobertura() {
		return cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public Boolean isEmpty() {
		return edad != null && sexo != null && obraSocial != null && obraSocial.isEmpty() && cobertura != null;
	}
}
