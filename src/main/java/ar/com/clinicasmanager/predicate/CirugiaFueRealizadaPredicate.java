package ar.com.clinicasmanager.predicate;

import javax.annotation.Nullable;

import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.enums.EstadoCirugia;

import com.google.common.base.Predicate;

public class CirugiaFueRealizadaPredicate implements Predicate<Cirugia>{

	@Override
	public boolean apply(@Nullable Cirugia cirugia) {		
		return cirugia.getEstado().equals(EstadoCirugia.REALIZADA);
	}

}
