package ar.com.clinicasmanager.function;

import javax.annotation.Nullable;

import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Tratamiento;

import com.google.common.base.Function;

public class CirugiaToTratamientoFunction implements Function<Cirugia, Tratamiento> {

	@Override
	@Nullable
	public Tratamiento apply(@Nullable Cirugia cirugia) {
		String fullName = cirugia.getDescripcion().isEmpty() ? cirugia.getFullName() : cirugia.getFullName() + " (Descripción: " + cirugia.getDescripcion() + ")";
		
		Tratamiento tratamiento = new Tratamiento();
		tratamiento.setFechaInicioTratamiento(cirugia.getFechaCirugia());
		tratamiento.setFullName(fullName);
		tratamiento.setTratamiento(cirugia.getTratamiento());
		return tratamiento;
	}

}
