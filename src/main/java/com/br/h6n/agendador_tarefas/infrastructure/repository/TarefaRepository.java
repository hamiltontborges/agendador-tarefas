package com.br.h6n.agendador_tarefas.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

}
