package hadrian.demo.controller;

import hadrian.demo.controller.model.OpenFdaResponse;
import hadrian.demo.service.DrugApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OpenFdaController {
    private final DrugApplicationService drugApplicationService;

    public OpenFdaController(DrugApplicationService drugApplicationService) {
        this.drugApplicationService = drugApplicationService;
    }

    @GetMapping("/v1/open-fda")
    public Mono<OpenFdaResponse> getDrugApplications(@RequestParam("manufacturerName") String manufacturerName,
                                                     @RequestParam("skip") Long skip,
                                                     @RequestParam("limit") Long limit) {
        return drugApplicationService.fetchDrugApplicationsFromOpenFda(manufacturerName, skip, limit);
    }

}
