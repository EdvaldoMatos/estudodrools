package com.drools.mapper;

import com.drools.domain.Cliente;
import com.drools.dto.DescontoRequest;
import com.drools.dto.DescontoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "descontoPercentual", constant = "0")
    Cliente toDomain(DescontoRequest request);

    @Mapping(target = "valorDesconto", expression = "java(cliente.calcularValorDesconto())")
    @Mapping(target = "valorFinal", expression = "java(cliente.calcularValorFinal())")
    DescontoResponse toResponse(Cliente cliente);

}
