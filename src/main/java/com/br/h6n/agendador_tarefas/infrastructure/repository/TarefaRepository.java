package com.br.h6n.agendador_tarefas.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;
import com.br.h6n.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

    List<TarefaEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime start, LocalDateTime end, StatusNotificacaoEnum status);

    List<TarefaEntity> findByEmailUsuario(String email);

}
