package br.com.social.controle;

import br.com.social.dto.ComentarioDTO;
import br.com.social.dto.PostDTO;
import br.com.social.servico.PostServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostControle {
    private final PostServico postServico;

    @PostMapping
    @Operation(summary = "Endpoint responsável por postagens de usuários.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<PostDTO> criarPost(
            @Valid @RequestBody PostDTO postDTO,
            @RequestHeader("X-Usuario-Id") Long usuarioId) {

        PostDTO novoPost = postServico.criarPost(postDTO, usuarioId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoPost.getId())
                .toUri();

        return ResponseEntity.created(location).body(novoPost);
    }

    // Listar todos os posts (feed)
    @GetMapping
    @Operation(summary = "Endpoint responsável por listar postagens.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<PostDTO>> listarTodosPosts() {
        return ResponseEntity.ok(postServico.listarTodosPosts());
    }

    // Buscar post por ID
    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsável por listar postagem pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<PostDTO> buscarPostPorId(@PathVariable Long id) {
        return ResponseEntity.ok(postServico.buscarPostPorId(id));
    }

    // Atualizar post
    @PutMapping("/{id}")
    @Operation(summary = "Endpoint responsável por atualizar postagens.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<PostDTO> atualizarPost(
            @PathVariable Long id,
            @Valid @RequestBody PostDTO postDTO,
            @RequestHeader("X-Usuario-Id") Long usuarioId) {

        return ResponseEntity.ok(postServico.atualizarPost(id, postDTO, usuarioId));
    }

    // Deletar post
    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint responsável por deletar postagens.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Void> deletarPost(
            @PathVariable Long id,
            @RequestHeader("X-Usuario-Id") Long usuarioId) {

        postServico.deletarPost(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    // Adicionar comentário
    @PostMapping("/{postId}/comentarios")
    @Operation(summary = "Endpoint responsável por postar comentarios.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<PostDTO> adicionarComentario(
            @PathVariable Long postId,
            @Valid @RequestBody ComentarioDTO comentarioDTO,
            @RequestHeader("X-Usuario-Id") Long usuarioId) {

        return ResponseEntity.ok(
                postServico.adicionarComentario(postId, comentarioDTO, usuarioId)
        );
    }

    // Curtir post
    @PostMapping("/{id}/curtir")
    @Operation(summary = "Endpoint responsável por curtidas.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<PostDTO> curtirPost(
            @PathVariable Long id,
            @RequestHeader("X-Usuario-Id") Long usuarioId) {

        return ResponseEntity.ok(postServico.curtirPost(id, usuarioId));
    }
}
