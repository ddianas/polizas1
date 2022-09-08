package ar.com.estudiocs.controller;

import ar.com.estudiocs.entities.Clientes;
import ar.com.estudiocs.entities.Polizas;
import ar.com.estudiocs.entities.Siniestros;
import ar.com.estudiocs.entities.Tiposiniestro;
import ar.com.estudiocs.services.IClientesService;
import ar.com.estudiocs.services.IPolizasService;
import ar.com.estudiocs.services.ISiniestrosService;
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

import java.util.List;

@Controller
public class SiniestrosController {

    @Autowired
    ISiniestrosService entityService;
    @Autowired
    IPolizasService polizasService;
    @Autowired
    IClientesService clientesService;
    @Autowired
    ITiposiniestroService tiposiniestroService;


    @RequestMapping(value = "/siniestros", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Siniestros> centroPage = entityService.findAll(pageable);
        PageWrapper<Siniestros> page = new PageWrapper<Siniestros>(centroPage, "/siniestros");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Siniestros());
        return "../siniestros/index";
    }

    @RequestMapping("siniestros/refresh")
    public String refresh() {
        return "redirect:/siniestros";
    }

    @RequestMapping(value = "siniestros/search", method = RequestMethod.POST)
    public String search(Model model, Siniestros entity) {
        if (entity.getId() == null) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getId()));
        model.addAttribute("entity", new Siniestros());
        model.addAttribute("page", null);
        return "../siniestros/index";
    }

    @RequestMapping("siniestros/create/{id}")
    public String create(@PathVariable Long id, Model model) {
        model.addAttribute("entity", new Siniestros());
        List<Clientes> list = clientesService.getAll();
        List<Tiposiniestro> listtiposin = tiposiniestroService.getAll();
        List<Polizas> listpol = polizasService.getAll();
        model.addAttribute("clientes", list);
        model.addAttribute("tiposiniestro", listtiposin);
        model.addAttribute("polizas", listpol);
        return "../siniestros/edit";
    }

    @RequestMapping("siniestros/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        List<Clientes> list = clientesService.getAll();
        List<Tiposiniestro> listtiposin = tiposiniestroService.getAll();
        List<Polizas> listpol = polizasService.getAll();
        model.addAttribute("clientes", list);
        model.addAttribute("tiposiniestro", listtiposin);
        model.addAttribute("polizas", listpol);
        model.addAttribute("entity", entityService.get(id));
        return "../siniestros/edit";
    }

    @RequestMapping(value = "siniestros", method = RequestMethod.POST)
    public String save(Model model, @Validated Siniestros entity) {
        String errores = "";
        if (entity.getNumtransaccion() == null) errores += "Ingrese el numero de transaccion";
        if (entity.getFechaocurrencia() == null) errores += "Ingrese la fecha de ocurrencia";
        if (entity.getMonto() == null) errores += "Ingrese el monto";
        if (entity.getObservacion().equals("")) errores += "Ingrese una observacion";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Clientes> list = clientesService.getAll();
            model.addAttribute("clientes", list);
            List<Tiposiniestro> listtiposin = tiposiniestroService.getAll();
            model.addAttribute("tiposiniestro", listtiposin);
            List<Polizas> listpol = polizasService.getAll();
            model.addAttribute("polizas", listpol);
            return "../siniestros/edit";
        }
        entityService.save(entity);
        return "redirect:/siniestros";
    }

    @RequestMapping("siniestros/delete/{id}")
    public String delete(@PathVariable Long id, Model model, Pageable pageable) {
        try {
            Siniestros entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/siniestros/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Siniestros> centroPage = entityService.findAll(pageable);
            PageWrapper<Siniestros> page = new PageWrapper<Siniestros>(centroPage, "/siniestros");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Siniestros());
            return "../siniestros/index";
        }
    }
}