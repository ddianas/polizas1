package ar.com.estudiocs.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.estudiocs.dao.ICoberturaDao;
import ar.com.estudiocs.entities.Cobertura;
import ar.com.estudiocs.services.ICoberturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CoberturaService implements ICoberturaService {

    @Autowired
    private ICoberturaDao entityDao;

    public List<Cobertura> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Cobertura> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Cobertura> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Cobertura get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Cobertura entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Cobertura entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
