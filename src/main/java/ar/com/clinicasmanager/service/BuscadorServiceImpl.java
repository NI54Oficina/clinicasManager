package ar.com.clinicasmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clinicasmanager.dao.ConsultaDAO;
import ar.com.clinicasmanager.dao.PacienteDAO;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.search.FechasSearch;
import ar.com.clinicasmanager.search.OperadorLogico;
import ar.com.clinicasmanager.search.PacienteSearch;
import ar.com.clinicasmanager.specification.ConsultaSpecifications;
import ar.com.clinicasmanager.specification.PacienteSpecifications;
import ar.com.clinicasmanager.specification.SpecificationUtils;

import com.google.common.base.Strings;

@Service
@Transactional
public class BuscadorServiceImpl implements BuscadorService {
	
	@Autowired
	private ConsultaDAO consultaDAO;
	
	@Autowired
	private PacienteDAO pacienteDAO;
	
	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;
	
	@Autowired
	private NodoTratamientoService nodoTratamientoService;

	@Override
	public List<Consulta> searchConsultas(OperadorLogico operadorLesiones, List<Long> lesiones, OperadorLogico operadorTratamientos, List<Long> tratamientos, PacienteSearch paciente, FechasSearch fechas) {
		Specifications<Consulta> specifications = null;
		
		if(paciente != null) {
			specifications = Specifications.where(ConsultaSpecifications.hasPaciente(paciente));
		}
		
		if(!lesiones.isEmpty()){
			List<Long> leavesIds = nodoDiagnosticoService.getChildrenLeavesIds(lesiones.get(0));
			specifications = SpecificationUtils.appendSpecification(OperadorLogico.AND, specifications, ConsultaSpecifications.hasLesion(leavesIds));
			
			for (int i = 1; i < lesiones.size() ; i++) {
				leavesIds = nodoDiagnosticoService.getChildrenLeavesIds(lesiones.get(i));
				specifications = SpecificationUtils.appendSpecification(operadorLesiones, specifications, ConsultaSpecifications.hasLesion(leavesIds));
			}
		}
		
		if(!tratamientos.isEmpty()){
			List<Long> leavesIds = nodoTratamientoService.getChildrenLeavesIds(tratamientos.get(0));
			specifications = SpecificationUtils.appendSpecification(OperadorLogico.AND, specifications, ConsultaSpecifications.hasTratamiento(leavesIds));
			
			for (int i = 1; i < tratamientos.size() ; i++) {
				leavesIds = nodoTratamientoService.getChildrenLeavesIds(tratamientos.get(i));
				specifications = SpecificationUtils.appendSpecification(operadorTratamientos, specifications, ConsultaSpecifications.hasTratamiento(leavesIds));
			}
		}
		
		if(fechas != null && (fechas.getPrimerConsultaFrom() != null || fechas.getPrimerConsultaTo() != null)) {
			specifications = SpecificationUtils.appendSpecification(OperadorLogico.AND, specifications, ConsultaSpecifications.primerConsultaBetweenDates(fechas.getPrimerConsultaFrom(), fechas.getPrimerConsultaTo()));
		}
		
		return consultaDAO.findAll(specifications);
	}
	
	@Override
	public List<Paciente> searchPacientes(final Paciente paciente) {
		
		Specifications<Paciente> specifications = null; 
		
		if(!Strings.isNullOrEmpty(paciente.getNombre())){
			specifications = Specifications.where(PacienteSpecifications.nombreIsLike(paciente.getNombre()));
		}
		
		if(paciente.getOcupacion() != null){
			specifications = SpecificationUtils.appendSpecification(specifications, PacienteSpecifications.ocupacionEquals(paciente.getOcupacion()));
		}
		
		if(paciente.getSexo() != null){
			specifications = SpecificationUtils.appendSpecification(specifications, PacienteSpecifications.sexoEquals(paciente.getSexo()));
		}
		
		if(paciente.getCobertura() != null){
			specifications = SpecificationUtils.appendSpecification(specifications, PacienteSpecifications.coberturaEquals(paciente.getCobertura()));
		}
				
		return pacienteDAO.findAll(specifications);
	}
}
