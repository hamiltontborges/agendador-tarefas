package com.br.h6n.agendador_tarefas.business.dto;

import java.time.LocalDateTime;

import com.br.h6n.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;

public record TarefaDTORecord(
    String id,
    String nomeTarefa,
    String descricao,
    String emailUsuario,
    LocalDateTime dataCriacao,
    LocalDateTime dataEvento,
    LocalDateTime dataAlteracao,
    StatusNotificacaoEnum statusNotificacaoEnum) {
        
}
