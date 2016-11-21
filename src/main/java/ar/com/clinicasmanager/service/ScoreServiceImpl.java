package ar.com.clinicasmanager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Score;
import ar.com.clinicasmanager.entity.enums.ScoreType;
import ar.com.clinicasmanager.ficha.FichaArtritis;
import ar.com.clinicasmanager.ficha.FichaMovilidadDedos;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

	private static final Integer QUICK_DASH_NRO_PREGUNTAS = 11;
	
	@Autowired
	private ConsultaService consultaService;
	
	@Override
	public Double completarQuickDash(Long id, List<Integer> respuestas) {
		Consulta consulta = consultaService.findOne(id);
		Score quickDash = new Score(ScoreType.QUICK_DASH, calcularQuickDash(respuestas));
		
		List<String> stringRespuestas = new ArrayList<String>();
		for (Integer respuesta : respuestas) {
			stringRespuestas.add(respuesta.toString());
		}
		quickDash.setRespuestas(stringRespuestas);
		
		consulta.putScore(quickDash);
		consultaService.save(consulta);
		
		return quickDash.getScore();
	}
	
	@Override
	public Double completarMichigan(Long id, List<String> respuestas) {
		Consulta consulta = consultaService.findOne(id);
		Double suma = Double.valueOf(0);
		
		for (String respuesta : respuestas) {
			suma += Double.valueOf(respuesta);
		}
		
		Score michigan = new Score(ScoreType.MICHIGAN, suma);
		michigan.setRespuestas(respuestas);
		
		consulta.putScore(michigan);
		consultaService.save(consulta);
		
		return michigan.getScore();
	}

	@Override
	public void completarEscalaDeDolor(Long id, Integer value) {
		Double doubleValue = Double.valueOf(value);
		Consulta consulta = consultaService.findOne(id);

		Score quickDash = new Score(ScoreType.ESCALA_DE_DOLOR, doubleValue);
		
		consulta.putScore(quickDash);
		consultaService.save(consulta);
	}
	
	@Override
	public void completarArtrits(Long id, List<String> respuestas) {
		Consulta consulta = consultaService.findOne(id);
		FichaArtritis fichaArtritis = consulta.getFichaArtritis() == null ? fichaArtritis = new FichaArtritis() : consulta.getFichaArtritis();
		
		fichaArtritis.setFecha(new Date());
		fichaArtritis.setRespuestas(respuestas);
		
		consulta.setFichaArtritis(fichaArtritis);
		consultaService.save(consulta);
	}
	
	@Override
	public void completarMovilidadDedos(Long id, List<String> respuestas) {
		Consulta consulta = consultaService.findOne(id);
		FichaMovilidadDedos fichaMovilidadDedos = new FichaMovilidadDedos();
		
		fichaMovilidadDedos.setFecha(new Date());
		fichaMovilidadDedos.setRespuestas(respuestas);
		
		consulta.getFichasMovilidadDedos().add(fichaMovilidadDedos);
		consultaService.save(consulta);
		
	}	
	
	private Double calcularQuickDash(List<Integer> respuestas) {
		Double totalizador = 0d;
		for (Integer respuesta : respuestas) {
			totalizador += respuesta;
		}
		
		Double score = ((totalizador / QUICK_DASH_NRO_PREGUNTAS) - 1.0) * 25;
		
		return  Math.round( score * 100.0) / 100.0;
	}
}
