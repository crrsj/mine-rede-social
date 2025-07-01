package br.com.social.servico;

import br.com.social.dto.LoginDTO;
import br.com.social.dto.RegistroDTO;
import br.com.social.entidade.Usuario;
import br.com.social.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final BCryptPasswordEncoder  passwordEncoder;

    public Usuario registrar(RegistroDTO registroDTO) {
        if (usuarioRepositorio.findByEmail(registroDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(registroDTO.getNome());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(registroDTO.getSenha()));

        return usuarioRepositorio.save(usuario);
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<Usuario> usuario = usuarioRepositorio.findByEmail(loginDTO.getEmail());
        return usuario.isPresent() &&
                passwordEncoder.matches(loginDTO.getSenha(), usuario.get().getSenha());
    }
}
