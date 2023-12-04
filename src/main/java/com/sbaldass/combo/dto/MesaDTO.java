package com.sbaldass.combo.dto;

import com.sbaldass.combo.domain.StatusMesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaDTO {
    private Long id;
    private Integer numero;
    private Integer capacidade;
    private StatusMesa status;
}
