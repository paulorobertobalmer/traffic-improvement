package com.challenge.repository;

import com.challenge.common.entity.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("Select l from Location l order by l.pickupQuantity desc ")
    List<Location> findTopZonesWithTotalPickupsAndDropOffsOrderByPickups(Pageable pageable);

    @Query("Select l from Location l order by l.dropOffQuantity desc ")
    List<Location> findTopZonesWithTotalPickupsAndDropOffsOrderByDropoffs(Pageable pageable);


}
