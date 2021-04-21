package hadrian.demo.service.client;

import hadrian.demo.service.client.model.DrugApplicationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

class OpenFdaClientTestIT {
    private final OpenFdaClient openFdaClient = new OpenFdaClient(WebClient.create(), new BuildingUrl());

    @Test
    void shouldFetchDrugApplicationResponse() {
        DrugApplicationResponse result = openFdaClient.getDrugApplicationResponse("Mylan", 0, 5)
                .block();

        assertThat(result.getMeta().getResults().getSkip()).isEqualTo(0);
        assertThat(result.getMeta().getResults().getLimit()).isEqualTo(5);
        assertThat(result.getResults()).isNotEmpty();
    }
}