package com.drools.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    private String nome;
    private Integer idade;

    @Builder.Default
    private BigDecimal valorCompra =  BigDecimal.ZERO;

    @Builder.Default
    private Integer descontoPercentual = 0;

    public void aplicarMaiorDesconto(Integer novoDesconto){
        int descontoAtual = this.descontoPercentual == null ? 0 : this.descontoPercentual;
        int novo = novoDesconto == null ? 0 : novoDesconto;

        if(novo >descontoAtual){
            this.descontoPercentual = novo;
        }
    }
    public BigDecimal calcularValorDesconto() {
        BigDecimal percentual = BigDecimal.valueOf(
                descontoPercentual == null ? 0 : descontoPercentual
        );

        return valorCompra
                .multiply(percentual)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularValorFinal() {
        return valorCompra
                .subtract(calcularValorDesconto())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
