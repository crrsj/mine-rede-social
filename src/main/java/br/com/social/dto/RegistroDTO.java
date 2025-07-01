package br.com.social.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistroDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}
