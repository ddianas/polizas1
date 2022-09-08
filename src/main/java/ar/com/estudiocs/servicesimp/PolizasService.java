package ar.com.estudiocs.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.estudiocs.dao.IPolizasDao;
import ar.com.estudiocs.entities.Polizas;
import ar.com.estudiocs.services.IPolizasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PolizasService implements IPolizasService {

    @Autowired
    private IPolizasDao entityDao;

    public List<Polizas> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Polizas> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Polizas> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Polizas get(Long id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Polizas entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Polizas entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
