package com.sbaldass.combo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PratoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    private String foto;
}
