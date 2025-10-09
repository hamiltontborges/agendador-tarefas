package com.br.h6n.agendador_tarefas.business.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTORecord;
import com.br.h6n.agendador_tarefas.business.mapper.TarefaConverter;
import com.br.h6n.agendador_tarefas.business.mapper.TarefaUpdateConverter;
import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;
import com.br.h6n.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.br.h6n.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.br.h6n.agendador_tarefas.infrastructure.repository.TarefaRepository;
import com.br.h6n.agendador_tarefas.infrastructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTORecord gravarTarefa(String token, TarefaDTORecord tarefaDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        TarefaDTORecord tarefaDTOfinal = new TarefaDTORecord(
            null, 
            tarefaDTO.nomeTarefa(), 
            tarefaDTO.descricao(), 
            email, LocalDateTime.now(), 
            tarefaDTO.dataAlteracao(), 
            null,
            StatusNotificacaoEnum.PENDENTE
        );
        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTOfinal);
        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }

   public List<TarefaDTORecord> buscaTarefasAgendasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
       return tarefaConverter.paraListaTarefaDTO(tarefaRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(inicio, fim, StatusNotificacaoEnum.PENDENTE));
   }

   public List<TarefaDTORecord> buscaTarefasPorEmail(String token) {
       String email = jwtUtil.extractUsername(token.substring(7));
       List<TarefaEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);

       return tarefaConverter.paraListaTarefaDTO(listaTarefas);
   }

   public void deletaTarefaPorId(String id) {
        try {
           tarefaRepository.deleteById(id);
       } catch (ResourceNotFoundException e) {
           throw new ResourceNotFoundException("Tarefa não encontrada.", e.getCause());
       }
   }

   public TarefaDTORecord alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada."));
            tarefaEntity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa.", e.getCause());
        }
   }

   public TarefaDTORecord updateTarefa(String id, TarefaDTORecord tarefaDTO) {
       try {
           TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada."));
           tarefaUpdateConverter.updateTarefa(tarefaDTO, tarefaEntity);
           return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
       } catch (ResourceNotFoundException e) {
           throw new ResourceNotFoundException("Erro ao atualizar tarefa.", e.getCause());
       }
   }
}
