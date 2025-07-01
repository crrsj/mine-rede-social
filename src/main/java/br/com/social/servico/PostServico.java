package br.com.social.servico;

import br.com.social.dto.ComentarioDTO;
import br.com.social.dto.PostDTO;
import br.com.social.repositorio.PostRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostServico {
    PostDTO criarPost(PostDTO postDTO, Long usuarioId);
    List<PostDTO> listarTodosPosts();
    PostDTO buscarPostPorId(Long id);
    PostDTO atualizarPost(Long postId, PostDTO postDTO, Long usuarioId);
    void deletarPost(Long postId, Long usuarioId);
    PostDTO adicionarComentario(Long postId, ComentarioDTO comentarioDTO, Long usuarioId);
    PostDTO curtirPost(Long postId, Long usuarioId);



}
