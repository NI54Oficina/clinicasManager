package ar.com.clinicasmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ar.com.clinicasmanager.dao.NodoDiagnosticoDAO;
import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.script.PopulateNodoDiagnosticoScript;

import com.google.common.collect.Lists;

@Service
@Transactional
public class NodoDiagnosticoServiceImpl implements NodoDiagnosticoService, ApplicationListener<ContextRefreshedEvent> {

	public static final String SIN_DIAGNOSTICO_NAME = "Sin diagnostico";
	public static final String ROOT_LABEL = "patologia";
	
	private NodoDiagnostico root;
	private NodoDiagnostico sinDiagnosticoNodo;
	
	@Autowired
	private NodoDiagnosticoDAO nodoDiagnosticoDAO;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(nodoDiagnosticoDAO.count() == 0){
			root = PopulateNodoDiagnosticoScript.doScript();
			
			nodoDiagnosticoDAO.save(root);
		}
		else if(root == null) {
			root = nodoDiagnosticoDAO.findByLabel(ROOT_LABEL);
			getChildrenLeavesIds(root, Lists.<Long>newArrayList());
		}
	}
	
	@Override
	public NodoDiagnostico getTreeRoot() {
		return root;
	}
	
	@Override
	public void save(NodoDiagnostico nodo) {
		nodoDiagnosticoDAO.save(nodo);
	}	
	
	@Override
	public void reloadTreeRoot() {
		root = nodoDiagnosticoDAO.findByLabel(ROOT_LABEL);
	}
	
	@Override
	public NodoDiagnostico getNodo(Long id) {
		if(id == null){
			return null;
		}
		
		return getNodoRecursively(getTreeRoot(), id);
	}
	
	private NodoDiagnostico getNodoRecursively(NodoDiagnostico nodo, Long id) {
		NodoDiagnostico nodoToGet = null;
		
		for (NodoDiagnostico child : nodo.getChildren()) {
			nodoToGet = child.getId().equals(id) ? child : getNodoRecursively(child, id);
			
			if(nodoToGet != null) {
				return nodoToGet;
			}
		}
		
		return null;
	}
	
	private NodoDiagnostico getNodoRecursivelyByLabel(NodoDiagnostico nodo, String label) {
		NodoDiagnostico nodoToGet = null;
		
		for (NodoDiagnostico child : nodo.getChildren()) {
			nodoToGet = child.getLabel().equals(label) ? child : getNodoRecursivelyByLabel(child, label);
			
			if(nodoToGet != null) {
				return nodoToGet;
			}
		}
		
		return null;
	}
	
	@Override
	public NodoDiagnostico getNodoSinDiagnostico() {
		if(sinDiagnosticoNodo == null) {
			sinDiagnosticoNodo = getNodoRecursivelyByLabel(getTreeRoot(), SIN_DIAGNOSTICO_NAME); 
		}
		 
		return sinDiagnosticoNodo; 
	}
	
	@Override
	public Boolean isNodoSinDiagnostico(Long id) {
		return getNodoSinDiagnostico().getId().equals(id);
	}
	
	@Override
	public String getFullName(NodoDiagnostico nodoDiagnostico) {
		List<String> names = new ArrayList<String>();
		while(nodoDiagnostico.getParent().getParent() != null){
			names.add(nodoDiagnostico.getLabel());
			nodoDiagnostico = nodoDiagnostico.getParent();
		}
		names.add(nodoDiagnostico.getLabel());
		
		return StringUtils.collectionToDelimitedString(Lists.reverse(names), ", ");
	}
	
	@Override
	public List<NodoDiagnostico> getParentLeaves(Long nodoId) {
		List<NodoDiagnostico> nodos = new ArrayList<NodoDiagnostico>();
		
		NodoDiagnostico nodo = getNodo(nodoId);
		nodos.add(nodo);
		
		while(nodo.getParent().getParent() != null){
			nodos.add(nodo);
			nodo = nodo.getParent();
		}
		
		nodos.add(nodo);
		
		return nodos;
	}

	@Override
	@Transactional
	public List<Long> getChildrenLeavesIds(Long nodoId) {
		return getChildrenLeavesIds(getNodo(nodoId), new ArrayList<Long>());
	}
	
	private List<Long> getChildrenLeavesIds(NodoDiagnostico nodo, List<Long> leaves) {
		if(nodo.getChildren().isEmpty()) {
			leaves.add(nodo.getId());
		}
		
		for (NodoDiagnostico child : nodo.getChildren()) {
			getChildrenLeavesIds(child, leaves);
		}
		
		return leaves;
	}

}
