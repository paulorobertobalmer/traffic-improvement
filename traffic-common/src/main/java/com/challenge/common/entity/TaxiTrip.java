package com.challenge.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "taxi_trip")
public class TaxiTrip {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private Timestamp pickupDatetime;
        private Timestamp dropOffDatetime;
        private Integer pickupLocationId;
        private Integer dropOffLocationId;
        private Timestamp createdAt;

        public TaxiTrip() {
        }

        public TaxiTrip(Integer id, Timestamp pickupDatetime, Timestamp dropOffDatetime,
                        Integer pickupLocationId, Integer dropOffLocationId, Timestamp createdAt) {
                this.id = id;
                this.pickupDatetime = pickupDatetime;
                this.dropOffDatetime = dropOffDatetime;
                this.pickupLocationId = pickupLocationId;
                this.dropOffLocationId = dropOffLocationId;
                this.createdAt = createdAt;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Timestamp getPickupDatetime() {
                return pickupDatetime;
        }

        public void setPickupDatetime(Timestamp pickupDatetime) {
                this.pickupDatetime = pickupDatetime;
        }

        public Timestamp getDropOffDatetime() {
                return dropOffDatetime;
        }

        public void setDropOffDatetime(Timestamp dropOffDatetime) {
                this.dropOffDatetime = dropOffDatetime;
        }

        public Integer getPickupLocationId() {
                return pickupLocationId;
        }

        public void setPickupLocationId(Integer pickupLocationId) {
                this.pickupLocationId = pickupLocationId;
        }

        public Integer getDropOffLocationId() {
                return dropOffLocationId;
        }

        public void setDropOffLocationId(Integer dropOffLocationId) {
                this.dropOffLocationId = dropOffLocationId;
        }

        public Timestamp getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Timestamp createdAt) {
                this.createdAt = createdAt;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                TaxiTrip taxiTrip = (TaxiTrip) o;
                return Objects.equals(id, taxiTrip.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}