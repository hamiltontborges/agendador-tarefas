package com.br.h6n.agendador_tarefas.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

    List<TarefaEntity> findByDataEventoBetween(LocalDateTime start, LocalDateTime end);

    List<TarefaEntity> findByEmailUsuario(String email);

}
