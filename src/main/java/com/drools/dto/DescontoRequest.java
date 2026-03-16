package com.drools.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescontoRequest {
    private String nome;
    private Integer idade;
    private BigDecimal valorCompra;
}
