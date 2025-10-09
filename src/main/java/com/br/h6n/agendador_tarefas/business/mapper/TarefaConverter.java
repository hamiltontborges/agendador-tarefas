package com.br.h6n.agendador_tarefas.business.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTORecord;
import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTORecord tarefaDTO);
    TarefaDTORecord paraTarefaDTO(TarefaEntity tarefasEntity);

    List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTORecord> tarefasDTOList);
    List<TarefaDTORecord> paraListaTarefaDTO(List<TarefaEntity> tarefasEntityList);
}
