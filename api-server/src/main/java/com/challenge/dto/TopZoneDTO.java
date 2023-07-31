package com.challenge.dto;

public class TopZoneDTO {

    private String zone;

    private Integer puTotal;

    private Integer doTotal;

    public TopZoneDTO() {
    }

    public TopZoneDTO(String zone, Integer puTotal, Integer doTotal) {
        this.zone = zone;
        this.puTotal = puTotal;
        this.doTotal = doTotal;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getPuTotal() {
        return puTotal;
    }

    public void setPuTotal(Integer puTotal) {
        this.puTotal = puTotal;
    }

    public Integer getDoTotal() {
        return doTotal;
    }

    public void setDoTotal(Integer doTotal) {
        this.doTotal = doTotal;
    }
}
