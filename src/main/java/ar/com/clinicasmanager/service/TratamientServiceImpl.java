package ar.com.clinicasmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ar.com.clinicasmanager.dao.TratamientoDAO;
import ar.com.clinicasmanager.entity.NodoTratamiento;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.Miembro;

import com.google.common.collect.Lists;

@Service
@Transactional
public class TratamientServiceImpl implements TratamientoService {

	@Autowired
	private TratamientoDAO tratamientoDAO;
	
	@Autowired
	private NodoTratamientoService nodoTratamientoService;
	
	@Override
	public void save(Tratamiento tratamiento) {
		tratamientoDAO.save(tratamiento);
	}

	@Override
	public void delete(Long id) {
		tratamientoDAO.delete(id);
	}
	
	@Override
	public Tratamiento createTratamiento(Long id, Miembro miembro) {
		NodoTratamiento nodo = nodoTratamientoService.getNodo(id);
		return new Tratamiento(nodo, miembro, getFullName(nodo));
	}
	
	@Override
	public Tratamiento createTratamiento(Long id) {
		String fullName;
		NodoTratamiento nodo = nodoTratamientoService.getNodo(id);
		
		fullName = getFullName(nodo);
		
		return new Tratamiento(nodo, fullName);
	}	
	
	private String getFullName(NodoTratamiento nodo) {
		List<String> names = new ArrayList<String>();
		while(nodo.getParent().getParent() != null){
			names.add(nodo.getLabel());
			nodo = nodo.getParent();
		}
		names.add(nodo.getLabel());
		
		return StringUtils.collectionToDelimitedString(Lists.reverse(names), ", ");
	}

}
