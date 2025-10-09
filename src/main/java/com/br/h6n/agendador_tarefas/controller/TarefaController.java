package com.br.h6n.agendador_tarefas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTORecord;
import com.br.h6n.agendador_tarefas.business.services.TarefaService;
import com.br.h6n.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTORecord> criarTarefa(@RequestBody TarefaDTORecord tarefaDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTORecord>> buscarTarefasPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
        @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTORecord>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable String id) {
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TarefaDTORecord> alterarStatusNotificacao(@PathVariable String id, @RequestParam StatusNotificacaoEnum status) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTORecord> atualizarTarefa(@PathVariable String id, @RequestBody TarefaDTORecord tarefaDTO) {
        return ResponseEntity.ok(tarefaService.updateTarefa(id, tarefaDTO));
    }

}
