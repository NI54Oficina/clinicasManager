package ar.com.clinicasmanager.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.text.SimpleDateFormat;
import java.util.List;

import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Diagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class GoogleCalendarServiceImpl implements GoogleCalendarService {
	
	@Autowired
	private Calendar googleCalendarService;

	@Autowired
	private JdbcUsersConnectionRepository usersConnectionRepository;

	@Override
	public String crearEvento(Consulta consulta, Cirugia cirugia, String calendarId) throws Exception {
		
		String accessToken = getSessionAccessToken();
		
		com.google.api.services.calendar.model.Calendar calendar = googleCalendarService.calendars().get(calendarId).setOauthToken(accessToken).execute();
		
		Event createdEvent = googleCalendarService.events().insert(calendar.getId(), buildEvent(consulta, cirugia)).setOauthToken(accessToken).execute();
		return createdEvent.getId();
	}

	@Override
	public void eliminarEvento(String calendarId, String googleEventId) throws Exception {

		String accessToken = getSessionAccessToken();
		
		com.google.api.services.calendar.model.Calendar calendar = googleCalendarService.calendars().get(calendarId).setOauthToken(accessToken).execute();
		
		googleCalendarService.events().delete(calendar.getId(), googleEventId).setOauthToken(accessToken).execute();
	}
	
	@Override
	public String editarEvento(Consulta consulta, Cirugia cirugia) throws Exception {
		
		String accessToken = getSessionAccessToken();
		
		com.google.api.services.calendar.model.Calendar calendar = googleCalendarService.calendars().get(cirugia.getGoogleCalendarId()).setOauthToken(accessToken).execute();
		
		Event event = buildEvent(consulta, cirugia);
		
		Event eventDb = googleCalendarService.events().get(calendar.getId(), cirugia.getGoogleEventId()).setOauthToken(accessToken).execute();
		eventDb.setSequence(eventDb.getSequence() + 1);
		eventDb.setStart(event.getStart());
		eventDb.setEnd(event.getEnd());
		eventDb.setSummary(event.getSummary());
		eventDb.setLocation(event.getLocation());
		
		return googleCalendarService.events().update(calendar.getId(), cirugia.getGoogleEventId(), eventDb).setOauthToken(accessToken).execute().getId();
	}
	
	@Override
	public List<CalendarListEntry> getCalendarios() throws Exception {
		String accessToken = getSessionAccessToken();
		
		return googleCalendarService.calendarList().list().setMinAccessRole("writer").setOauthToken(accessToken).execute().getItems();
	}	
	
	private Event buildEvent(Consulta consulta, Cirugia cirugia){
		//TODO: automatizar zona horaria
		Event event = new Event();
		event.setSummary("Cirugía ");
		event.setDescription(buildDescription(consulta, cirugia));
		event.setLocation(cirugia.getLugar());
		
	    int gmtOffset = 1000 * 60 * 60 * 2;

		EventDateTime startTime = new EventDateTime();
		startTime.setDateTime(new DateTime(cirugia.getFechaCirugia().getTime() - gmtOffset));
		event.setStart(startTime  );
		
		java.util.Calendar calendar = java.util.Calendar.getInstance(); // creates calendar
	    calendar.setTime(cirugia.getFechaCirugia()); // sets calendar time/date
	    calendar.add(java.util.Calendar.HOUR_OF_DAY, 1); // adds one hour
	    
		DateTime dateTime = new DateTime(calendar.getTime().getTime() - gmtOffset);
		
		EventDateTime end = new EventDateTime();
		end.setDateTime(dateTime );
		event.setEnd(end);
		
		return event;
	}
	
	private String buildDescription(Consulta consulta, Cirugia cirugia) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder builder = new StringBuilder();
		
		builder.append("Nombre: " + consulta.getPaciente().getNombre() + System.lineSeparator());
		builder.append("Edad: " + consulta.getPaciente().getEdad() + System.lineSeparator());
		builder.append("DNI: " + consulta.getPaciente().getDni() + System.lineSeparator());
		builder.append("N° afiliado: " + consulta.getPaciente().getNumeroSocio() + System.lineSeparator());
		builder.append("Teléfono: " + consulta.getPaciente().getTelefono() + System.lineSeparator());
		builder.append("Cobertura: " + consulta.getPaciente().getCobertura() + System.lineSeparator());
		if(!StringUtils.isEmpty(consulta.getPaciente().getObraSocial())) {
			builder.append("Obra social: " + consulta.getPaciente().getObraSocial() + System.lineSeparator());
		}

		if(consulta.getDiagnostico() != null){
			builder.append("Diagnósticos:" + System.lineSeparator());
			builder.append(dateFormat.format(consulta.getDiagnostico().getFechaDiagnostico()) + " - " + consulta.getDiagnostico().getResumen());
			for (Diagnostico diagnostico : consulta.getDiagnosticosAnteriores()) {
				builder.append(dateFormat.format(diagnostico.getFechaDiagnostico()) + " - " + diagnostico.getResumen() + System.lineSeparator());
			}
			builder.append(System.lineSeparator());
		}
		
		builder.append("Cirugía propuesta: " + cirugia.getFullName() + System.lineSeparator());
		builder.append("Descripción: " + cirugia.getDescripcion() + System.lineSeparator());
		builder.append("Lugar: " + cirugia.getLugar() + System.lineSeparator());
		
		return builder.toString();
	}
	
	private String getSessionAccessToken() throws Exception{
		Connection<Google> connection = usersConnectionRepository.createConnectionRepository(
			"equipodoctoralevy@gmail.com").findPrimaryConnection(Google.class);

		if(connection.hasExpired()){
			connection.refresh();
		}

		return connection.getApi().getAccessToken();
	}

}
