package hadrian.demo.service.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationResult {
    @JsonProperty("application_number")
    private String applicationNumber;

    @JsonProperty("openfda")
    private OpenFda openFda;

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public OpenFda getOpenFda() {
        return openFda;
    }

    public void setOpenFda(OpenFda openFda) {
        this.openFda = openFda;
    }

    @Override
    public String toString() {
        return "ApplicationResult{" +
                "applicationNumber='" + applicationNumber + '\'' +
                ", openFda=" + openFda +
                '}';
    }
}
