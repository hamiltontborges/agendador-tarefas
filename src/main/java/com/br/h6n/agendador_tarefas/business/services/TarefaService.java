package com.br.h6n.agendador_tarefas.business.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTO;
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

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        tarefaDTO.setEmailUsuario(email);
        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);

        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }

   public List<TarefaDTO> buscaTarefasAgendasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
       return tarefaConverter.paraListaTarefaDTO(tarefaRepository.findByDataEventoBetween(inicio, fim));
   }

   public List<TarefaDTO> buscaTarefasPorEmail(String token) {
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

   public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada."));
            tarefaEntity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa.", e.getCause());
        }
   }

   public TarefaDTO updateTarefa(String id, TarefaDTO tarefaDTO) {
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
