package ar.com.clinicasmanager.search;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FechasSearch {

	@DateTimeFormat(pattern = "dd/MM/yyy")
	private Date primerConsultaFrom;
	
	@DateTimeFormat(pattern = "dd/MM/yyy")
	private Date primerConsultaTo;

	public Date getPrimerConsultaFrom() {
		return primerConsultaFrom;
	}

	public void setPrimerConsultaFrom(Date primerConsultaFrom) {
		this.primerConsultaFrom = primerConsultaFrom;
	}

	public Date getPrimerConsultaTo() {
		return primerConsultaTo;
	}

	public void setPrimerConsultaTo(Date primerConsultaTo) {
		this.primerConsultaTo = primerConsultaTo;
	}

	public boolean isEmpty() {
		return primerConsultaFrom == null && primerConsultaTo == null;
	}
}
