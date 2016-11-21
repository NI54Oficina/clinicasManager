package ar.com.clinicasmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.clinicasmanager.entity.Usuario;

@RequestMapping("/usuarios")
@Controller
@RooWebScaffold(path = "usuarios", formBackingObject = Usuario.class)
public class UsuarioController {

	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(Usuario usuario, Model uiModel, HttpServletRequest httpServletRequest) {
        uiModel.asMap().clear();
        
        setUserPassword(usuario);        
        usuarioDAO.save(usuario);
        
        return "redirect:/usuarios/" + encodeUrlPathSegment(usuario.getId().toString(), httpServletRequest);
    }
	
	private void setUserPassword(Usuario usuario){
        if(usuario.getUsuarioGoogle()){
        	usuario.setPassword(".");
        }
        else{
            if(!usuario.getPassword().isEmpty()){
            	String hash = passwordEncoder.encodePassword(usuario.getPassword(), usuario.getUsername());
        		usuario.setPassword(hash);
            }
            else{
            	usuario.setPassword(usuarioDAO.findOne(usuario.getId()).getPassword());
            }
        }
	}
}
