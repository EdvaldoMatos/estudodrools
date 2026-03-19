package com.drools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNRuntime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DmnConfig {

    @Bean
    public DMNRuntime dmnRuntime() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(
                "src/main/resources/dmn/desconto.dmn",
                kieServices.getResources().newClassPathResource("dmn/desconto.dmn")
        );

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();

        if (results.hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException("Erro ao compilar DMN: " + results.getMessages());
        }

        KieContainer kieContainer =
                kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());

        return kieContainer.newKieSession().getKieRuntime(DMNRuntime.class);
    }
}