package com.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ZoneInfoDTO {
    private String zone;
    private Date date;
    @JsonProperty("pu")
    private Long pickups;
    @JsonProperty("do")
    private Long dropoffs;


    public ZoneInfoDTO() {
    }

    public ZoneInfoDTO(String zone, Long pickups, Long dropoffs) {
        this.zone = zone;
        this.pickups = pickups;
        this.dropoffs = dropoffs;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPickups() {
        return pickups;
    }

    public void setPickups(Long pickups) {
        this.pickups = pickups;
    }

    public Long getDropoffs() {
        return dropoffs;
    }

    public void setDropoffs(Long dropoffs) {
        this.dropoffs = dropoffs;
    }
}