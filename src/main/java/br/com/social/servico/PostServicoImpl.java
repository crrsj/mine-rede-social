package br.com.social.servico;

import br.com.social.entidade.Post;
import br.com.social.dto.ComentarioDTO;
import br.com.social.dto.PostDTO;
import br.com.social.entidade.Comentario;
import br.com.social.entidade.Usuario;
import br.com.social.excessoes.AcessoNegadoException;
import br.com.social.excessoes.RecursoNaoEncontradoException;
import br.com.social.repositorio.ComentarioRepositorio;
import br.com.social.repositorio.PostRepositorio;
import br.com.social.repositorio.UsuarioRepositorio;
import br.com.social.util.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServicoImpl implements PostServico{

    private final PostRepositorio postRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ComentarioRepositorio comentarioRepositorio;
    private final MapperUtil mapperUtil;

    @Override
    @Transactional
    public PostDTO criarPost(PostDTO postDTO, Long usuarioId) {
        Usuario autor = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        Post post = new Post();
        post.setConteudo(postDTO.getConteudo());
        post.setAutor(autor);
        post.setCurtidas(0);

        Post postSalvo = postRepositorio.save(post);
        return mapperUtil.mapPostToDTO(postSalvo);
    }

    @Override
    @Transactional
    public List<PostDTO> listarTodosPosts() {
        return postRepositorio.findAllOrderByDataCriacaoDesc().stream()
                .map(mapperUtil::mapPostToDTO)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public PostDTO buscarPostPorId(Long id) {
        Post post = postRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado"));
        return mapperUtil.mapPostToDTO(post);
    }

    @Override
    @Transactional
    public PostDTO atualizarPost(Long postId, PostDTO postDTO, Long usuarioId) {
        Post post = postRepositorio.findById(postId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado"));

        if (!post.getAutor().getId().equals(usuarioId)) {
            throw new AcessoNegadoException("Você não tem permissão para editar este post");
        }

        post.setConteudo(postDTO.getConteudo());
        Post postAtualizado = postRepositorio.save(post);
        return mapperUtil.mapPostToDTO(postAtualizado);

    }

    @Override
    @Transactional
    public void deletarPost(Long postId, Long usuarioId) {
        Post post = postRepositorio.findById(postId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado"));

        if (!post.getAutor().getId().equals(usuarioId)) {
            throw new AcessoNegadoException("Você não tem permissão para excluir este post");
        }

        postRepositorio.delete(post);
    }

    @Override
    @Transactional
    public PostDTO adicionarComentario(Long postId, ComentarioDTO comentarioDTO, Long usuarioId) {
        Post post = postRepositorio.findById(postId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado"));

        Usuario autor = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        Comentario comentario = new Comentario();
        comentario.setConteudo(comentarioDTO.getConteudo());
        comentario.setAutor(autor);
        comentario.setPost(post);

        post.adicionarComentario(comentario);
        comentarioRepositorio.save(comentario);

        return mapperUtil.mapPostToDTO(postRepositorio.save(post));
    }

    @Override
    @Transactional
    public PostDTO curtirPost(Long postId, Long usuarioId) {
        Post post = postRepositorio.findById(postId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado"));

        post.setCurtidas(post.getCurtidas() + 1);
        Post postAtualizado = postRepositorio.save(post);
        return mapperUtil.mapPostToDTO(postAtualizado);
    }
    public void deletarComentario(Long comentarioId, Long usuarioId) {
        if (!comentarioRepositorio.existsByIdAndAutorId(comentarioId, usuarioId)) {
            throw new AcessoNegadoException("Você não tem permissão para excluir este comentário");
        }
        comentarioRepositorio.deleteById(comentarioId);
    }

    public List<ComentarioDTO> getComentariosPorPost(Long postId) {
        return comentarioRepositorio.findByPostIdOrderByDataCriacaoDesc(postId)
                .stream()
                .map(mapperUtil::mapComentarioToDTO)
                .collect(Collectors.toList());
    }
}
