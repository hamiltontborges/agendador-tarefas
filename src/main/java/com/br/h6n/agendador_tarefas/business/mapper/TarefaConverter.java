package com.br.h6n.agendador_tarefas.business.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTO;
import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);
    TarefaDTO paraTarefaDTO(TarefaEntity tarefasEntity);

    List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTO> tarefasDTOList);
    List<TarefaDTO> paraListaTarefaDTO(List<TarefaEntity> tarefasEntityList);
}
