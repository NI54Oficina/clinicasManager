package ar.com.clinicasmanager.service;

import java.util.List;

public interface ScoreService {

	Double completarQuickDash(Long id, List<Integer> respuestas);
	
	void completarEscalaDeDolor(Long id, Integer value);
	
	void completarArtrits(Long id, List<String> respuestas);

	void completarMovilidadDedos(Long id, List<String> respuestas);

	Double completarMichigan(Long id, List<String> respuestas);

}
