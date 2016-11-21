package ar.com.clinicasmanager.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ar.com.clinicasmanager.dao.DiagnosticoDAO;
import ar.com.clinicasmanager.dao.LesionDAO;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Diagnostico;
import ar.com.clinicasmanager.entity.Lesion;
import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.entity.enums.Miembro;

import com.google.common.collect.Sets;

@Service
@Transactional
public class DiagnosticoServiceImpl implements DiagnosticoService {

	@Autowired
	private DiagnosticoDAO diagnosticoDAO;
	
	@Autowired
	private LesionDAO lesionDAO;
	
	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;
	
	@Override
	public void save(Diagnostico diagnostico) {
		diagnosticoDAO.save(diagnostico);
	}

	@Override
	public void updateResumen(Diagnostico diagnostico) {
		diagnostico.setResumen(buildResumenDiagnostico(diagnostico));
	}
	
	@Override
	public void createDiagnostico(Consulta consulta, Lesion lesion) {
		lesion = createLesion(lesion.getMiembro(), lesion.getPatologia().getId());
		Diagnostico nuevoDiagnostico = new Diagnostico();
		nuevoDiagnostico.setLesiones(Sets.newHashSet(lesion));
		updateResumen(nuevoDiagnostico);
		
		if(consulta.getDiagnostico() != null) {
			consulta.addDiagnosticoAnterior(consulta.getDiagnostico());
		}
		consulta.setDiagnostico(nuevoDiagnostico);
		consulta.getEstado().setSinDiagnostico(false);
	}
	
	@Override
	public void createSinDiagnostico(Consulta consulta) {
		NodoDiagnostico sinDiagnosticoNodo = nodoDiagnosticoService.getNodoSinDiagnostico();
		Lesion sinDiagnosticoLesion = new Lesion(Miembro.AMBOS, sinDiagnosticoNodo, sinDiagnosticoNodo.getLabel());
		Diagnostico sinDiagnostico = new Diagnostico();
		sinDiagnostico.setLesiones(Sets.newHashSet(sinDiagnosticoLesion));
		sinDiagnostico.setResumen(sinDiagnosticoLesion.getFullName());
		
		if(consulta.getDiagnostico() != null) {
			consulta.addDiagnosticoAnterior(consulta.getDiagnostico());
		}
		consulta.setDiagnostico(sinDiagnostico);
		consulta.getEstado().setSinDiagnostico(true);
	}
	
	@Override
	public void delete(Long idDiagnostico) {
		diagnosticoDAO.delete(idDiagnostico);
	}

	@Override
	public void addLesion(Consulta consulta, Lesion lesion) {
		consulta.getDiagnostico().addLesion(createLesion(lesion.getMiembro(), lesion.getPatologia().getId()));
		updateResumen(consulta.getDiagnostico());
	}
	
	private Lesion createLesion(Miembro miembro, Long id) {
		NodoDiagnostico nodoDiagnostico = nodoDiagnosticoService.getNodo(id);
		String fullName = nodoDiagnosticoService.getFullName(nodoDiagnostico);
		
		return new Lesion(miembro, nodoDiagnostico, fullName);
	}
	
	private String buildResumenDiagnostico(Diagnostico diagnostico) {
		
		Set<String> msdLesionesString = new HashSet<String>();
		Set<String> msiLesionesString = new HashSet<String>();
		Set<String> bilateralLesionesString = new HashSet<String>();
		
		for (Lesion lesion : diagnostico.getLesiones()) {
			if(lesion.getMiembro() != null) {
				if(lesion.getMiembro().equals(Miembro.MSD)) {
					msdLesionesString.add(buildResumenLesion(lesion.getPatologia()));
				}
				else if(lesion.getMiembro().equals(Miembro.MSI)) {
					msiLesionesString.add(buildResumenLesion(lesion.getPatologia()));
				} 
				else {
					bilateralLesionesString.add(buildResumenLesion(lesion.getPatologia()));
				}
			}
		}
		
		String resumen = "";
		if(!msdLesionesString.isEmpty()) {
			resumen = "MSD: " + StringUtils.collectionToDelimitedString(msdLesionesString, ", ") + "<br>";
		}
		if(!msiLesionesString.isEmpty()) {
			resumen = resumen + "MSI: " + StringUtils.collectionToDelimitedString(msiLesionesString, ", ") + "<br>"; 
		}
		if(!bilateralLesionesString.isEmpty()) {
			resumen = resumen + "Bilateral: " + StringUtils.collectionToDelimitedString(bilateralLesionesString, ", ");
		}
		
		return resumen;
	}	

	private String buildResumenLesion(NodoDiagnostico nodoDiagnostico) {		
		String summaryName = "";
		
		while(nodoDiagnostico.getParent().getParent() != null){
			if(nodoDiagnostico.getDisplayInSumamry()){
				summaryName = nodoDiagnostico.getLabel() + " " + summaryName;
			}
			nodoDiagnostico = nodoDiagnostico.getParent();
		}
		
		if(nodoDiagnostico.getDisplayInSumamry()){
			summaryName = nodoDiagnostico.getLabel() + " " + summaryName;
		}
		
		return summaryName;
	}

	@Override
	public void deleteLesion(Consulta consulta, Long idLesion) {
		Lesion lesionToDelete = null;
		
		for (Lesion lesion : consulta.getDiagnostico().getLesiones()) {
			if(lesion.getId().equals(idLesion)) {
				lesionToDelete = lesion;
			}
		}
		
		if(lesionToDelete != null) {
			consulta.getDiagnostico().removeLesion(lesionToDelete);
			updateResumen(consulta.getDiagnostico());
		}
	}	

}

