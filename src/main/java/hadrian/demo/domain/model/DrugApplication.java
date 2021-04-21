package hadrian.demo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("DrugApplication")
public class DrugApplication {
    @Id
    private String applicationNumber;

    private List<String> manufacturerName;

    private List<String> substanceName;

    private List<String> productNumbers;

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public List<String> getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(List<String> manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<String> getSubstanceName() {
        return substanceName;
    }

    public void setSubstanceName(List<String> substanceName) {
        this.substanceName = substanceName;
    }

    public List<String> getProductNumbers() {
        return productNumbers;
    }

    public void setProductNumbers(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }


}
