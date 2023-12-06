package com.sbaldass.combo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private Long userId;
    private Long pratoId;
    private Integer pontuacao;
    private String comentario;
}
