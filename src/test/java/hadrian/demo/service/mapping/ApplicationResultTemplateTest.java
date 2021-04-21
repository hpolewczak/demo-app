package hadrian.demo.service.mapping;

import hadrian.demo.domain.model.DrugApplication;
import hadrian.demo.service.client.model.ApplicationResult;
import hadrian.demo.service.client.model.OpenFda;
import hadrian.demo.service.client.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationResultTemplateTest {
    public static final String APP_NUMBER = "AppNumber";
    public static final String MANUFACTURER = "Mylan";
    public static final String SUBSTANCE_NAME = "PARACETAMOL";
    public static final String PROD_NUMBER = "PROD_NUMBER";
    private final ApplicationResultTemplate applicationResultTemplate = new ApplicationResultTemplate();

    @Test
    void shouldMapApplicationNumber() {
        //given
        ApplicationResult applicationResult = new ApplicationResult();
        applicationResult.setApplicationNumber(APP_NUMBER);
        //when
        DrugApplication result = applicationResultTemplate.create(applicationResult);
        //then
        assertThat(result.getApplicationNumber()).isEqualTo(APP_NUMBER);
    }

    @Test
    void shouldMapManufacturerName() {
        //given
        ApplicationResult applicationResult = new ApplicationResult();
        OpenFda openFda = new OpenFda();
        openFda.setManufacturerName(List.of(MANUFACTURER));
        applicationResult.setOpenFda(openFda);
        //when
        DrugApplication result = applicationResultTemplate.create(applicationResult);
        //then
        assertThat(result.getManufacturerName()).contains(MANUFACTURER);
    }

    @Test
    void shouldMapSubstanceName() {
        //given
        ApplicationResult applicationResult = new ApplicationResult();
        OpenFda openFda = new OpenFda();
        openFda.setSubstanceName(List.of(SUBSTANCE_NAME));
        applicationResult.setOpenFda(openFda);
        //when
        DrugApplication result = applicationResultTemplate.create(applicationResult);
        //then
        assertThat(result.getSubstanceName()).contains(SUBSTANCE_NAME);
    }

    @Test
    void shouldMapProductNumber() {
        //given
        ApplicationResult applicationResult = new ApplicationResult();
        OpenFda openFda = new OpenFda();
        Product product = new Product();
        product.setProductNumber(PROD_NUMBER);
        openFda.setProducts(List.of(product));
        applicationResult.setOpenFda(openFda);
        //when
        DrugApplication result = applicationResultTemplate.create(applicationResult);
        //then
        assertThat(result.getProductNumbers()).contains(PROD_NUMBER);
    }
}