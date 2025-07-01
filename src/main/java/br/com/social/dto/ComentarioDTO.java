package br.com.social.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioDTO {
    private Long id;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private String autorNome;
}
