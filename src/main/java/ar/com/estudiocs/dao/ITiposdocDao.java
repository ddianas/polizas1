package ar.com.estudiocs.dao;

import java.util.List;

import ar.com.estudiocs.entities.Tiposdoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITiposdocDao extends JpaRepository<Tiposdoc, Integer> {
    @Query("select c from Tiposdoc c where c.descrip like ?1")
    public List<Tiposdoc> findByDescrip(String name);
    
    Page<Tiposdoc> findAll(Pageable pageable);
}
