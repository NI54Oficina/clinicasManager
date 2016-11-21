package ar.com.clinicasmanager.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clinicasmanager.dao.ConsultaDAO;
import ar.com.clinicasmanager.dao.NodoDiagnosticoDAO;
import ar.com.clinicasmanager.dao.NodoTratamientoDAO;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Diagnostico;
import ar.com.clinicasmanager.entity.Lesion;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Miembro;
import ar.com.clinicasmanager.entity.enums.Sexo;
import ar.com.clinicasmanager.search.FechasSearch;
import ar.com.clinicasmanager.search.OperadorLogico;
import ar.com.clinicasmanager.search.PacienteSearch;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Longs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class BuscarConsultaTest {

	@Autowired
	private BuscadorService buscadorService;	
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private NodoTratamientoDAO nodoTratamientoDAO;
	
	@Autowired
	private NodoDiagnosticoDAO nodoDiagnosticoDAO;
	
	@Autowired
	private ConsultaDAO consultaDAO;
	
	@Before
	public void setUp() {
		Paciente paciente = crearPaciente(10, Sexo.MASC, Cobertura.ART, "obraSocial");
		Paciente pacienteTwo = crearPaciente(10, Sexo.FEM, Cobertura.OS, "obraSocial");
		
		Tratamiento tratamiento = new Tratamiento(nodoTratamientoDAO.findOne(3L), "fullname");
		Tratamiento tratamiento2 = new Tratamiento(nodoTratamientoDAO.findOne(6L), "fullname");
		Lesion lesion = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(8L), "fullName");
		Lesion lesion2 = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(10L), "fullName");
		createAndSaveConsulta(paciente, createDiagnostico(Sets.newHashSet(lesion, lesion2)), Lists.newArrayList(tratamiento, tratamiento2), new Date());
		
		tratamiento = new Tratamiento(nodoTratamientoDAO.findOne(6L), "fullname");
		tratamiento2 = new Tratamiento(nodoTratamientoDAO.findOne(7L), "fullname");
		lesion = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(10L), "fullName");
		lesion2 = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(11L), "fullName");
		createAndSaveConsulta(paciente, createDiagnostico(Sets.newHashSet(lesion, lesion2)), Lists.newArrayList(tratamiento, tratamiento2), new Date(969678000000L)); //09/23/2000
		
		tratamiento = new Tratamiento(nodoTratamientoDAO.findOne(6L), "fullname");
		tratamiento2 = new Tratamiento(nodoTratamientoDAO.findOne(7L), "fullname");
		lesion = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(10L), "fullName");
		lesion2 = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(11L), "fullName");
		createAndSaveConsulta(pacienteTwo, createDiagnostico(Sets.newHashSet(lesion, lesion2)), Lists.newArrayList(tratamiento, tratamiento2), new Date(1600830000000L)); //09/23/2020
	}
	
	@Test
	public void testSearchNoFilter() {
		assertEquals(3, buscadorService.searchConsultas(null, Longs.asList(), null, Longs.asList(), null, null).size());
	}
	
	@Test
	public void testSearchConsultaByPaciente(){
		PacienteSearch pacienteCriteria = new PacienteSearch();
		
		pacienteCriteria.setEdad(10);
		assertEquals(3, buscadorService.searchConsultas(null, Longs.asList(), null, Longs.asList(), pacienteCriteria, null).size());
		
		pacienteCriteria.setSexo(Sexo.FEM);
		assertEquals(1, buscadorService.searchConsultas(null, Longs.asList(), null, Longs.asList(), pacienteCriteria, null).size());
	}
	
	@Test
	public void testSearchConsultaByDiagnosticos(){
		List<Consulta> consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(8L), null, Longs.asList(), null, null);
		assertEquals(1, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(10L), null, Longs.asList(), null, null);
		assertEquals(3, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(8, 12), null, Longs.asList(), null, null);
		assertEquals(0, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(10, 11), null, Longs.asList(), null, null);
		assertEquals(2, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(8, 10, 11), null, Longs.asList(), null, null);
		assertEquals(0, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.OR, Longs.asList(8, 12), null, Longs.asList(), null, null);
		assertEquals(1, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.OR, Longs.asList(10, 11), null, Longs.asList(), null, null);
		assertEquals(3, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.OR, Longs.asList(8, 10, 11), null, Longs.asList(), null, null);
		assertEquals(3, consultas.size());		
	}	
	
	@Test
	public void testSearchConsultaByTratamiento(){
		List<Consulta> consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.AND, Longs.asList(6), null, null);
		assertEquals(3, consultas.size());
		
		consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.AND, Longs.asList(3), null, null);
		assertEquals(1, consultas.size());
		
		consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.AND, Longs.asList(3, 8), null, null);
		assertEquals(0, consultas.size());
		
		consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.AND, Longs.asList(3, 6), null, null);
		assertEquals(1, consultas.size());
		
		consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.AND, Longs.asList(3, 6, 7), null, null);
		assertEquals(0, consultas.size());
		
		consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.OR, Longs.asList(3, 8), null, null);
		assertEquals(1, consultas.size());
		
		consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.OR, Longs.asList(3, 6), null, null);
		assertEquals(3, consultas.size());
		
		consultas = buscadorService.searchConsultas(null, Longs.asList(), OperadorLogico.OR, Longs.asList(3, 6, 7), null, null);
		assertEquals(3, consultas.size());		
	}	
	
	@Test
	public void testSearchConsultaByFechaPrimerConsulta() {
		FechasSearch fechas = new FechasSearch();
		fechas.setPrimerConsultaTo(new Date(1569207600000L)); //09/23/2019
		
		List<Consulta> consultas = buscadorService.searchConsultas(null, Longs.asList(), null, Longs.asList(), null, fechas );
		assertEquals(2, consultas.size());
		
		fechas.setPrimerConsultaFrom(new Date(1001214000000L)); //09/23/2001
		consultas = buscadorService.searchConsultas(null, Longs.asList(), null, Longs.asList(), null, fechas );
		assertEquals(1, consultas.size());
		
		fechas.setPrimerConsultaTo(null);
		consultas = buscadorService.searchConsultas(null, Longs.asList(), null, Longs.asList(), null, fechas );
		assertEquals(2, consultas.size());
	}
	
	@Test
	public void testSearchConsultaByTratamientoDiagnosticoAndPaciente() {
		FechasSearch fechas = new FechasSearch();
		
		PacienteSearch pacienteCriteria = new PacienteSearch();
		pacienteCriteria.setSexo(Sexo.FEM);
		
		List<Consulta> consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(10), OperadorLogico.AND, Longs.asList(6), pacienteCriteria, fechas);
		assertEquals(1, consultas.size());
		
		consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(10), OperadorLogico.AND, Longs.asList(3), pacienteCriteria, fechas);
		assertEquals(0, consultas.size());
		
		pacienteCriteria.setSexo(Sexo.MASC);
		consultas = buscadorService.searchConsultas(OperadorLogico.AND, Longs.asList(10), OperadorLogico.AND, Longs.asList(6), pacienteCriteria, fechas);
		assertEquals(2, consultas.size());
	}
	
	private Paciente crearPaciente(int edad, Sexo sexo, Cobertura cobertura, String obraSocial){
		Paciente paciente = new Paciente();
		paciente.setCobertura(cobertura);
		paciente.setEdad(edad);
		paciente.setObraSocial(obraSocial);
		paciente.setSexo(sexo);
		pacienteService.savePaciente(paciente);
		return paciente;
	}	
	
	private Diagnostico createDiagnostico(Set<Lesion> lesiones){
		Diagnostico diagnostico = new Diagnostico();
		diagnostico.setFechaDiagnostico(new Date());
		diagnostico.setLesiones(lesiones);
		return diagnostico;
	}
	
	private Consulta createAndSaveConsulta(Paciente paciente, Diagnostico diagnostico, List<Tratamiento> tratamientos, Date fechaPrimerConsulta){
		Consulta consulta = new Consulta();
		consulta.setPaciente(paciente);
		consulta.setDiagnostico(diagnostico);
		consulta.setTratamientos(tratamientos);
		consulta.setFechaPrimerConsulta(fechaPrimerConsulta);
		return consultaDAO.save(consulta);
	}
	
}