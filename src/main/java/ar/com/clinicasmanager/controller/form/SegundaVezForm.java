package ar.com.clinicasmanager.controller.form;

public class SegundaVezForm {

	private Long id;

	private String evolucion;

	public SegundaVezForm() {
	}
	
	public SegundaVezForm(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}

}
