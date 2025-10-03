package com.br.h6n.agendador_tarefas.business.mapper;

import org.mapstruct.Mapper;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTO;
import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);
    TarefaDTO paraTarefaDTO(TarefaEntity tarefasEntity);
}
