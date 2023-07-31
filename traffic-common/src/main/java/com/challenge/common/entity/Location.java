package com.challenge.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "location")
public record Location (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id,
        String borough,
        String zoneName,
        String serviceZone,
        Timestamp createdAt
) {

}