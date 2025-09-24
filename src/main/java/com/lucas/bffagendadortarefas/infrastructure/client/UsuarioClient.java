package com.lucas.bffagendadortarefas.infrastructure.client;


import com.lucas.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.lucas.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.lucas.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.lucas.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.lucas.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.lucas.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.lucas.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail (@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario (@RequestBody UsuarioDTORequest usuarioDTO);



    @PostMapping ("/login")
     String login (@RequestBody LoginRequestDTO usuarioDTO);



    @DeleteMapping ("/{email}")
    void deletaUsuarioPorEmail (@PathVariable String email,
                                @RequestHeader ("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario (@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader ("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco (@RequestBody EnderecoDTORequest dto,
                                          @RequestParam ("id") Long id,
                                          @RequestHeader ("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone (@RequestBody TelefoneDTORequest dto,
                                          @RequestParam ("id") Long id,
                                          @RequestHeader ("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco (@RequestBody EnderecoDTORequest dto,
                                          @RequestHeader ("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone (@RequestBody TelefoneDTORequest dto,
                                          @RequestHeader ("Authorization") String token);

}
