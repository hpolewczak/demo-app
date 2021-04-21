package hadrian.demo.service.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugApplicationResponse {
    private Metadata meta;
    private List<ApplicationResult> results;

    public Metadata getMeta() {
        return meta;
    }

    public void setMeta(Metadata meta) {
        this.meta = meta;
    }

    public List<ApplicationResult> getResults() {
        return results;
    }

    public void setResults(List<ApplicationResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "DrugApplicationResponse{" +
                "meta=" + meta +
                ", results=" + results +
                '}';
    }
}
