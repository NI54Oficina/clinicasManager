package ar.com.clinicasmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ar.com.clinicasmanager.dao.NodoTratamientoDAO;
import ar.com.clinicasmanager.entity.NodoTratamiento;
import ar.com.clinicasmanager.script.PopulateNodoTratamientoScript;

@Service
@Transactional
public class NodoTratamientoServiceImpl implements NodoTratamientoService, ApplicationListener<ContextRefreshedEvent> {

	private NodoTratamiento root;
	
	@Autowired
	private NodoTratamientoDAO nodoTratamientoDAO;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(nodoTratamientoDAO.count() == 0){
			root = PopulateNodoTratamientoScript.doScript();
			
			nodoTratamientoDAO.save(root);
		}
		else if(root == null){
			root = nodoTratamientoDAO.findOne(1L);
			getChildrenLeavesIds(root, Lists.<Long>newArrayList());
		}
	}
	
	@Override
	public NodoTratamiento getTreeRoot() {
		return root;
	}
	
	@Override
	public NodoTratamiento getNodo(Long id) {
		if(id == null){
			return null;
		}
		
		return getNodoRecursively(getTreeRoot(), id);
	}
	
	private NodoTratamiento getNodoRecursively(NodoTratamiento nodo, Long id) {
		NodoTratamiento nodoToGet = null;
		
		for (NodoTratamiento child : nodo.getChildren()) {
			nodoToGet = child.getId().equals(id) ? child : getNodoRecursively(child, id);
			
			if(nodoToGet != null) {
				return nodoToGet;
			}
		}
		
		return null;
	}
	
	@Override
	public List<Long> getChildrenLeavesIds(Long nodoId) {
		return getChildrenLeavesIds(getNodo(nodoId), new ArrayList<Long>());
	}
	
	private List<Long> getChildrenLeavesIds(NodoTratamiento nodo, List<Long> leaves) {
		if(nodo.getChildren().isEmpty()) {
			leaves.add(nodo.getId());
		}
		
		for (NodoTratamiento child : nodo.getChildren()) {
			getChildrenLeavesIds(child, leaves);
		}
		
		return leaves;
	}	
}
