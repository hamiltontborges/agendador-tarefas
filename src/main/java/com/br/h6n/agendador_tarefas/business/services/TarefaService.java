package com.br.h6n.agendador_tarefas.business.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.br.h6n.agendador_tarefas.business.dto.TarefaDTO;
import com.br.h6n.agendador_tarefas.business.mapper.TarefaConverter;
import com.br.h6n.agendador_tarefas.infrastructure.entity.TarefaEntity;
import com.br.h6n.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.br.h6n.agendador_tarefas.infrastructure.repository.TarefaRepository;
import com.br.h6n.agendador_tarefas.infrastructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter usuarioConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        tarefaDTO.setEmailUsuario(email);
        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefaEntity tarefaEntity = usuarioConverter.paraTarefaEntity(tarefaDTO);

        return usuarioConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }
}
