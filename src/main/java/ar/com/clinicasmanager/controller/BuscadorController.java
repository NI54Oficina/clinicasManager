package ar.com.clinicasmanager.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.clinicasmanager.controller.form.BuscarConsultaForm;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Ocupacion;
import ar.com.clinicasmanager.entity.enums.Sexo;
import ar.com.clinicasmanager.search.Comparacion;
import ar.com.clinicasmanager.search.OperadorLogico;
import ar.com.clinicasmanager.service.BuscadorService;
import ar.com.clinicasmanager.service.NodoDiagnosticoService;
import ar.com.clinicasmanager.service.TratamientoService;

import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;

@Controller
public class BuscadorController {
	
	@Autowired
	private BuscadorService buscadorService;
	
	@Autowired
	private TratamientoService tratamientoService;
	
	@Autowired
	private NodoDiagnosticoService nodoDiagnosticoService;	
	
	@RequestMapping(value = "/buscar/paciente", produces = "text/html")
	public String buscarPorPaciente(Paciente paciente, Model uiModel, HttpServletRequest request){
		populateEditForm(uiModel, new Paciente());
		if(request.getParameterMap().size() > 0){
			uiModel.addAttribute("pacientes", buscadorService.searchPacientes(paciente));
			uiModel.addAttribute("busquedaRealizada", true);
		}
		
		return "buscador/paciente";
	}
	
	@RequestMapping(value = "/buscar/consulta", produces = "text/html")
	public String buscarPorConsulta(BuscarConsultaForm form, Model uiModel, HttpServletRequest request) {
		List<Long> lesionesIds = transformStringToLongList(form.getIdDiagnostico());
		List<Long> tratamientosIds = transformStringToLongList(form.getIdTratamiento());
		
		if(!lesionesIds.isEmpty() || !tratamientosIds.isEmpty() || !form.pacienteIsEmpty() || !form.fechasIsEmpty()) {
			OperadorLogico operadorLesiones = form.getOperadorLesiones() != null ? form.getOperadorLesiones() : OperadorLogico.OR;
			OperadorLogico operadorTratamientos = form.getOperadorTratamientos() != null ? form.getOperadorTratamientos() : OperadorLogico.OR;
			
			uiModel.addAttribute("consultas", buscadorService.searchConsultas(operadorLesiones, lesionesIds, operadorTratamientos, tratamientosIds, form.getPaciente(), form.getFechas()));
			uiModel.addAttribute("busquedaRealizada", true);
		} 
		
		uiModel.addAttribute("form", form);
		populateEditFormConsulta(uiModel);
		
		return "buscador/consulta";
	}	
	
	private List<Long> transformStringToLongList(String string) {
		if(StringUtils.isEmpty(string)) {
			return Collections.emptyList();
		}
		
		return Lists.transform(Arrays.asList(StringUtils.delimitedListToStringArray(string.trim(), " ")), Longs.stringConverter());
	}
	
	private void populateEditForm(Model uiModel, Paciente paciente) {
		uiModel.addAttribute("paciente", paciente);
		uiModel.addAttribute("coberturas", Arrays.asList(Cobertura.values()));
		uiModel.addAttribute("ocupaciones", Arrays.asList(Ocupacion.values()));
		uiModel.addAttribute("sexos", Arrays.asList(Sexo.values()));
	}
	
	private void populateEditFormConsulta(Model uiModel) {
		uiModel.addAttribute("coberturas", Arrays.asList(Cobertura.values()));
		uiModel.addAttribute("sexos", Arrays.asList(Sexo.values()));
		uiModel.addAttribute("comparaciones", Arrays.asList(Comparacion.values()));
	}
}
