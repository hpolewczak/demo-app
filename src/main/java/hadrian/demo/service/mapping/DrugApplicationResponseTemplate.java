package hadrian.demo.service.mapping;

import hadrian.demo.controller.model.OpenFdaResponse;
import hadrian.demo.domain.model.DrugApplication;
import hadrian.demo.service.client.model.DrugApplicationResponse;
import hadrian.demo.service.client.model.Results;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DrugApplicationResponseTemplate {
    private final ApplicationResultTemplate applicationResultTemplate;

    public DrugApplicationResponseTemplate(ApplicationResultTemplate applicationResultTemplate) {
        this.applicationResultTemplate = applicationResultTemplate;
    }

    public OpenFdaResponse create(DrugApplicationResponse source) {
        OpenFdaResponse response = new OpenFdaResponse();
        if (source.getMeta()!=null && source.getMeta().getResults() != null) {
            Results metaResults = source.getMeta().getResults();
            response.setTotal(metaResults.getTotal());
            response.setSkip(metaResults.getSkip());
            response.setLimit(metaResults.getLimit());
        }
        response.setDrugApplication(getDrugApplications(source));
        return response;
    }

    private List<DrugApplication> getDrugApplications(DrugApplicationResponse source) {
        return Optional.ofNullable(source.getResults())
                .orElse(Collections.emptyList())
                .stream()
                .map(applicationResultTemplate::create)
                .collect(Collectors.toList());
    }

}
