package ar.com.clinicasmanager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.clinicasmanager.dao.ConfiguracionFichaDAO;
import ar.com.clinicasmanager.dao.FichaDAO;
import ar.com.clinicasmanager.dao.NodoDiagnosticoDAO;
import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.ConfiguracionFicha;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Diagnostico;
import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.FichaPregunta;
import ar.com.clinicasmanager.entity.Lesion;
import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.entity.NodoTratamiento;
import ar.com.clinicasmanager.entity.RespuestasFicha;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.EstadoCirugia;
import ar.com.clinicasmanager.entity.enums.Miembro;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
public class ConsultaServiceTest {

	@Autowired
	private FichaDAO fichaDAO;

	@Autowired
	private ConfiguracionFichaDAO configuracionFichaDAO;
	
	@Autowired
	private NodoDiagnosticoDAO nodoDiagnosticoDAO;
	
	@Autowired
	private ConsultaService consultaService;
	
	@Test
	public void testGetTratamientoAndCirugiasOrderedByDate(){
		Consulta consulta = new Consulta();
		
		Tratamiento tratamiento = new Tratamiento(new NodoTratamiento(), "trat1");
		tratamiento.setFechaInicioTratamiento(new Date(5));
		
		Cirugia cirugia = new Cirugia();
		cirugia.setFullName("cirug1");
		cirugia.setEstado(EstadoCirugia.REALIZADA);
		cirugia.setFechaCirugia(new Date(10));
		
		Tratamiento tratamiento2 = new Tratamiento(new NodoTratamiento(), "trat2");
		tratamiento2.setFechaInicioTratamiento(new Date(15));
		
		Cirugia cirugia2 = new Cirugia();
		cirugia2.setFullName("cirug2");
		cirugia2.setEstado(EstadoCirugia.PENDIENTE);
		cirugia2.setFechaCirugia(new Date(20));
		
		consulta.setTratamientos(Lists.newArrayList(tratamiento, tratamiento2));
		consulta.setCirugias(Lists.newArrayList(cirugia, cirugia2));
		
		List<Tratamiento> tratamientosOrdered = consultaService.getTratamientoAndCirugiasOrderedByDate(consulta);
		
		Assert.assertEquals(tratamientosOrdered.size(), 3);
		
		Assert.assertEquals(tratamientosOrdered.get(0).getFullName(), "trat2");
		Assert.assertEquals(tratamientosOrdered.get(1).getFullName(), "cirug1");
		Assert.assertEquals(tratamientosOrdered.get(2).getFullName(), "trat1");
		
	}
	
	@Test
	public void testGetFichasPendientes(){
		Ficha ficha = createFicha();
		Ficha ficha2 = createFicha();
		Ficha ficha3 = createFicha();
		
		NodoDiagnostico nodo1 = createNodo(1);
		NodoDiagnostico nodo2 = createNodo(2);
		NodoDiagnostico nodo3 = createNodo(3);
		
		createConfiguracionFicha(ficha, Lists.newArrayList(nodo1, nodo2), 1);
		createConfiguracionFicha(ficha2, Lists.newArrayList(nodo3, nodo2), 2);
		createConfiguracionFicha(ficha3, Lists.newArrayList(nodo3), 3);
		
		Consulta consulta = createConsulta(Sets.newHashSet(nodo1, nodo2));
		consulta.setRespuestasFichas(new ArrayList<RespuestasFicha>());
		
		// Fecha de consulta, 10 días atras
		consulta.setFechaPrimerConsulta(new Date(new Date().getTime() - 10 * 24 * 3600 * 1000));		
		Assert.assertEquals(1, consultaService.getFichasPendientes(consulta).size());
		
		// Fecha de consulta, 20 días atras
		consulta.setFechaPrimerConsulta(new Date(new Date().getTime() - 20 * 24 * 3600 * 1000));		
		Assert.assertEquals(2, consultaService.getFichasPendientes(consulta).size());
		
		// Respondo una de las fichas pendientes
		RespuestasFicha respuestasFicha = new RespuestasFicha();
		respuestasFicha.setFicha(ficha);		
		consulta.setRespuestasFichas(Lists.newArrayList(respuestasFicha));		
		Assert.assertEquals(1, consultaService.getFichasPendientes(consulta).size());
		
		// Respondo todas las fichas pendientes
		RespuestasFicha respuestasFicha2 = new RespuestasFicha();
		respuestasFicha2.setFicha(ficha2);		
		consulta.addRespuestasFicha(respuestasFicha2);		
		Assert.assertEquals(0, consultaService.getFichasPendientes(consulta).size());
		
	}

	private Consulta createConsulta(Set<NodoDiagnostico> nodos) {
		Consulta consulta = new Consulta();
		
		Diagnostico diagnostico = new Diagnostico();
		diagnostico.setLesiones(new HashSet<Lesion>());
		for (NodoDiagnostico nodo : nodos) {
			diagnostico.addLesion(new Lesion(Miembro.MSD, nodo, ""));
		}
		
		consulta.setDiagnostico(diagnostico);
		
		return consulta;
	}

	private void createConfiguracionFicha(Ficha ficha, ArrayList<NodoDiagnostico> nodos, int semanas) {
		for (NodoDiagnostico nodoDiagnostico : nodos) {
			ConfiguracionFicha configuracionFicha = new ConfiguracionFicha(ficha);
			configuracionFicha.setDiagnostico(nodoDiagnostico);
			configuracionFicha.setPeriodoSemanas(semanas);
			configuracionFichaDAO.save(configuracionFicha);
		}
	}

	private NodoDiagnostico createNodo(int i) {
		NodoDiagnostico nodo = new NodoDiagnostico();
		nodo.setLabel("" + i);
		
		nodoDiagnosticoDAO.save(nodo);
		
		return nodo;
	}

	private Ficha createFicha() {
		FichaPregunta fichaPregunta = new FichaPregunta();
		fichaPregunta.setPregunta("pregunta");
		
		Ficha ficha = new Ficha();
		ficha.setNombre("nombre");
		
		ficha.setPreguntas(Lists.newArrayList(fichaPregunta));
		
		fichaDAO.save(ficha);
		
		return ficha;
	}
}
