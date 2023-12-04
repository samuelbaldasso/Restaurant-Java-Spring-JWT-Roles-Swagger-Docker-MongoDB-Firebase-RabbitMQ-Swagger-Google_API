package com.sbaldass.combo.dto;

import com.sbaldass.combo.services.PedidoService;
import com.sbaldass.combo.services.PratoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {

    private Long id;
    private Long pedidoId;
    private Long pratoId;
    private Integer quantidade;
    private Double precoUnitario;

    private PedidoService pedidoService;
    private PratoService pratoService;

}
