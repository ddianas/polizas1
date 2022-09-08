package ar.com.estudiocs.controller;

import ar.com.estudiocs.entities.Roles;
import ar.com.estudiocs.services.IRolesService;
import ar.com.estudiocs.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RolesController {

    @Autowired
    IRolesService entityService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Roles> centroPage = entityService.findAll(pageable);
        PageWrapper<Roles> page = new PageWrapper<Roles>(centroPage, "/roles");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Roles());
        return "../roles/index";
    }

    @RequestMapping("roles/refresh")
    public String refresh() {
        return "redirect:/roles";
    }

    @RequestMapping(value = "roles/search", method = RequestMethod.POST)
    public String search(Model model, Roles entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Roles());
        model.addAttribute("page", null);
        return "../roles/index";
    }

    @RequestMapping("roles/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Roles());
        return "../roles/edit";
    }

    @RequestMapping("roles/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../roles/edit";
    }

    @RequestMapping(value = "roles", method = RequestMethod.POST)
    public String save(Model model, @Validated Roles entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../roles/edit";      
        }

        entityService.save(entity);
        return "redirect:/roles";
    }

    @RequestMapping("roles/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Roles entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/roles/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Roles> centroPage = entityService.findAll(pageable);
            PageWrapper<Roles> page = new PageWrapper<Roles>(centroPage, "/roles");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Roles());
            return "../roles/index";
        }
    }
    
}