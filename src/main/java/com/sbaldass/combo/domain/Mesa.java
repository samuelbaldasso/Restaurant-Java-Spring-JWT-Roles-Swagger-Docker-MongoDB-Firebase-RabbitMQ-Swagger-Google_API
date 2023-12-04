package com.sbaldass.combo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mesas")
@Getter
@Setter
@NoArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private Integer capacidade;
    private StatusMesa status;

}
