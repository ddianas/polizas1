package ar.com.estudiocs.services;


import ar.com.estudiocs.entities.Siniestros;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISiniestrosService {
    List<Siniestros> getAll();
    Page<Siniestros> findAll(Pageable pageable);
    List<Siniestros> findByDescrip(Long id);
    Siniestros get(Long id);
    void save(Siniestros entity);
    String delete(Siniestros entity);
}
