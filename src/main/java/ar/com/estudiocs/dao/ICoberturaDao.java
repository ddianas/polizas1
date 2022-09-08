package ar.com.estudiocs.dao;

import java.util.List;

import ar.com.estudiocs.entities.Cobertura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoberturaDao extends JpaRepository<Cobertura, Integer> {
    @Query("select c from Cobertura c where c.descrip like ?1")
    public List<Cobertura> findByDescrip(String name);
    
    Page<Cobertura> findAll(Pageable pageable);
}
