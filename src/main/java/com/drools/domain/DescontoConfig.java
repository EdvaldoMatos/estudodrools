package com.drools.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DescontoConfig {

    private String tipoCliente;
    private Integer idadeMinima;
    private BigDecimal valorMinimoCompra;
    private Integer descontoPercentual;
    private Integer prioridade;
}
