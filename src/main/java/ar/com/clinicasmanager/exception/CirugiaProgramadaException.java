package ar.com.clinicasmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="La consulta ya posee una cirugía programada")
public class CirugiaProgramadaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
