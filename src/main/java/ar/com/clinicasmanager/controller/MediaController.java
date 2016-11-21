package ar.com.clinicasmanager.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import ar.com.clinicasmanager.dao.MediaElementDAO;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.MediaElement;
import ar.com.clinicasmanager.service.ConsultaService;

@Controller
public class MediaController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private MediaElementDAO mediaElementDAO;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(value = "/consulta/{id}/media", params = "form", produces = "text/html")
	public String mediaForm(@PathVariable("id") Long id, Model uiModel){	
		uiModel.addAttribute("consultaId", id);
		
		return "consultas/mediaForm";
	}
	
	
	@RequestMapping(value = "/consulta/{idConsulta}/fileUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MediaElement> fileUpload(
			@PathVariable("idConsulta") Long idConsulta, 
			@RequestParam("file") List<MultipartFile> files, 
			@RequestParam("description") String description, 
			Model uiModel) throws IOException{
		
		Consulta consulta = consultaService.findOne(idConsulta);
		
		List<MediaElement> mediaElements = new ArrayList<MediaElement>();
		for (MultipartFile multipartFile : files) {
			MediaElement mediaElement = new MediaElement();
			mediaElement.setDescripcion(description);
			mediaElement.setMedia(multipartFile.getBytes());
			
			consulta.addMedia(mediaElement);
			mediaElements.add(mediaElementDAO.save(mediaElement));
		}
		
		return mediaElements;
	}
	
	@RequestMapping(value = "/consulta/{id}/media", method = RequestMethod.GET, produces = "text/html")
	public String list(@PathVariable("id") Long id, Model uiModel){
		Consulta consulta = consultaService.findOne(id);
		
		uiModel.addAttribute("consulta", consulta);
		
		return "consultas/mediaList";
	}
	
	@RequestMapping(value = "/media/{idMedia}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("idMedia") Long idMedia,  Model uiModel){
		mediaElementDAO.delete(idMedia);
	}
	
	@RequestMapping(value = "/media/{idMedia}")
	public void getMedia(HttpServletResponse response, @PathVariable("idMedia") Long id) throws IOException {

		response.setContentType("image/jpeg");
		byte[] buffer = mediaElementDAO.findOne(id).getMedia();
		InputStream in1 = new ByteArrayInputStream(buffer);
		IOUtils.copy(in1, response.getOutputStream());
	}
}
