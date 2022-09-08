package ar.com.estudiocs.dao;

import java.util.List;

import ar.com.estudiocs.entities.Polizas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPolizasDao extends JpaRepository<Polizas, Long> {
    @Query("select c from Polizas c where c.descrip like ?1")
    public List<Polizas> findByDescrip(String name);
    
    Page<Polizas> findAll(Pageable pageable);
}
