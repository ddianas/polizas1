package ar.com.estudiocs.controllerrest;

import java.util.List;

import ar.com.estudiocs.entities.Roles;
import ar.com.estudiocs.services.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RolesRestController {

    @Autowired
    IRolesService entityService;
    
    @GetMapping(path = "/rolesrest")
    public List<Roles> getAll() {
        return entityService.getAll();
    }

    @GetMapping(path = "/rolesrest/{id}")
    public Roles get(@PathVariable Integer id) {
        return entityService.get(id);
    }

    @GetMapping(path = "/rolesrest/search/{expresion}")
    public List<Roles> getListRoles(@PathVariable String expresion) {
        return entityService.findByDescrip(expresion);
    }

    @PostMapping(value = "/rolesrest", consumes = "application/json", produces = "application/json")
    public Roles save(@RequestBody Roles entity) {
        entityService.save(entity);
        return entity;
    }

    @GetMapping(path = "/rolesrest/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Roles entity = null;
         try {
            entity = entityService.get(id);
            entityService.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

}
