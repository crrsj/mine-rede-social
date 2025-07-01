package br.com.social.controle;

import br.com.social.dto.LoginDTO;
import br.com.social.dto.RegistroDTO;
import br.com.social.servico.UsuarioServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/social")
@RequiredArgsConstructor
public class LoginControle {
    private final UsuarioServico servico;

    @PostMapping("/login")
    @Operation(summary = "Endpoint responsável por acessar a rede.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        if (servico.login(loginDTO)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login bem-sucedido!");
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email ou senha incorretos");
            response.put("status", "error");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/registro")
    @Operation(summary = "Endpoint responsável por registrar usuarios na rede.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Map<String, String>> registrar(@RequestBody RegistroDTO registroDTO) {
        try {
            servico.registrar(registroDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuário registrado com sucesso!");
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }

}





