package ar.com.estudiocs.controller;

import ar.com.estudiocs.entities.Cobertura;
import ar.com.estudiocs.services.ICoberturaService;
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
public class CoberturaController {

    @Autowired
    ICoberturaService entityService;

    @RequestMapping(value = "/cobertura", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Cobertura> centroPage = entityService.findAll(pageable);
        PageWrapper<Cobertura> page = new PageWrapper<Cobertura>(centroPage, "/cobertura");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Cobertura());
        return "../cobertura/index";
    }

    @RequestMapping("cobertura/refresh")
    public String refresh() {
        return "redirect:/cobertura";
    }

    @RequestMapping(value = "cobertura/search", method = RequestMethod.POST)
    public String search(Model model, Cobertura entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Cobertura());
        model.addAttribute("page", null);
        return "../cobertura/index";
    }

    @RequestMapping("cobertura/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Cobertura());
        return "../cobertura/edit";
    }

    @RequestMapping("cobertura/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../cobertura/edit";
    }

    @RequestMapping(value = "cobertura", method = RequestMethod.POST)
    public String save(Model model, @Validated Cobertura entity) {
        String errores = "";
        if (entity.getDescrip().equals("")) errores += "Ingrese la descripcion";
        if (entity.getMonto() == null) errores += "Ingrese el monto";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            return "../cobertura/edit";      
        }

        entityService.save(entity);
        return "redirect:/cobertura";
    }

    @RequestMapping("cobertura/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Cobertura entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/cobertura/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Cobertura> centroPage = entityService.findAll(pageable);
            PageWrapper<Cobertura> page = new PageWrapper<Cobertura>(centroPage, "/cobertura");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Cobertura());
            return "../cobertura/index";
        }
    }
    
}