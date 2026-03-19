package com.drools.api;

import com.drools.domain.Cliente;
import com.drools.dto.DescontoRequest;
import com.drools.dto.DescontoResponse;
import com.drools.mapper.ClienteMapper;
import com.drools.service.DescontoDmnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/descontos/dmn")
@RequiredArgsConstructor
public class DescontoDmnController {

    private final ClienteMapper clienteMapper;
    private final DescontoDmnService descontoDmnService;

    @PostMapping
    public ResponseEntity<DescontoResponse> calcular(@RequestBody DescontoRequest request) {
        Cliente cliente = clienteMapper.toDomain(request);

        Integer desconto = descontoDmnService.calcularDesconto(request);
        cliente.aplicarMaiorDesconto(desconto);

        return ResponseEntity.ok(clienteMapper.toResponse(cliente));
    }
}
