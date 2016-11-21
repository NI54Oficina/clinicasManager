package ar.com.clinicasmanager.order;

import java.util.Comparator;

import javax.annotation.Nullable;

import ar.com.clinicasmanager.entity.Tratamiento;

public class ByDateDesc implements Comparator<Tratamiento> {
		
		@Override
		public int compare(@Nullable Tratamiento left, @Nullable Tratamiento right) {
			return left.getFechaInicioTratamiento().compareTo(right.getFechaInicioTratamiento());
		}
}
