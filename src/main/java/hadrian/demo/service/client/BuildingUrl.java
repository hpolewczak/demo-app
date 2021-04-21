package hadrian.demo.service.client;

import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

public class BuildingUrl {

    public String buildCriteria(String manufacturerName, long skip, long limit) {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.fda.gov")
                .path("/drug/drugsfda.json")
                .queryParam("search", "openfda.manufacturer_name:'" + encodeValue(manufacturerName) + "'")
                .queryParam("skip", skip)
                .queryParam("limit", limit)
                .build()
                .toUriString();
    }

    private String encodeValue(String value) {
        return UriUtils.encode(value, StandardCharsets.UTF_8);
    }


}
