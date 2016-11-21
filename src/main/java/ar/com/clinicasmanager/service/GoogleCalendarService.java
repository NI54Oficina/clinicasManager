package ar.com.clinicasmanager.service;

import java.util.List;

import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Consulta;

import com.google.api.services.calendar.model.CalendarListEntry;

public interface GoogleCalendarService {

	String crearEvento(Consulta consulta, Cirugia cirugia, String calendarId) throws Exception;

	void eliminarEvento(String googleCalendarId, String googleEventId) throws Exception;

	String editarEvento(Consulta consulta, Cirugia cirugia) throws Exception;

	List<CalendarListEntry> getCalendarios() throws Exception;

}
