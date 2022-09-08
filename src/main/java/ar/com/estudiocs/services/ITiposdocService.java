package ar.com.estudiocs.services;

import ar.com.estudiocs.entities.Tiposdoc;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITiposdocService {
    List<Tiposdoc> getAll();
    Page<Tiposdoc> findAll(Pageable pageable);
    List<Tiposdoc> findByDescrip(String descrip);
    Tiposdoc get(Integer id);
    void save(Tiposdoc entity);
    String delete(Tiposdoc entity);
}
