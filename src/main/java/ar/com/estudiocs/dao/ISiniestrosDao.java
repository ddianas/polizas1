package ar.com.estudiocs.dao;

import java.util.List;

import ar.com.estudiocs.entities.Siniestros;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISiniestrosDao extends JpaRepository<Siniestros, Long> {
    @Query("select c from Siniestros c where c.id like ?1")
    public List<Siniestros> findByDescrip(String name);
    
    Page<Siniestros> findAll(Pageable pageable);
}
