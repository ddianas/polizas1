package ar.com.estudiocs.controllerrest;

import ar.com.estudiocs.entities.Usuarios;
import ar.com.estudiocs.services.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuariosRestController {

    @Autowired
    IUsuariosService entityService;
    
    @GetMapping(path = "/usuariosrest")
    public List<Usuarios> getAll() {
        return entityService.getAll();
    }

    @GetMapping(path = "/usuariosrest/{id}")
    public Usuarios get(@PathVariable Integer id) {
        return entityService.get(id);
    }

    @GetMapping(path = "/usuariosrest/search/{expresion}")
    public List<Usuarios> getListRoles(@PathVariable String expresion) {
        return entityService.findByDescrip(expresion);
    }

    @PostMapping(value = "/usuariosrest", consumes = "application/json", produces = "application/json")
    public Usuarios save(@RequestBody Usuarios entity) {
        entityService.save(entity);
        return entity;
    }

    @GetMapping(path = "/usuariosrest/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Usuarios entity = null;
         try {
            entity = entityService.get(id);
            entityService.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

}
