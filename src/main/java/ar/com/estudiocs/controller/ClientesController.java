package ar.com.estudiocs.controller;

import ar.com.estudiocs.entities.Clientes;
import ar.com.estudiocs.entities.Tiposdoc;
import ar.com.estudiocs.services.IClientesService;
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

import java.util.List;

@Controller
public class ClientesController {

    @Autowired
    IClientesService entityService;
    @Autowired
    ITiposdocService tiposdocService;

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Clientes> centroPage = entityService.findAll(pageable);
        PageWrapper<Clientes> page = new PageWrapper<Clientes>(centroPage, "/clientes");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Clientes());
        return "../clientes/index";
    }

    @RequestMapping("clientes/refresh")
    public String refresh() {
        return "redirect:/clientes";
    }

    @RequestMapping(value = "clientes/search", method = RequestMethod.POST)
    public String search(Model model, Clientes entity) {
        if (entity.getApellido().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getApellido()));
        model.addAttribute("entity", new Clientes());
        model.addAttribute("page", null);
        return "../clientes/index";
    }

    @RequestMapping("/clientes/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Clientes());
        List<Tiposdoc> list = tiposdocService.getAll();
        model.addAttribute("tiposdoc", list);
        return "../clientes/edit";
    }

    @RequestMapping("/clientes/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        List<Tiposdoc> list = tiposdocService.getAll();
        model.addAttribute("tiposdoc", list);
        model.addAttribute("entity", entityService.get(id));
        return "../clientes/edit";
    }

    @RequestMapping(value = "clientes", method = RequestMethod.POST)
    public String save(Model model, @Validated Clientes entity) {
        String errores = "";
        if (entity.getNrodoc().equals("")) errores += "Ingrese su numero de documento";
        if (entity.getApellido().equals("")) errores += "Ingrese su apellido";
        if (entity.getNombre().equals("")) errores += "Ingrese su nombre";
        if (entity.getDirec().equals("")) errores += "Ingrese su direccion";
        if (entity.getEmail().equals("")) errores += "Ingrese su email";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Tiposdoc> list = tiposdocService.getAll();
            model.addAttribute("tiposdoc", list);
            return "../clientes/edit";
        }

        entityService.save(entity);
        return "redirect:/clientes";
    }

    @RequestMapping("clientes/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Clientes entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/clientes/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Clientes> centroPage = entityService.findAll(pageable);
            PageWrapper<Clientes> page = new PageWrapper<Clientes>(centroPage, "/clientes");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Clientes());
            return "../clientes/index";
        }
    }
    
}