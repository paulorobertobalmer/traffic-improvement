package com.challenge.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "location")
public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String borough;
        private String zoneName;
        private String serviceZone;
        private Integer pickupQuantity;
        private Integer dropOffQuantity;
        private Timestamp createdAt;

        public Location() {
        }

        public Location(Integer id, String borough, String zoneName, String serviceZone,
                        Integer pickupQuantity, Integer dropOffQuantity, Timestamp createdAt) {
                this.id = id;
                this.borough = borough;
                this.zoneName = zoneName;
                this.serviceZone = serviceZone;
                this.pickupQuantity = pickupQuantity;
                this.dropOffQuantity = dropOffQuantity;
                this.createdAt = createdAt;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getBorough() {
                return borough;
        }

        public void setBorough(String borough) {
                this.borough = borough;
        }

        public String getZoneName() {
                return zoneName;
        }

        public void setZoneName(String zoneName) {
                this.zoneName = zoneName;
        }

        public String getServiceZone() {
                return serviceZone;
        }

        public void setServiceZone(String serviceZone) {
                this.serviceZone = serviceZone;
        }

        public Integer getPickupQuantity() {
                return pickupQuantity;
        }

        public void setPickupQuantity(Integer pickupQuantity) {
                this.pickupQuantity = pickupQuantity;
        }

        public Integer getDropOffQuantity() {
                return dropOffQuantity;
        }

        public void setDropOffQuantity(Integer dropOffQuantity) {
                this.dropOffQuantity = dropOffQuantity;
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
                Location location = (Location) o;
                return Objects.equals(id, location.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}
