package ar.com.clinicasmanager.specification;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clinicasmanager.dao.ConsultaDAO;
import ar.com.clinicasmanager.dao.NodoDiagnosticoDAO;
import ar.com.clinicasmanager.dao.NodoTratamientoDAO;
import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Diagnostico;
import ar.com.clinicasmanager.entity.EstadoConsulta;
import ar.com.clinicasmanager.entity.Lesion;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Miembro;
import ar.com.clinicasmanager.entity.enums.Sexo;
import ar.com.clinicasmanager.search.Comparacion;
import ar.com.clinicasmanager.search.PacienteSearch;
import ar.com.clinicasmanager.service.BuscadorService;
import ar.com.clinicasmanager.service.PacienteService;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Longs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class ConsultaSpecificationsTest {
	
	@Autowired
	private NodoTratamientoDAO nodoTratamientoDAO;
	
	@Autowired
	private NodoDiagnosticoDAO nodoDiagnosticoDAO;
	
	@Autowired
	private ConsultaDAO consultaDAO;
	
	@Autowired
	private PacienteService pacienteService;	
	
	@Autowired
	private BuscadorService buscadorService;

	@Test
	public void testHasLesionSpecification(){
		Paciente paciente = createAndSaveValidPaciente();
		
		Lesion lesion = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(2L), "fullName");
		Lesion lesion2 = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(3L), "fullName");
		createAndSaveConsulta(paciente, createDiagnostico(Sets.newHashSet(lesion, lesion2)), null);
		
		lesion = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(3L), "fullName");
		lesion2 = new Lesion(Miembro.MSD, nodoDiagnosticoDAO.findOne(4L), "fullName");
		createAndSaveConsulta(paciente, createDiagnostico(Sets.newHashSet(lesion, lesion2)), null);
		
		assertEquals(2, consultaDAO.findAll(ConsultaSpecifications.hasLesion(Longs.asList(3l))).size());
		assertEquals(2, consultaDAO.findAll(ConsultaSpecifications.hasLesion(Longs.asList(2l, 4l))).size());
		assertEquals(2, consultaDAO.findAll(ConsultaSpecifications.hasLesion(Longs.asList(2l, 3l, 4l))).size());
		assertEquals(1, consultaDAO.findAll(ConsultaSpecifications.hasLesion(Longs.asList(2l))).size());
		assertEquals(0, consultaDAO.findAll(ConsultaSpecifications.hasLesion(Longs.asList(5l))).size());
	}
	
	@Test
	public void testHasTratamientoSpecification(){
		Paciente paciente = createAndSaveValidPaciente();
		
		Tratamiento tratamiento = new Tratamiento(nodoTratamientoDAO.findOne(2L), "fullname");
		Tratamiento tratamiento2 = new Tratamiento(nodoTratamientoDAO.findOne(3L), "fullname");
		createAndSaveConsulta(paciente, null, Lists.newArrayList(tratamiento, tratamiento2));
		
		tratamiento = new Tratamiento(nodoTratamientoDAO.findOne(3L), "fullname");
		tratamiento2 = new Tratamiento(nodoTratamientoDAO.findOne(4L), "fullname");
		createAndSaveConsulta(paciente, null, Lists.newArrayList(tratamiento, tratamiento2));
		
		tratamiento = new Tratamiento(nodoTratamientoDAO.findOne(5L), "fullname");
		tratamiento2 = new Tratamiento(nodoTratamientoDAO.findOne(6L), "fullname");
		Consulta consulta = createAndSaveConsulta(paciente, null, Lists.newArrayList(tratamiento, tratamiento2));
		
		Cirugia cirugia = new Cirugia();
		cirugia.setTratamiento(nodoTratamientoDAO.findOne(7L));
		consulta.setCirugias(Lists.newArrayList(cirugia));
		consultaDAO.save(consulta);
		
		assertEquals(2, consultaDAO.findAll(ConsultaSpecifications.hasTratamiento(Longs.asList(3))).size());
		assertEquals(1, consultaDAO.findAll(ConsultaSpecifications.hasTratamiento(Longs.asList(4))).size());
		assertEquals(1, consultaDAO.findAll(ConsultaSpecifications.hasTratamiento(Longs.asList(5))).size());
		assertEquals(0, consultaDAO.findAll(ConsultaSpecifications.hasTratamiento(Longs.asList(1))).size());
		assertEquals(1, consultaDAO.findAll(ConsultaSpecifications.hasTratamiento(Longs.asList(7))).size());
		assertEquals(2, consultaDAO.findAll(ConsultaSpecifications.hasTratamiento(Longs.asList(2,7))).size());
	}
	
	@Test
	public void testIsDadoDeAltaSpecification(){
		Paciente paciente = createAndSaveValidPaciente();
		
		EstadoConsulta estadoConsulta = new EstadoConsulta();
		estadoConsulta.setDadoDeAlta(true);
		
		createAndSaveConsulta(paciente, estadoConsulta);
		createAndSaveConsulta(paciente, new EstadoConsulta());
		
		assertEquals(1, consultaDAO.findAll(ConsultaSpecifications.isDadoDeAlta()).size());
	}
	
	@Test
	public void testHasPacienteSpecification() {
		Paciente paciente = crearPaciente(10, Sexo.MASC, Cobertura.ART, "obraSocial");
		
		createAndSaveConsulta(paciente, null, null);
		
		PacienteSearch searchCriteria = new PacienteSearch();
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria))).size());
		
		searchCriteria.setEdad(10);
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria))).size());
		
		searchCriteria.setEdad(11);
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setComparacionEdad(Comparacion.LT);
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setComparacionEdad(Comparacion.LE);
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setComparacionEdad(Comparacion.GT);
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setEdad(10);
		searchCriteria.setComparacionEdad(Comparacion.GE);
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setComparacionEdad(Comparacion.BETWEEN);
		searchCriteria.setEdad(11);
		searchCriteria.setEdadMax(13);
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setEdad(9);
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria = new PacienteSearch();
		searchCriteria.setCobertura(Cobertura.ART);
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setCobertura(Cobertura.OS);
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria = new PacienteSearch();
		searchCriteria.setSexo(Sexo.MASC);
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setSexo(Sexo.FEM);
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria = new PacienteSearch();
		searchCriteria.setObraSocial("obraSocial");
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setObraSocial("obraSocialDos");
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria = new PacienteSearch();
		searchCriteria.setEdad(10);
		searchCriteria.setSexo(Sexo.MASC);
		searchCriteria.setCobertura(Cobertura.ART);
		searchCriteria.setObraSocial("obraSocial");
		assertEquals(1, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setEdad(10);
		searchCriteria.setSexo(Sexo.FEM);
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setSexo(Sexo.MASC);
		searchCriteria.setCobertura(Cobertura.OS);
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
		
		searchCriteria.setCobertura(Cobertura.ART);
		searchCriteria.setObraSocial("obraSocialDos");
		assertEquals(0, consultaDAO.findAll(Specifications.where(ConsultaSpecifications.hasPaciente(searchCriteria ))).size());
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
	
	private Consulta createAndSaveConsulta(Paciente paciente, EstadoConsulta estadoConsulta){
		Consulta consulta = new Consulta();
		consulta.setPaciente(paciente);
		consulta.setEstado(estadoConsulta);
		return consultaDAO.save(consulta);
	}	
	
	private Consulta createAndSaveConsulta(Paciente paciente, Diagnostico diagnostico, List<Tratamiento> tratamientos){
		Consulta consulta = new Consulta();
		consulta.setPaciente(paciente);
		consulta.setDiagnostico(diagnostico);
		consulta.setTratamientos(tratamientos);
		return consultaDAO.save(consulta);
	}
	
	private Paciente createAndSaveValidPaciente(){
		Paciente paciente = new Paciente();
		pacienteService.savePaciente(paciente);
		return paciente;
	}
}
