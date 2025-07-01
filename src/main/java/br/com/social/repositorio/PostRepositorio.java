package br.com.social.repositorio;

import br.com.social.entidade.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepositorio extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p ORDER BY p.dataCriacao DESC")
    List<Post> findAllOrderByDataCriacaoDesc();

    List<Post> findByAutorId(Long usuarioId);
}
