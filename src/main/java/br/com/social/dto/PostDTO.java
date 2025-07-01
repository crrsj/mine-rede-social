package br.com.social.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private String autorNome;
    private Integer curtidas;
    private List<ComentarioDTO> comentarios;
}
