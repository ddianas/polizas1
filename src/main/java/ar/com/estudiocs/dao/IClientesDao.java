package ar.com.estudiocs.dao;

import java.util.List;

import ar.com.estudiocs.entities.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientesDao extends JpaRepository<Clientes, Integer> {
    @Query("select c from Clientes c where c.apellido like ?1")
    public List<Clientes> findByDescrip(String name);
    
    Page<Clientes> findAll(Pageable pageable);
}
