package hadrian.demo.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hadrian.demo.domain.model.DrugApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {"spring.main.allow-bean-definition-overriding=true"}
)
@ContextConfiguration(classes = TestServiceConfig.class)
class DemoAppPersistenceIT {
	private static final String APPLICATION_NUMBER = "AppNumber123";
	private static final String MANUFACTURER_NAME = "corp";
	private static final String PROD_NUMBERS = "prodNUmbers";
	private static final String SUBSTANCE_NAME = "substanceName";
	private final String urlPrefix;
	private final TestRestTemplate restTemplate;

	@Autowired
	public DemoAppPersistenceIT(@LocalServerPort int port, TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.urlPrefix = "http://localhost:" + port;
	}

	@BeforeEach
	void setUp() throws Exception {
		DrugApplication drugApplication = new DrugApplication();
		drugApplication.setApplicationNumber(APPLICATION_NUMBER);
		drugApplication.setManufacturerName(List.of(MANUFACTURER_NAME));
		drugApplication.setProductNumbers(List.of(PROD_NUMBERS));
		drugApplication.setSubstanceName(List.of(SUBSTANCE_NAME));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(serializeToJson(drugApplication), headers);
		assertThat(restTemplate.postForEntity(
				urlPrefix + "/v1/drugApplication", request, String.class)
				.getStatusCode())
				.isEqualTo(HttpStatus.OK);
	}

	@AfterEach
	void tearDown() {
		restTemplate.delete(urlPrefix + "/v1/drugApplication/" + APPLICATION_NUMBER);
	}

	@Test
	void shouldGetDrugApplication() {
		assertThat(restTemplate.getForObject(urlPrefix + "/v1/drugApplication", String.class))
				.contains(APPLICATION_NUMBER);
	}

	private String serializeToJson(DrugApplication drugApplication) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(drugApplication);
	}
}
