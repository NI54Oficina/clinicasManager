package ar.com.clinicasmanager.predicate;

import javax.annotation.Nullable;

import ar.com.clinicasmanager.entity.ConfiguracionFicha;

import com.google.common.base.Predicate;

public class SemanasConfiguracionSuperadoPredicate implements Predicate<ConfiguracionFicha> {

	private double semanasTranscurridas;
	
	public SemanasConfiguracionSuperadoPredicate(double semanasTranscurridas){
		this.semanasTranscurridas = semanasTranscurridas;
	}
	
	@Override
	public boolean apply(@Nullable ConfiguracionFicha input) {
		return input.getPeriodoSemanas() <= semanasTranscurridas;
	}

}
