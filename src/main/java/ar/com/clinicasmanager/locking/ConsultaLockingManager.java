package ar.com.clinicasmanager.locking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ar.com.clinicasmanager.entity.UltimoAcceso;
import ar.com.clinicasmanager.exception.LockNotGrantedException;

@Component
public class ConsultaLockingManager {
	
	private static final long TWENTY_MINUTE_IN_MILLIS = 20 * 60 * 1000;
	
	private Map<Long, UltimoAcceso> consultaToUltimoAcceso = new HashMap<Long, UltimoAcceso>();

	public synchronized void getLockOnConsulta(Long id) {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(consultaToUltimoAcceso.containsKey(id)){
			UltimoAcceso ultimoAcceso = consultaToUltimoAcceso.get(id);
			if(ultimoAcceso.getUsername().equals(currentUsername)){
				ultimoAcceso.refresh();
			}
			else{
				Date releaseDate = new Date(ultimoAcceso.getDate().getTime() + (TWENTY_MINUTE_IN_MILLIS));				
				if(releaseDate.after(new Date())){
					throw new LockNotGrantedException("siendo editada por " + ultimoAcceso.getUsername());
				}
				else{
					consultaToUltimoAcceso.put(id, new UltimoAcceso(currentUsername));
				}
			}
		}
		else{
			consultaToUltimoAcceso.put(id, new UltimoAcceso(currentUsername));
		}
	}

	public synchronized void freeLockOnConsulta(Long idConsulta) {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		if(consultaToUltimoAcceso.containsKey(idConsulta)){
			if(consultaToUltimoAcceso.get(idConsulta).getUsername().equals(currentUsername)){
				consultaToUltimoAcceso.remove(idConsulta);
			}
		}
	}
}
