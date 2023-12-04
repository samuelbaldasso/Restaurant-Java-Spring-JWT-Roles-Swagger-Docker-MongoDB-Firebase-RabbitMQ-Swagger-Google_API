package com.sbaldass.combo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private Long id;
    private Long usuarioId;
    private LocalDateTime dataHora;
    private Integer numeroPessoas;
    private String status;
}
