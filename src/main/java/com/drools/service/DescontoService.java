package com.drools.service;

import com.drools.domain.Cliente;
import com.drools.domain.DescontoConfig;
import com.drools.mapper.DescontoConfigMapper;
import com.drools.repository.DescontoConfigRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DescontoService {

    private final KieSession kieSession;
    private final DescontoConfigRepository descontoConfigRepository;
    private final DescontoConfigMapper descontoConfigMapper;

    public Cliente aplicarRegras(Cliente cliente) {
        try {

            List<DescontoConfig> configs =descontoConfigMapper.toDomainList(
                    descontoConfigRepository.findAllByOrderByPrioridadeDesc()
            );


            kieSession.insert(cliente);

            for (DescontoConfig config : configs) {
                kieSession.insert(config);
            }


            kieSession.getAgenda().getAgendaGroup("descontos").setFocus();
            kieSession.fireAllRules();
            return cliente;

        } finally {
            kieSession.dispose();
        }
    }
}
