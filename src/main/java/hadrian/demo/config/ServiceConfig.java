package hadrian.demo.config;

import hadrian.demo.service.DrugApplicationService;
import hadrian.demo.service.client.BuildingUrl;
import hadrian.demo.service.client.OpenFdaClient;
import hadrian.demo.service.mapping.ApplicationResultTemplate;
import hadrian.demo.service.mapping.DrugApplicationResponseTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceConfig {

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    BuildingUrl buildingUrl() {
        return new BuildingUrl();
    }

    @Bean
    OpenFdaClient openFdaClient() {
        return new OpenFdaClient(webClient(), buildingUrl());
    }

    @Bean
    DrugApplicationService drugApplicationService() {
        return new DrugApplicationService(openFdaClient(), drugApplicationResponseTemplate());
    }

    @Bean
    DrugApplicationResponseTemplate drugApplicationResponseTemplate() {
        return new DrugApplicationResponseTemplate(new ApplicationResultTemplate());
    }
}
