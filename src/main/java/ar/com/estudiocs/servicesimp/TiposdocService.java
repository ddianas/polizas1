package ar.com.estudiocs.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.estudiocs.dao.ITiposdocDao;
import ar.com.estudiocs.entities.Tiposdoc;
import ar.com.estudiocs.services.ITiposdocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TiposdocService implements ITiposdocService {

    @Autowired
    private ITiposdocDao entityDao;

    public List<Tiposdoc> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Tiposdoc> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Tiposdoc> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Tiposdoc get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Tiposdoc entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Tiposdoc entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
