package com.drools.service;

import com.drools.dto.DescontoRequest;
import lombok.RequiredArgsConstructor;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DescontoDmnService {

    private final DMNRuntime dmnRuntime;

    public Integer calcularDesconto(DescontoRequest request) {
        DMNModel model = dmnRuntime.getModel(
                "https://exemplo.com/dmn/desconto",
                "DescontoCliente"
        );

        if (model == null) {
            throw new IllegalStateException("Modelo DMN não encontrado");
        }

        DMNContext context = dmnRuntime.newContext();
        context.set("idade", request.getIdade());
        context.set("tipoCliente", request.getTipoCliente());
        context.set("valorCompra", request.getValorCompra());

        DMNResult result = dmnRuntime.evaluateAll(model, context);

        if (result.hasErrors()) {
            throw new IllegalStateException("Erro ao avaliar DMN: " + result.getMessages());
        }

        Object output = result.getContext().get("descontoPercentual");
        return output == null ? 0 : ((Number) output).intValue();
    }
}
