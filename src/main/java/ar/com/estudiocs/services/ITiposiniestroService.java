package ar.com.estudiocs.services;

import ar.com.estudiocs.entities.Tiposiniestro;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITiposiniestroService {
    List<Tiposiniestro> getAll();
    Page<Tiposiniestro> findAll(Pageable pageable);
    List<Tiposiniestro> findByDescrip(String descrip);
    Tiposiniestro get(Integer id);
    void save(Tiposiniestro entity);
    String delete(Tiposiniestro entity);
}
