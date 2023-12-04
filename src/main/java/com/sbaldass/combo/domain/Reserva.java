package com.sbaldass.combo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User usuario;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private Integer numeroPessoas;

    private String status;
}
