package ar.com.estudiocs.services;

import ar.com.estudiocs.entities.Polizas;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPolizasService {
    List<Polizas> getAll();
    Page<Polizas> findAll(Pageable pageable);
    List<Polizas> findByDescrip(String descrip);
    Polizas get(Long id);
    void save(Polizas entity);
    String delete(Polizas entity);
}
