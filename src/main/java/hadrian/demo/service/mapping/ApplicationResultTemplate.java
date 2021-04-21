package hadrian.demo.service.mapping;

import hadrian.demo.domain.model.DrugApplication;
import hadrian.demo.service.client.model.ApplicationResult;
import hadrian.demo.service.client.model.OpenFda;
import hadrian.demo.service.client.model.Product;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApplicationResultTemplate {
    public DrugApplication create(ApplicationResult source) {
        DrugApplication drugApplication = new DrugApplication();
        drugApplication.setApplicationNumber(source.getApplicationNumber());
        OpenFda openFda = source.getOpenFda();
        if (openFda!=null) {
            drugApplication.setManufacturerName(openFda.getManufacturerName());
            drugApplication.setSubstanceName(openFda.getSubstanceName());
            drugApplication.setProductNumbers(extractProductNumbers(openFda));
        }
        return drugApplication;
    }

    private List<String> extractProductNumbers(OpenFda openFda) {
        return Optional.ofNullable(openFda.getProducts())
                .orElse(Collections.emptyList())
                .stream()
                .map(Product::getProductNumber)
                .collect(Collectors.toList());
    }
}
