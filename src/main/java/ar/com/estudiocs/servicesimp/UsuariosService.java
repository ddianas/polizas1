package ar.com.estudiocs.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.estudiocs.dao.IUsuariosDao;
import ar.com.estudiocs.entities.Usuarios;
import ar.com.estudiocs.services.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService implements IUsuariosService {

    @Autowired
    private IUsuariosDao entityDao;

    public List<Usuarios> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    public Page<Usuarios> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "username")));
    }

    public List<Usuarios> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }


    public Usuarios get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Usuarios entity) {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entity.setPassword(entity.getPassorig());
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Usuarios entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public Usuarios findUsuario(String usuario, String pass) {
        List<Usuarios> result = entityDao.findUsuario(usuario, pass, PageRequest.of(0, 1));
        try {
            if (result == null) {
                return null;
            } else {
                return result.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

}

