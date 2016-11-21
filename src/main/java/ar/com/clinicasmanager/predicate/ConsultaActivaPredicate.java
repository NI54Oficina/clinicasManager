package ar.com.clinicasmanager.predicate;

import javax.annotation.Nullable;

import ar.com.clinicasmanager.entity.Consulta;

import com.google.common.base.Predicate;

public class ConsultaActivaPredicate implements Predicate<Consulta>{

	@Override
	public boolean apply(@Nullable Consulta consulta) {		
		return !consulta.getEstado().getDadoDeAlta();
	}

}
