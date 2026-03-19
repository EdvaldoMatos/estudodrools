package com.drools.api;

import com.drools.dto.DescontoComparativoResponse;
import com.drools.dto.DescontoRequest;
import com.drools.service.DescontoComparativoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/descontos/comparativo")
@RequiredArgsConstructor
public class DescontoComparativoController {

    private final DescontoComparativoService service;

    @PostMapping
    public ResponseEntity<DescontoComparativoResponse> comparar(@RequestBody DescontoRequest request) {
        return ResponseEntity.ok(service.comparar(request));
    }
}