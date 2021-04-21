package hadrian.demo.controller.model;

import hadrian.demo.domain.model.DrugApplication;

import java.util.List;

public class OpenFdaResponse {
    private long skip;
    private long limit;
    private long total;
    private List<DrugApplication> drugApplication;

    public long getSkip() {
        return skip;
    }

    public void setSkip(long skip) {
        this.skip = skip;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<DrugApplication> getDrugApplication() {
        return drugApplication;
    }

    public void setDrugApplication(List<DrugApplication> drugApplication) {
        this.drugApplication = drugApplication;
    }

    @Override
    public String toString() {
        return "OpenFdaResponse{" +
                "skip=" + skip +
                ", limit=" + limit +
                ", total=" + total +
                ", drugApplication=" + drugApplication +
                '}';
    }
}
