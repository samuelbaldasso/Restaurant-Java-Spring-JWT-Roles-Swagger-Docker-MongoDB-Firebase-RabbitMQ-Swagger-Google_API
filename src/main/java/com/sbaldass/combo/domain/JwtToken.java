package com.sbaldass.combo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {
    public String email;
    public String token;
}
