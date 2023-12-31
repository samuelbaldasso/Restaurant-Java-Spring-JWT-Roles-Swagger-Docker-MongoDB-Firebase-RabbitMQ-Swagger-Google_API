package com.sbaldass.combo.dto;

import com.sbaldass.combo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    private LocalDateTime dataHora;
    private String status;
    private Double total;
    private Long usuarioId;

}
