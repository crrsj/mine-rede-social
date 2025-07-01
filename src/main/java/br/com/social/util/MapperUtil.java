package br.com.social.util;


import br.com.social.entidade.Post;
import br.com.social.dto.ComentarioDTO;
import br.com.social.dto.PostDTO;
import br.com.social.entidade.Comentario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {
    public PostDTO mapPostToDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setConteudo(post.getConteudo());
        dto.setDataCriacao(post.getDataCriacao());
        dto.setAutorNome(post.getAutor().getNome());
        dto.setCurtidas(post.getCurtidas());

        List<ComentarioDTO> comentariosDTO = post.getComentarios().stream()
                .map(this::mapComentarioToDTO)
                .sorted((c1, c2) -> c2.getDataCriacao().compareTo(c1.getDataCriacao()))
                .collect(Collectors.toList());

        dto.setComentarios(comentariosDTO);
        return dto;
    }

    public ComentarioDTO mapComentarioToDTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setConteudo(comentario.getConteudo());
        dto.setDataCriacao(comentario.getDataCriacao());
        dto.setAutorNome(comentario.getAutor().getNome());
        return dto;
    }
}