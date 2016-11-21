package ar.com.clinicasmanager.controller.form;

import java.util.ArrayList;
import java.util.List;

public class QuickDashForm {

	private List<String> respuestas = new ArrayList<String>();

	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}

	public List<Integer> getRespuestasInteger() {
		List<Integer> integerRespuestas = new ArrayList<Integer>();
		for (String respuesta : respuestas) {
			integerRespuestas.add(Integer.valueOf(respuesta));
		}
		return integerRespuestas;
	}
}
