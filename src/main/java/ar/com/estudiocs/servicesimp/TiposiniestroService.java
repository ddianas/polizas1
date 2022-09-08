package ar.com.estudiocs.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.estudiocs.dao.ITiposiniestroDao;
import ar.com.estudiocs.entities.Tiposiniestro;
import ar.com.estudiocs.services.ITiposiniestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TiposiniestroService implements ITiposiniestroService {

    @Autowired
    private ITiposiniestroDao entityDao;

    public List<Tiposiniestro> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Tiposiniestro> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Tiposiniestro> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Tiposiniestro get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Tiposiniestro entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Tiposiniestro entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
