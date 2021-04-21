package hadrian.demo.domain;

import hadrian.demo.domain.model.DrugApplication;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugApplicationRepository extends ReactiveCrudRepository<DrugApplication, String> {
}
