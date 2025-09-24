package com.lucas.bffagendadortarefas.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {

    private String nome;
    private String senha;
    private String email;
    private List<EnderecoDTORequest> enderecos;
    private List <TelefoneDTORequest> telefones;
}
