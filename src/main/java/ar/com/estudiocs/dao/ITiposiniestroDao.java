package ar.com.estudiocs.dao;

import java.util.List;

import ar.com.estudiocs.entities.Tiposiniestro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITiposiniestroDao extends JpaRepository<Tiposiniestro, Integer> {
    @Query("select c from Tiposiniestro c where c.descrip like ?1")
    public List<Tiposiniestro> findByDescrip(String name);
    
    Page<Tiposiniestro> findAll(Pageable pageable);
}
