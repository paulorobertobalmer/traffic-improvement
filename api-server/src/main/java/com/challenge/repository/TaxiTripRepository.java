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

    @Query("SELECT NEW com.challenge.dto.ZoneInfoDTO(l.zoneName, " +
            "(SELECT COUNT(t1) FROM TaxiTrip t1 WHERE t1.pickupLocationId = l.id AND t1.pickupDatetime BETWEEN :specificDateStart AND :specificDateEnd), " +
            "(SELECT COUNT(t2) FROM TaxiTrip t2 WHERE t2.dropOffLocationId = l.id AND t2.dropOffDatetime BETWEEN :specificDateStart AND :specificDateEnd)) " +
            "FROM Location l " +
            "WHERE l.id = :zoneId")
    ZoneInfoDTO getZoneInfoOnDate(Integer zoneId, Date specificDateStart, Date specificDateEnd);

}
