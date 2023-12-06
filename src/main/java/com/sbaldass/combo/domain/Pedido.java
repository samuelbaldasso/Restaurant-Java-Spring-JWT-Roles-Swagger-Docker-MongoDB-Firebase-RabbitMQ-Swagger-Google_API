package com.sbaldass.combo.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    private String status;
    private Double total;

    @ManyToOne
    private User usuario;
}
