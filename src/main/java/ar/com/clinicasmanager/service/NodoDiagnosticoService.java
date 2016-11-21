package ar.com.clinicasmanager.service;

import java.util.List;

import ar.com.clinicasmanager.entity.NodoDiagnostico;

public interface NodoDiagnosticoService {
	
	NodoDiagnostico getTreeRoot();

	NodoDiagnostico getNodo(Long id);

	String getFullName(NodoDiagnostico nodoDiagnostico);

	List<Long> getChildrenLeavesIds(Long nodoId);
	
	List<NodoDiagnostico> getParentLeaves(Long nodoId);

	void reloadTreeRoot();

	NodoDiagnostico getNodoSinDiagnostico();

	Boolean isNodoSinDiagnostico(Long id);

	void save(NodoDiagnostico dbNodo);
}
