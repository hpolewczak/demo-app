package hadrian.demo.service;

import hadrian.demo.controller.model.OpenFdaResponse;
import hadrian.demo.service.client.OpenFdaClient;
import hadrian.demo.service.client.model.DrugApplicationResponse;
import hadrian.demo.service.mapping.DrugApplicationResponseTemplate;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DrugApplicationServiceTest {
    private static final String MANUFACTURER_NAME = "Polfarma";
    private static final long SKIP = 5;
    private static final long LIMIT = 10;
    private final OpenFdaClient openFdaClient = mock(OpenFdaClient.class);
    private final DrugApplicationResponseTemplate drugApplicationResponseTemplate = mock(DrugApplicationResponseTemplate.class);
    private final DrugApplicationService drugApplicationService = new DrugApplicationService(
            openFdaClient, drugApplicationResponseTemplate);

    @Test
    void fetchDrugApplicationsFromOpenFda() {
        //given
        DrugApplicationResponse drugApplicationResponse = new DrugApplicationResponse();
        OpenFdaResponse openFdaResponse = new OpenFdaResponse();
        when(openFdaClient.getDrugApplicationResponse(eq(MANUFACTURER_NAME), eq(SKIP), eq(LIMIT)))
                .thenReturn(Mono.just(drugApplicationResponse));
        when(drugApplicationResponseTemplate.create(any()))
                .thenReturn(openFdaResponse);
        //when //then
        StepVerifier.create(drugApplicationService.fetchDrugApplicationsFromOpenFda(MANUFACTURER_NAME, SKIP, LIMIT))
                .expectNext(openFdaResponse)
                .verifyComplete();
    }
}