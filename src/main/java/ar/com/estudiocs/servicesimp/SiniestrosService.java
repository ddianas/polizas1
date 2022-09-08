package ar.com.estudiocs.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.estudiocs.dao.ISiniestrosDao;
import ar.com.estudiocs.entities.Siniestros;
import ar.com.estudiocs.services.ISiniestrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SiniestrosService implements ISiniestrosService {

    @Autowired
    private ISiniestrosDao entityDao;

    public List<Siniestros> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Page<Siniestros> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id")));
    }

    public List<Siniestros> findByDescrip(Long id) {
        return entityDao.findByDescrip("%" + id + "%");
    }

    public Siniestros get(Long id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Siniestros entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Siniestros entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
