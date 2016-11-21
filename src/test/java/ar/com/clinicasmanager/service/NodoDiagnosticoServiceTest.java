package ar.com.clinicasmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clinicasmanager.entity.NodoDiagnostico;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class NodoDiagnosticoServiceTest {

	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;
	
	@Test
	public void testGetChildrenIds() {
		List<Long> ids = nodoDiagnosticoService.getChildrenLeavesIds(8953L);
		
		assertEquals(ids.size(), 28);
	}
	
	@Test
	public void testGetNodo() {
		NodoDiagnostico nodo = nodoDiagnosticoService.getNodo(8953L);
		
		assertEquals(nodo.getId(), Long.valueOf(8953));
		assertEquals(nodo.getLabel(), "misceláneas");
		
		nodo = nodoDiagnosticoService.getNodo(8975L);
		
		assertEquals(nodo.getId(), Long.valueOf(8975));
		assertEquals(nodo.getLabel(), "5to rayo");
		
		nodo = nodoDiagnosticoService.getNodo(10000L);
		
		assertNull(nodo);
		
	}
}
