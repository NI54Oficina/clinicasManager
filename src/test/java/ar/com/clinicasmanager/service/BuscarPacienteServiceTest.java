package ar.com.clinicasmanager.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Ocupacion;
import ar.com.clinicasmanager.entity.enums.Sexo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
public class BuscarPacienteServiceTest {

	@Autowired
	private BuscadorService buscadorService;	
	
	@Autowired
	private PacienteService pacienteService;
	
	@Test
	public void testsearchPacientes(){
		crearPaciente("1","nombre1",10,Ocupacion.ADMINISTRATIVO,Sexo.MASC,Cobertura.ART);
		crearPaciente("2","nombre2",10,Ocupacion.ALIMENTICIA,Sexo.MASC,Cobertura.OS);
		crearPaciente("3","nombre3",10,Ocupacion.AMA_DE_CASA,Sexo.FEM,Cobertura.ART);
		
		Paciente pacienteABuscar = new Paciente();
		List<Paciente> pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 3);
		
		pacienteABuscar = new Paciente();
		pacienteABuscar.setNombre("nombre");
		pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 3);
		
		pacienteABuscar.setOcupacion(Ocupacion.AMA_DE_CASA);
		pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 1);
		
		pacienteABuscar = new Paciente();
		pacienteABuscar.setCobertura(Cobertura.ART);
		pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 2);
		
		pacienteABuscar = new Paciente();
		pacienteABuscar.setNombre("1");
		pacienteABuscar.setCobertura(Cobertura.ART);
		pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 1);
		
		pacienteABuscar = new Paciente();
		pacienteABuscar.setSexo(Sexo.MASC);
		pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 2);
		
		pacienteABuscar = new Paciente();
		pacienteABuscar.setSexo(Sexo.FEM);
		pacienteABuscar.setCobertura(Cobertura.ART);
		pacienteABuscar.setNombre("nombre3");
		pacienteABuscar.setOcupacion(Ocupacion.AMA_DE_CASA);
		pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 1);
		
		pacienteABuscar.setOcupacion(Ocupacion.ADMINISTRATIVO);
		pacientes = buscadorService.searchPacientes(pacienteABuscar);		
		Assert.assertEquals(pacientes.size(), 0);
	}
	
	private void crearPaciente(String dni, String nombre, int edad, Ocupacion ocupacion, Sexo sexo, Cobertura cobertura){
		Paciente paciente = new Paciente();
		paciente.setCobertura(cobertura);
		paciente.setDni(dni);
		paciente.setEdad(edad);
		paciente.setNombre(nombre);
		paciente.setOcupacion(ocupacion);
		paciente.setSexo(sexo);
		pacienteService.savePaciente(paciente);
	}
	
}
