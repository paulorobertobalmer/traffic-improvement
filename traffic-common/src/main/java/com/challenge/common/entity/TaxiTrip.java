package com.challenge.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "taxi_trip")
public record TaxiTrip(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id,
        Timestamp pickupDatetime,
        Timestamp dropOffDatetime,
        Integer pickupLocationId,
        Integer dropOffLocationId,
        Timestamp createdAt
) {

}