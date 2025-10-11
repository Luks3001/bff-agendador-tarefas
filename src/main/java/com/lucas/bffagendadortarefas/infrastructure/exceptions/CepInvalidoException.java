package com.lucas.bffagendadortarefas.infrastructure.exceptions;

public class CepInvalidoException extends RuntimeException {

    public CepInvalidoException(String mensagem) {
        super(mensagem);
    }

    public CepInvalidoException(String mensagem, Throwable throwable){
        super (mensagem,throwable);
    }
}
