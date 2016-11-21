package ar.com.clinicasmanager.predicate;

import ar.com.clinicasmanager.entity.FichaPregunta;

import com.google.common.base.Predicate;

public class PreguntaTieneMismoIdPredicate implements Predicate<FichaPregunta> {

	private Long id;

	public PreguntaTieneMismoIdPredicate(Long id) {
		this.id = id;
	}

	@Override
	public boolean apply(FichaPregunta input) {
		return input.getId().equals(id);
	}
}
