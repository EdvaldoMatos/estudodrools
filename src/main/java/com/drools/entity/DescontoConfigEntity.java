package com.drools.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "desconto_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescontoConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_cliente")
    private String tipoCliente;

    @Column(name = "idade_minima")
    private Integer idadeMinima;

    @Column(name = "valor_minimo_compra", precision = 15, scale = 2)
    private BigDecimal valorMinimoCompra;

    @Column(name = "desconto_percentual", nullable = false)
    private Integer descontoPercentual;

    @Column(name = "prioridade", nullable = false)
    private Integer prioridade;
}
