package hadrian.demo.service.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hadrian.demo.service.client.model.Product;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenFda {
    @JsonProperty("application_number")
    private List<String> applicationNumber;

    @JsonProperty("brand_name")
    private List<String> brandName;

    @JsonProperty("manufacturer_name")
    private List<String> manufacturerName;

    @JsonProperty("substance_name")
    private List<String> substanceName;

    @JsonProperty("products")
    private List<Product> products;

    public List<String> getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(List<String> applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public List<String> getBrandName() {
        return brandName;
    }

    public void setBrandName(List<String> brandName) {
        this.brandName = brandName;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OpenFda{" +
                "applicationNumber=" + applicationNumber +
                ", brandName=" + brandName +
                ", manufacturerName=" + manufacturerName +
                ", substanceName=" + substanceName +
                ", products=" + products +
                '}';
    }
}
