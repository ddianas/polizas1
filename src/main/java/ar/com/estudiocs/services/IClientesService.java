package ar.com.estudiocs.services;

import ar.com.estudiocs.entities.Clientes;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientesService {
    List<Clientes> getAll();
    Page<Clientes> findAll(Pageable pageable);
    List<Clientes> findByDescrip(String apellido);
    Clientes get(Integer id);
    void save(Clientes entity);
    String delete(Clientes entity);
}
