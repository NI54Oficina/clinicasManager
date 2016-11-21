package ar.com.clinicasmanager.predicate;

import javax.annotation.Nullable;

import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.RespuestasFicha;

import com.google.common.base.Predicate;

public class FichaFueRespondidaPredicate implements Predicate<RespuestasFicha> {

	private Ficha ficha;
	
	public FichaFueRespondidaPredicate(Ficha ficha) {
		this.ficha = ficha;
	}

	@Override
	public boolean apply(@Nullable RespuestasFicha input) {
		return input.getFicha().getId().equals(ficha.getId());
	}
}
