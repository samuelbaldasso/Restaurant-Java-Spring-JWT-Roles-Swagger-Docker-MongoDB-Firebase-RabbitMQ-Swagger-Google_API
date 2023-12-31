package com.sbaldass.combo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
public enum StatusMesa {
    DISPONIVEL("disponivel"),
    OCUPADA("ocupada");

    private final String status;

    StatusMesa(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static StatusMesa fromStatus(String status) {
        for (StatusMesa s : StatusMesa.values()) {
            if (s.getStatus().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status);
    }

}
