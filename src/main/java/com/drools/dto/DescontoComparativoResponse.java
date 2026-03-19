package com.drools.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DescontoComparativoResponse {

    private DescontoRequest request;

    private DescontoResponse resultadoDRL;
    private DescontoResponse resultadoDMN;

    private Boolean iguais;
}
