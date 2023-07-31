package com.challenge.repository;


import com.challenge.dto.ZoneInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.challenge.common.entity.TaxiTrip;

import java.util.Date;
import java.util.List;

@Repository
public interface TaxiTripRepository extends JpaRepository<TaxiTrip, Integer> {

    @Query("SELECT NEW com.challenge.dto.ZoneInfoDTO(l.zoneName, COUNT(t.pickupLocationId), COUNT(t.dropOffLocationId)) " +
            "FROM TaxiTrip t " +
            "JOIN Location l ON t.pickupLocationId = l.id AND t.dropOffLocationId = l.id " +
            "WHERE t.pickupDatetime >= :specificDateStart AND t.dropOffDatetime <= :specificDateEnd and l.id = :zoneId " +
            "GROUP BY l.zoneName")
    ZoneInfoDTO getZoneInfoOnDate(Integer zoneId, Date specificDateStart, Date specificDateEnd);

}
