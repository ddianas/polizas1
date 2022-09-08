package ar.com.estudiocs.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.estudiocs.dao.IClientesDao;
import ar.com.estudiocs.entities.Clientes;
import ar.com.estudiocs.services.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientesService implements IClientesService {

    @Autowired
    private IClientesDao entityDao;

    public List<Clientes> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "apellido"));
    }

    public Page<Clientes> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "apellido")));
    }

    public List<Clientes> findByDescrip(String apellido) {
        return entityDao.findByDescrip("%" + apellido + "%");
    }

    public Clientes get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Clientes entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Clientes entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
