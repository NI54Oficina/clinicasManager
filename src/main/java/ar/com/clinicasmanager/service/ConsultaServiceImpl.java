package ar.com.clinicasmanager.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clinicasmanager.dao.ConfiguracionFichaDAO;
import ar.com.clinicasmanager.dao.ConsultaDAO;
import ar.com.clinicasmanager.entity.Alta;
import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.ConfiguracionFicha;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Evolucion;
import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.Internacion;
import ar.com.clinicasmanager.entity.Lesion;
import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.EstadoCirugia;
import ar.com.clinicasmanager.function.CirugiaToTratamientoFunction;
import ar.com.clinicasmanager.order.ByDateDesc;
import ar.com.clinicasmanager.predicate.CirugiaFueRealizadaPredicate;
import ar.com.clinicasmanager.predicate.FichaFueRespondidaPredicate;
import ar.com.clinicasmanager.predicate.SemanasConfiguracionSuperadoPredicate;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ConsultaServiceImpl implements ConsultaService {
	
	@Autowired
	private ConsultaDAO consultaDAO;
	
	@Autowired
	private ConfiguracionFichaDAO configuracionFichaDAO;

	@Autowired
	private DiagnosticoService diagnosticoService;
	
	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;
	
	@Override
	public Consulta save(Consulta consulta) {
		return consultaDAO.save(consulta);
	}

	@Override
	public Consulta findOne(Long id) {
		return consultaDAO.findOne(id);
	}
	
	@Override
	public void deleteConsulta(Long id) {
		consultaDAO.delete(id);
	}
	
	@Override
	public void deleteConsultas(List<Consulta> entities) {
		consultaDAO.delete(entities);
	}

	@Override
	public List<Consulta> findByPaciente(Paciente paciente) {
		return consultaDAO.findByPaciente(paciente);
	}

	@Override
	public void darAlta(Consulta consulta, Alta alta) {
		consulta.getEstado().setDadoDeAlta(true);
		consulta.setAlta(alta);
		save(consulta);
	}

	@Override
	public Cirugia getCirugiaPendiente(Consulta consulta) {
		Cirugia cirugiaPendiente = null;
		for (Cirugia cirugia : consulta.getCirugias()) {
			if(cirugia.getEstado().equals(EstadoCirugia.PENDIENTE)){
				cirugiaPendiente = cirugia;
			}
		}
		return cirugiaPendiente;
	}

	@Override
	public List<Evolucion> getEvoluciones(Consulta consulta, int maxResult) {
		//FIXME: hacer esto con alguna query
		if (!consulta.getEvoluciones().isEmpty()) {
			return Iterables.get(Iterables.partition(consulta.getEvoluciones(), maxResult), 0);
		}
		return null;
	}

	@Override
	public Tratamiento getTratamiento(Consulta consulta, Long idTratamiento) {
		Tratamiento tratamiento = null;
		
		for (Tratamiento tratamientoConsulta : consulta.getTratamientos()) {
			if(tratamientoConsulta.getId().equals(idTratamiento)){
				tratamiento = tratamientoConsulta;
			}
		}
		return tratamiento;
	}

	@Override
	public Internacion getInternacion(Consulta consulta, final Long idInternacion) {
		Predicate<Internacion> predicate = new Predicate<Internacion>() {

			@Override
			public boolean apply(@Nullable Internacion input) {
				return input.getId().equals(idInternacion);
			}
		};
		
		return Iterables.find(consulta.getInternaciones(), predicate);
	}

	@Override
	public Set<Ficha> getFichasPendientes(Consulta consulta) {
		Duration duration = new Duration(consulta.getFechaPrimerConsulta().getTime(), new Date().getTime());
		double semanas = duration.getStandardDays() / 7;
		
		Set<Ficha> fichasPendientes = new HashSet<Ficha>();
		List<NodoDiagnostico> nodos = new ArrayList<NodoDiagnostico>();
		
		if(consulta.getDiagnostico() != null){
			for (Lesion lesion : consulta.getDiagnostico().getLesiones()) {
				nodos.addAll(nodoDiagnosticoService.getParentLeaves(lesion.getPatologia().getId()));
			}
			
			for (ConfiguracionFicha configuracionFicha : getFichasParaResponder(nodos, semanas)) {
				if(!Iterables.any(consulta.getRespuestasFichas(), new FichaFueRespondidaPredicate(configuracionFicha.getFicha()))){
					fichasPendientes.add(configuracionFicha.getFicha());
				}
			}
		}
		return fichasPendientes;
	}

	private List<ConfiguracionFicha> getFichasParaResponder(List<NodoDiagnostico> nodos, double semanas) {
		List<ConfiguracionFicha> configuraciones = configuracionFichaDAO.findByDiagnosticoIn(nodos);
		return Lists.newArrayList(Iterables.filter(configuraciones, new SemanasConfiguracionSuperadoPredicate(semanas)));
	}
	
	@Override
	public List<Tratamiento> getTratamientoAndCirugiasOrderedByDate(Consulta consulta) {
		Iterable<Cirugia> cirugiasRealizadas = Iterables.filter(consulta.getCirugias(), new CirugiaFueRealizadaPredicate());
		List<Tratamiento> tratamientosRealizados = Lists.newArrayList(Iterables.transform(cirugiasRealizadas, new CirugiaToTratamientoFunction()));
		Iterables.addAll(tratamientosRealizados, consulta.getTratamientos());
		
		Collections.sort(tratamientosRealizados, new ByDateDesc());
		
		return Lists.reverse(tratamientosRealizados);
	}

	@Override
	public Cirugia getUltimaCirugiaRealizada(Consulta consulta) {
		List<Cirugia> cirugias = consulta.getCirugias();
		if(cirugias.size() > 0){
			cirugias = Lists.newArrayList(Iterables.filter(cirugias, new CirugiaFueRealizadaPredicate()));
			Collections.sort(cirugias, new Comparator<Cirugia>() {
	
				@Override
				public int compare(Cirugia o1, Cirugia o2) {
					return o2.getFechaCirugia().compareTo(o1.getFechaCirugia());
				}
			});
		}
		return Iterables.getFirst(cirugias, null);
	}
	
}
