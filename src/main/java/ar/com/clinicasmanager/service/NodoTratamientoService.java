package ar.com.clinicasmanager.service;

import java.util.List;

import ar.com.clinicasmanager.entity.NodoTratamiento;

public interface NodoTratamientoService {

	NodoTratamiento getTreeRoot();

	NodoTratamiento getNodo(Long id);

	List<Long> getChildrenLeavesIds(Long nodoId);

}
