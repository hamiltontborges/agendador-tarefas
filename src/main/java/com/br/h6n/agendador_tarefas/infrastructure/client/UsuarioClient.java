package com.br.h6n.agendador_tarefas.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.h6n.agendador_tarefas.business.dto.UsuarioDTO;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuarios")
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);
}
