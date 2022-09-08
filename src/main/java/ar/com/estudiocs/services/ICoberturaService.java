package ar.com.estudiocs.services;

import ar.com.estudiocs.entities.Cobertura;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICoberturaService {
    List<Cobertura> getAll();
    Page<Cobertura> findAll(Pageable pageable);
    List<Cobertura> findByDescrip(String descrip);
    Cobertura get(Integer id);
    void save(Cobertura entity);
    String delete(Cobertura entity);
}
