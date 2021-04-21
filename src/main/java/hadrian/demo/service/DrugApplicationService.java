package hadrian.demo.service;

import hadrian.demo.controller.model.OpenFdaResponse;
import hadrian.demo.service.client.OpenFdaClient;
import hadrian.demo.service.mapping.DrugApplicationResponseTemplate;
import reactor.core.publisher.Mono;

public class DrugApplicationService {
    private final OpenFdaClient openFdaClient;
    private final DrugApplicationResponseTemplate drugApplicationResponseTemplate;

    public DrugApplicationService(OpenFdaClient openFdaClient, DrugApplicationResponseTemplate drugApplicationResponseTemplate) {
        this.openFdaClient = openFdaClient;
        this.drugApplicationResponseTemplate = drugApplicationResponseTemplate;
    }

    public Mono<OpenFdaResponse> fetchDrugApplicationsFromOpenFda(String manufacturerName, long skip, long limit) {
        return openFdaClient
                .getDrugApplicationResponse(manufacturerName, skip, limit)
                .map(source -> {
                    return drugApplicationResponseTemplate.create(source);
                });
    }
}
