package com.br.h6n.agendador_tarefas.infrastructure.exceptions;

import javax.naming.AuthenticationException;


public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message);
    }
}
