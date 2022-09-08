package ar.com.estudiocs.controller;


import ar.com.estudiocs.entities.Clientes;
import ar.com.estudiocs.entities.Cobertura;
import ar.com.estudiocs.entities.Polizas;
import ar.com.estudiocs.services.IClientesService;
import ar.com.estudiocs.services.ICoberturaService;
import ar.com.estudiocs.services.IPolizasService;
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

import java.util.List;

@Controller
public class PolizasController {

    @Autowired
    IPolizasService entityService;
    @Autowired
    IClientesService clientesService;
    @Autowired
    ICoberturaService coberturaService;

    @RequestMapping(value = "/polizas", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Polizas> centroPage = entityService.findAll(pageable);
        PageWrapper<Polizas> page = new PageWrapper<Polizas>(centroPage, "/polizas");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Polizas());
        return "../polizas/index";
    }

    @RequestMapping("polizas/refresh")
    public String refresh() {
        return "redirect:/polizas";
    }

    @RequestMapping(value = "polizas/search", method = RequestMethod.POST)
    public String search(Model model, Polizas entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Polizas());
        model.addAttribute("page", null);
        return "../polizas/index";
    }

    @RequestMapping("polizas/create/{id}")
    public String create(@PathVariable Long id, Model model) {
        model.addAttribute("entity", new Polizas());
        List<Clientes> list = clientesService.getAll();
        List<Cobertura> listcob = coberturaService.getAll();
        model.addAttribute("clientes", list);
        model.addAttribute("cobertura", listcob);
        return "../polizas/edit";
    }

    @RequestMapping("polizas/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        List<Clientes> list = clientesService.getAll();
        List<Cobertura> listcob = coberturaService.getAll();
        model.addAttribute("clientes", list);
        model.addAttribute("cobertura", listcob);
        model.addAttribute("entity", entityService.get(id));
        return "../polizas/edit";
    }

    @RequestMapping(value = "polizas", method = RequestMethod.POST)
    public String save(Model model, @Validated Polizas entity) {
        String errores = "";
        if (entity.getDescrip().equals("")) errores += "Ingrese la descripcion";
        if (entity.getNropoliza() == null) errores += "Ingrese el numero de poliza";
        if (entity.getFechainicio() == null) errores += "Ingrese la fecha de inicio";
        if (entity.getFechafin() == null) errores += "Ingrese la fecha de fin";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Clientes> list = clientesService.getAll();
            model.addAttribute("clientes", list);
            List<Cobertura> listcob = coberturaService.getAll();
            model.addAttribute("cobertura", listcob);
            return "../polizas/edit";      
        }
        entityService.save(entity);
        return "redirect:/polizas";
    }

    @RequestMapping("polizas/delete/{id}")
    public String delete(@PathVariable Long id, Model model, Pageable pageable) {
        try {
            Polizas entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/polizas/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Polizas> centroPage = entityService.findAll(pageable);
            PageWrapper<Polizas> page = new PageWrapper<Polizas>(centroPage, "/polizas");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Polizas());
            return "../polizas/index";
        }
    }
    
}