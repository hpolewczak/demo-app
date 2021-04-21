package hadrian.demo.service.mapping;

import hadrian.demo.domain.model.DrugApplication;
import hadrian.demo.service.client.model.ApplicationResult;
import hadrian.demo.service.client.model.DrugApplicationResponse;
import hadrian.demo.service.client.model.Metadata;
import hadrian.demo.service.client.model.Results;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DrugApplicationResponseTemplateTest {
    private final ApplicationResultTemplate applicationResultTemplate = mock(ApplicationResultTemplate.class);
    private final DrugApplicationResponseTemplate drugApplicationResponseTemplate =
            new DrugApplicationResponseTemplate(applicationResultTemplate);

    @Test
    void shouldMapMetadataTotal() {
        //given
        DrugApplicationResponse drugApplicationResponse = new DrugApplicationResponse();
        Metadata metadata = new Metadata();
        Results results = new Results();
        results.setTotal(100);
        metadata.setResults(results);
        drugApplicationResponse.setMeta(metadata);
        //when //then
        assertThat(drugApplicationResponseTemplate.create(drugApplicationResponse).getTotal()).isEqualTo(100);
    }

    @Test
    void shouldMapMetadataSkip() {
        //given
        DrugApplicationResponse drugApplicationResponse = new DrugApplicationResponse();
        Metadata metadata = new Metadata();
        Results results = new Results();
        results.setSkip(10);
        metadata.setResults(results);
        drugApplicationResponse.setMeta(metadata);
        //when //then
        assertThat(drugApplicationResponseTemplate.create(drugApplicationResponse).getSkip()).isEqualTo(10);
    }

    @Test
    void shouldMapMetadataLimit() {
        //given
        DrugApplicationResponse drugApplicationResponse = new DrugApplicationResponse();
        Metadata metadata = new Metadata();
        Results results = new Results();
        results.setLimit(20);
        metadata.setResults(results);
        drugApplicationResponse.setMeta(metadata);
        //when //then
        assertThat(drugApplicationResponseTemplate.create(drugApplicationResponse).getLimit()).isEqualTo(20);
    }

    @Test
    void shouldMapOneResult() {
        //given
        DrugApplicationResponse drugApplicationResponse = new DrugApplicationResponse();
        ApplicationResult applicationResult = new ApplicationResult();
        DrugApplication drugApplication = new DrugApplication();
        drugApplicationResponse.setResults(List.of(applicationResult));
        when(applicationResultTemplate.create(eq(applicationResult))).thenReturn(drugApplication);
        //when //then
        assertThat(drugApplicationResponseTemplate.create(drugApplicationResponse).getDrugApplication())
                .containsOnly(drugApplication);
    }

    @Test
    void shouldMapTwoResults() {
        //given
        DrugApplicationResponse drugApplicationResponse = new DrugApplicationResponse();
        ApplicationResult applicationResult1 = new ApplicationResult();
        ApplicationResult applicationResult2 = new ApplicationResult();
        DrugApplication drugApplication1 = new DrugApplication();
        DrugApplication drugApplication2 = new DrugApplication();
        drugApplicationResponse.setResults(List.of(applicationResult1, applicationResult2));
        when(applicationResultTemplate.create(eq(applicationResult1))).thenReturn(drugApplication1);
        when(applicationResultTemplate.create(eq(applicationResult2))).thenReturn(drugApplication2);
        //when //then
        assertThat(drugApplicationResponseTemplate.create(drugApplicationResponse).getDrugApplication())
                .containsOnly(drugApplication1, drugApplication2);
    }
}