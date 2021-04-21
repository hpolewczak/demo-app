package hadrian.demo.controller;

import hadrian.demo.domain.DrugApplicationRepository;
import hadrian.demo.domain.model.DrugApplication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DrugApplicationController {
    private final DrugApplicationRepository drugApplicationRepository;

    public DrugApplicationController(DrugApplicationRepository drugApplicationRepository) {
        this.drugApplicationRepository = drugApplicationRepository;
    }

    @GetMapping("/v1/drugApplication")
    public Flux<DrugApplication> getDrugApplications() {
        return drugApplicationRepository.findAll();
    }

    @GetMapping("/v1/drugApplication/{id}")
    public Mono<DrugApplication> getDrugApplication(@PathVariable String id) {
        return drugApplicationRepository.findById(id);
    }

    @PostMapping("/v1/drugApplication")
    public Mono<DrugApplication> saveDrugApplication(@RequestBody DrugApplication drugApplication) {
        return drugApplicationRepository.save(drugApplication);
    }

    @DeleteMapping("/v1/drugApplication/{id}")
    public Mono<Void> deleteDrugApplication(@PathVariable String id) {
        return drugApplicationRepository.deleteById(id);
    }
}
