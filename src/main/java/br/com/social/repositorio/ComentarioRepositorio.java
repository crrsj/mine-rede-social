package br.com.social.repositorio;

import br.com.social.entidade.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepositorio extends JpaRepository<Comentario,Long> {
    @Query("SELECT c FROM Comentario c WHERE c.post.id = :postId ORDER BY c.dataCriacao DESC")
    List<Comentario> findByPostIdOrderByDataCriacaoDesc(@Param("postId") Long postId);

    // Conta o número de comentários de um post
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.post.id = :postId")
    Long countByPostId(@Param("postId") Long postId);

    // Busca comentários de um usuário específico
    List<Comentario> findByAutorId(Long usuarioId);

    // Verifica se um comentário pertence a um usuário (para validação de edição/exclusão)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Comentario c WHERE c.id = :comentarioId AND c.autor.id = :usuarioId")
    boolean existsByIdAndAutorId(@Param("comentarioId") Long comentarioId,
                                 @Param("usuarioId") Long usuarioId);
}
