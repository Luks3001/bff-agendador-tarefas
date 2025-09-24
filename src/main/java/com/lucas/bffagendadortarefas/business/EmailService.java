package com.lucas.bffagendadortarefas.business;


import com.lucas.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.lucas.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto) {

        emailClient.enviarEmail(dto);
    }
}

