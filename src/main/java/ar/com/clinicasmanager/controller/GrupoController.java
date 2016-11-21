package ar.com.clinicasmanager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.clinicasmanager.entity.Grupo;

@RequestMapping("/grupos")
@Controller
@RooWebScaffold(path = "grupos", formBackingObject = Grupo.class)
public class GrupoController {

	@Resource(name = "sessionRegistry")
	private SessionRegistryImpl sessionRegistry;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Grupo grupo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, grupo);
            return "grupos/create";
        }
        uiModel.asMap().clear();
        grupoDAO.save(grupo);
//        expireCurrentSessions();
        return "redirect:/grupos/" + encodeUrlPathSegment(grupo.getId().toString(), httpServletRequest);
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Grupo grupo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, grupo);
            return "grupos/update";
        }
        uiModel.asMap().clear();
        grupoDAO.save(grupo);
//        expireCurrentSessions();
        return "redirect:/grupos/" + encodeUrlPathSegment(grupo.getId().toString(), httpServletRequest);
    }
	
//	private void expireCurrentSessions(){
//		for (Object username : sessionRegistry.getAllPrincipals()) {
//			for (SessionInformation sessionInformation : sessionRegistry.getAllSessions(username, false)) {
//				sessionInformation.expireNow();
//			}
//		}
//	}
}
