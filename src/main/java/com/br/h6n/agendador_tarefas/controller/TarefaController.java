package com.br.h6n.agendador_tarefas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTO;
import com.br.h6n.agendador_tarefas.business.services.TarefaService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefaDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }
    
}
