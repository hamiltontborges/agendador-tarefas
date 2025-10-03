package com.br.h6n.agendador_tarefas.infrastructure.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.br.h6n.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tarefas")
public class TarefaEntity {

    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private String emailUsuario;
    private LocalDateTime dataCriacao;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

}
