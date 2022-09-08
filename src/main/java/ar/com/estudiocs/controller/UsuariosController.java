package ar.com.estudiocs.controller;


import java.util.List;

import ar.com.estudiocs.entities.Roles;
import ar.com.estudiocs.entities.Usuarios;
import ar.com.estudiocs.services.IRolesService;
import ar.com.estudiocs.services.IUsuariosService;
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
public class UsuariosController {

    @Autowired
    IUsuariosService entityService;
    @Autowired
    IRolesService rolService;
   
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)    
    public String list(Model model, Pageable pageable) {        
        Page<Usuarios> centroPage = entityService.findAll(pageable);
        PageWrapper<Usuarios> page = new PageWrapper<Usuarios>(centroPage, "/usuarios");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Usuarios());
        return "../usuarios/index";
    }

    @RequestMapping("/usuarios/refresh")
    public String refresh() {
        return "redirect:/usuarios";
    }

    @RequestMapping(value = "/usuarios/search", method = RequestMethod.POST)
    public String search(Model model, Usuarios entity) {
        if (entity.getUsername().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getUsername()));
        model.addAttribute("entity", new Usuarios());
        model.addAttribute("page", null); 
        return "../usuarios/index";
    }

    @RequestMapping("/usuarios/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Usuarios());
        List<Roles> list = rolService.getAll();
        model.addAttribute("roles", list);
        return "../usuarios/edit";
    }

    @RequestMapping("/usuarios/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Roles> list = rolService.getAll();
        model.addAttribute("roles", list);
        return "../usuarios/edit";
    }

    @RequestMapping(value = "usuarios", method = RequestMethod.POST)
    public String save(Model model, @Validated Usuarios entity) {
        String errores = "";
        if (entity.getUsername().equals("")) errores += "Usuario Incorrecto ";
        if (entity.getPassorig().equals("")) errores += "Password Incorrecto ";
        
        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Roles> list = rolService.getAll();
            model.addAttribute("roles", list);
            return "../usuarios/edit";
        }

        entityService.save(entity);
        return "redirect:/usuarios";
    }

    @RequestMapping("usuarios/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Usuarios entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/usuarios/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Usuarios> centroPage = entityService.findAll(pageable);
            PageWrapper<Usuarios> page = new PageWrapper<Usuarios>(centroPage, "/usuarios");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Usuarios());
            return "../usuarios/index";
        }
    }   
    
}