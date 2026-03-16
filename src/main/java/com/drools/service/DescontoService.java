package com.drools.service;

import com.drools.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescontoService {

    private final KieSession kieSession;

    public Cliente aplicarRegras(Cliente cliente) {
        kieSession.insert(cliente);
        kieSession.fireAllRules();
        return cliente;
    }
}
