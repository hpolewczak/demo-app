package hadrian.demo.service.client;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BuildingUrlTest {
    private static final int SKIP = 5;
    private static final int LIMIT = 10;
    private final BuildingUrl buildingUrl = new BuildingUrl();

    @Test
    void shouldBuildCriteria() {
        assertThat(buildingUrl.buildCriteria("Mylan Pharm Inc.", SKIP, LIMIT))
                .isEqualTo("https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:'Mylan%20Pharm%20Inc.'&skip=5&limit=10");
    }

    @Test
    void shouldBuildCriteriaWithEscape() {
        assertThat(buildingUrl.buildCriteria("!@#$%\"^&*(", SKIP, LIMIT))
                .isEqualTo("https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:'%21%40%23%24%25%22%5E%26%2A%28'&skip=5&limit=10");
    }

    @Test
    void shouldBuildCriteriaWithLimitParam() {
        assertThat(buildingUrl.buildCriteria("", SKIP , LIMIT)).contains("limit=10");
    }

    @Test
    void shouldBuildCriteriaWithSkipParam() {
        assertThat(buildingUrl.buildCriteria("", SKIP , LIMIT)).contains("skip=5");
    }
}