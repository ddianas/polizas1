package ar.com.estudiocs.controller;

import ar.com.estudiocs.entities.Tiposiniestro;
import ar.com.estudiocs.services.ITiposiniestroService;
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
public class TiposiniestroController {

    @Autowired
    ITiposiniestroService entityService;

    @RequestMapping(value = "/tiposiniestro", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Tiposiniestro> centroPage = entityService.findAll(pageable);
        PageWrapper<Tiposiniestro> page = new PageWrapper<Tiposiniestro>(centroPage, "/tiposiniestro");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Tiposiniestro());
        return "../tiposiniestro/index";
    }

    @RequestMapping("tiposiniestro/refresh")
    public String refresh() {
        return "redirect:/tiposiniestro";
    }

    @RequestMapping(value = "tiposiniestro/search", method = RequestMethod.POST)
    public String search(Model model, Tiposiniestro entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Tiposiniestro());
        model.addAttribute("page", null);
        return "../tiposiniestro/index";
    }

    @RequestMapping("tiposiniestro/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Tiposiniestro());
        return "../tiposiniestro/edit";
    }

    @RequestMapping("tiposiniestro/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../tiposiniestro/edit";
    }

    @RequestMapping(value = "tiposiniestro", method = RequestMethod.POST)
    public String save(Model model, @Validated Tiposiniestro entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../tiposiniestro/edit";
        }

        entityService.save(entity);
        return "redirect:/tiposiniestro";
    }

    @RequestMapping("tiposiniestro/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Tiposiniestro entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/tiposiniestro/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Tiposiniestro> centroPage = entityService.findAll(pageable);
            PageWrapper<Tiposiniestro> page = new PageWrapper<Tiposiniestro>(centroPage, "/tiposiniestro");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Tiposiniestro());
            return "../tiposiniestro/index";
        }
    }
    
}