package ar.com.clinicasmanager.ficha;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

@Entity
public class FichaArtritis {
	
	@Id
	@GeneratedValue
	private Long id;

	private Date fecha;
	
	@ElementCollection
	@CollectionTable(name = "RESPUESTAS_FICHA_ARTRITIS", joinColumns=@JoinColumn(name="FICHA_MOVILIDAD_ARTRITIS"))
	@OrderColumn
	private List<String> respuestas;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
