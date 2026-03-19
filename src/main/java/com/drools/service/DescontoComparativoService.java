package com.drools.service;


import com.drools.domain.Cliente;
import com.drools.dto.DescontoComparativoResponse;
import com.drools.dto.DescontoRequest;
import com.drools.dto.DescontoResponse;
import com.drools.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescontoComparativoService {

    private final DescontoService descontoService;
    private final DescontoDmnService descontoDmnService;
    private final ClienteMapper mapper;

    public DescontoComparativoResponse comparar(DescontoRequest request ) {

        // DRL
        Cliente clienteDRL = mapper.toDomain(request);
        Cliente resultadoDRL = descontoService.aplicarRegras(clienteDRL);
        DescontoResponse responseDRL = mapper.toResponse(resultadoDRL);


        // DMN
        Cliente clienteDMN = mapper.toDomain(request);
        Integer descontoDMN = descontoDmnService.calcularDesconto(request);
        clienteDMN.aplicarMaiorDesconto(descontoDMN);
        DescontoResponse responseDMN = mapper.toResponse(clienteDMN);

        // Comparação
        boolean iguais = responseDRL.getDescontoPercentual()
                .equals(responseDMN.getDescontoPercentual());

        return DescontoComparativoResponse.builder()
                .request(request)
                .resultadoDRL(responseDRL)
                .resultadoDMN(responseDMN)
                .iguais(iguais)
                .build();
    }
}
