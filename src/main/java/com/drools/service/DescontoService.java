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
        try{
        kieSession.insert(cliente);
        kieSession.getAgenda().getAgendaGroup("descontos").setFocus();
        kieSession.fireAllRules();
        return cliente;

        }finally {
            kieSession.dispose();
        }
    }
}
