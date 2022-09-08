package ar.com.estudiocs.dao;

import java.util.List;

import ar.com.estudiocs.entities.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosDao extends JpaRepository<Usuarios, Integer> {
    @Query("select c from Usuarios c where c.username like ?1")
    List<Usuarios> findByDescrip(String name);

    @Query("select c from Usuarios c where c.username = ?1 and c.password = ?2")
    List<Usuarios> findUsuario(String usuario, String pass, Pageable pagesize);

    Usuarios findOneByUsername(String username);

    Page<Usuarios> findAll(Pageable pageable);
}
