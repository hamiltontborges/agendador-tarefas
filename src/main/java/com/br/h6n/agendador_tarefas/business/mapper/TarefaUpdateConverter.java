package com.br.h6n.agendador_tarefas.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTORecord;
import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    void updateTarefa(TarefaDTORecord tarefaDTO, @MappingTarget TarefaEntity tarefaEntity);
}
