package ar.com.clinicasmanager.entity;

import javax.persistence.Embeddable;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@Embeddable
public class EstadoConsulta {

	private Boolean sinDiagnostico = true;
	
	private Boolean conCirugiaPendiente = false;
	
	private Boolean conInternacion = false;
	
	private Boolean incompleta = true;
	
	private Boolean dadoDeAlta = false;
	
	private Boolean segundaVez = false;

	public Boolean getSegundaVez() {
		return segundaVez;
	}

	public void setSegundaVez(Boolean segundaVez) {
		this.segundaVez = segundaVez;
	}
}
