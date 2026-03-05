package com.lucas.bffagendadortarefas.controller;


import com.lucas.bffagendadortarefas.business.UsuarioService;
import com.lucas.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.lucas.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.lucas.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.lucas.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.lucas.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.lucas.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.lucas.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.lucas.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @ApiResponse(responseCode = "409", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuarios", description = "Login do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);

    }

    @GetMapping
    @Operation(summary = "Buscar Dados de Usuarios por Email",
            description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));

    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuarios por Email", description = "Deleta  usuario")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @Parameter(hidden = true) @RequestHeader("Authorization")
                                                      String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuarios",
            description = "Atualizar dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));

    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuarios",
            description = "Atualiza endereço de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuarios",
            description = "Atualiza telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuarios",
            description = "Salva endereço de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuarios",
            description = "Salva telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereço pelo cep",
            description = "Busca dados de endereço recebendo um cep")
    @ApiResponse(responseCode = "200", description = "Dados de endereço retornados com  sucesso")
    @ApiResponse(responseCode = "400", description = "Cep inválido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse> buscarEndereco(@PathVariable("cep") String cep) {

        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));
    }

    @DeleteMapping("/endereco")
    @Operation(summary = "Deleta Endereço de Usuários",
            description = "Deleta o endereço de um usuário passando o ID")
    @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado / Sem permissão")
    public ResponseEntity<Void> deletaEndereco(@RequestParam("id") Long id,
                                               @RequestHeader("Authorization") String token) {
        usuarioService.deletaEndereco(id, token);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/telefone")
    @Operation(summary = "Deleta Telefone de Usuários",
            description = "Deleta o telefone de um usuário passando o ID")
    @ApiResponse(responseCode = "204", description = "Telefone deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado / Sem permissão")
    public ResponseEntity<Void> deletaTelefone(@RequestParam("id") Long id,
                                               @RequestHeader("Authorization") String token) {
        usuarioService.deletaTelefone(id, token);
        return ResponseEntity.noContent().build();
    }

}
