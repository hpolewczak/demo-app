package hadrian.demo.service.client;

import hadrian.demo.service.client.model.DrugApplicationResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class OpenFdaClient {
    private final WebClient client;
    private final BuildingUrl buildingUrl;

    public OpenFdaClient(WebClient client, BuildingUrl buildingUrl) {
        this.client = client;
        this.buildingUrl = buildingUrl;
    }

    public Mono<DrugApplicationResponse> getDrugApplicationResponse(String manufacturerName, long skip, long limit) {
        return getResponse(buildingUrl.buildCriteria(manufacturerName, skip, limit), DrugApplicationResponse.class);
    }

    private <T> Mono<T> getResponse(String url, Class<T> clazz) {
        return client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(clazz);
    }
}
