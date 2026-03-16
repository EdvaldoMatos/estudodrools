package com.drools.api;

import com.drools.domain.Cliente;
import com.drools.dto.DescontoRequest;
import com.drools.dto.DescontoResponse;
import com.drools.mapper.ClienteMapper;
import com.drools.service.DescontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/descontos")
@RequiredArgsConstructor
public class DescontoController {

    private final DescontoService descontoService;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<DescontoResponse> calcular(@RequestBody DescontoRequest request) {
        Cliente cliente = clienteMapper.toDomain(request);
        Cliente clienteCalculado = descontoService.aplicarRegras(cliente);
        DescontoResponse response = clienteMapper.toResponse(clienteCalculado);
        return ResponseEntity.ok(response);
    }
}