package ar.com.clinicasmanager.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
public class GoogleCalendarServiceTest {

	@Autowired
	private GoogleCalendarService calendarService;

	@Test
	public void testGetCalendar() throws Exception {

		calendarService.getCalendarios().get(0);
		
	}
	
}
