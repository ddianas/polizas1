package ar.com.estudiocs.controller;

import ar.com.estudiocs.entities.Tiposdoc;
import ar.com.estudiocs.services.ITiposdocService;
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
public class TiposdocController {

    @Autowired
    ITiposdocService entityService;

    @RequestMapping(value = "/tiposdoc", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Tiposdoc> centroPage = entityService.findAll(pageable);
        PageWrapper<Tiposdoc> page = new PageWrapper<Tiposdoc>(centroPage, "/tiposdoc");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Tiposdoc());
        return "../tiposdoc/index";
    }

    @RequestMapping("tiposdoc/refresh")
    public String refresh() {
        return "redirect:/tiposdoc";
    }

    @RequestMapping(value = "tiposdoc/search", method = RequestMethod.POST)
    public String search(Model model, Tiposdoc entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Tiposdoc());
        model.addAttribute("page", null);
        return "../tiposdoc/index";
    }

    @RequestMapping("tiposdoc/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Tiposdoc());
        return "../tiposdoc/edit";
    }

    @RequestMapping("tiposdoc/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../tiposdoc/edit";
    }

    @RequestMapping(value = "tiposdoc", method = RequestMethod.POST)
    public String save(Model model, @Validated Tiposdoc entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../tiposdoc/edit";
        }
        if (entity.getCodigo().equals("")) {
            model.addAttribute("message", "Código Incorrecto");
            model.addAttribute("entity", entity);
            return "../tiposdoc/edit";
        }

        entityService.save(entity);
        return "redirect:/tiposdoc";
    }

    @RequestMapping("tiposdoc/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Tiposdoc entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/tiposdoc/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Tiposdoc> centroPage = entityService.findAll(pageable);
            PageWrapper<Tiposdoc> page = new PageWrapper<Tiposdoc>(centroPage, "/tiposdoc");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Tiposdoc());
            return "../tiposdoc/index";
        }
    }
    
}