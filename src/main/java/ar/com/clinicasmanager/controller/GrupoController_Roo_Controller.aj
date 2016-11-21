// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import ar.com.clinicasmanager.dao.GrupoDAO;
import ar.com.clinicasmanager.entity.Grupo;
import ar.com.clinicasmanager.entity.enums.PermisosUsuario;

privileged aspect GrupoController_Roo_Controller {
    
    @Autowired
    GrupoDAO GrupoController.grupoDAO;
    
        
    @RequestMapping(params = "form", produces = "text/html")
    public String GrupoController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Grupo());
        return "grupos/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String GrupoController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("grupo", grupoDAO.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "grupos/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String GrupoController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("grupoes", grupoDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) grupoDAO.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("grupoes", grupoDAO.findAll());
        }
        return "grupos/list";
    }
    
        
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String GrupoController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, grupoDAO.findOne(id));
        return "grupos/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String GrupoController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Grupo grupo = grupoDAO.findOne(id);
        grupoDAO.delete(grupo);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/grupos";
    }
    
    void GrupoController.populateEditForm(Model uiModel, Grupo grupo) {
        uiModel.addAttribute("grupo", grupo);
        uiModel.addAttribute("permisosusuarios", Arrays.asList(PermisosUsuario.values()));
    }
    
    String GrupoController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}