package com.lucas.bffagendadortarefas.controller;


import com.lucas.bffagendadortarefas.business.UsuarioService;
import com.lucas.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.lucas.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.lucas.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.lucas.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.lucas.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.lucas.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.lucas.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuarios", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity <UsuarioDTOResponse> salvaUsuario (@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }

    @PostMapping ("/login")
    @Operation(summary = "Login Usuarios", description = "Login do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login (@RequestBody LoginRequestDTO usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);

    }

    @GetMapping
    @Operation(summary = "Buscar Dados de Usuarios por Email",
            description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail (@RequestParam("email") String email,
                                                                    @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));

    }
    @DeleteMapping ("/{email}")
    @Operation(summary = "Deletar Usuarios por Id", description = "Deleta  usuario")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail (@PathVariable String email,
                                                       @RequestHeader ("Authorization") String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuarios",
            description = "Atualizar dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario (@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));

    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuarios",
            description = "Atualiza endereço de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco (@RequestBody EnderecoDTORequest dto,
                                                                 @RequestParam ("id") Long id,
                                                                 @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuarios",
            description = "Atualiza telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone (@RequestBody TelefoneDTORequest dto,
                                                                 @RequestParam ("id") Long id,
                                                                 @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuarios",
            description = "Salva endereço de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco (@RequestBody EnderecoDTORequest dto,
                                                                 @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuarios",
            description = "Salva telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone (@RequestBody TelefoneDTORequest dto,
                                                                 @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

}
