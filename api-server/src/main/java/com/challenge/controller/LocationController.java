package com.challenge.controller;

import com.challenge.exception.BusinessException;
import com.challenge.pattern.StandardError;
import com.challenge.service.LocationService;
import com.challenge.service.TaxiTripService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
public class LocationController {

    private final LocationService locationService;

    private final TaxiTripService taxiTripService;

    public LocationController(LocationService locationService, TaxiTripService taxiTripService) {
        this.locationService = locationService;
        this.taxiTripService = taxiTripService;
    }

    @RequestMapping(value = "top-zones", method = RequestMethod.GET)
    public ResponseEntity top5Zones(@RequestParam(name = "order") String orderBy)  {
        try {
            return ResponseEntity.ok(locationService.getTopZonesWithTotalPickupsAndDropOffs(orderBy));
        } catch (BusinessException ex) {
            StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @RequestMapping(value = "zone-trips", method = RequestMethod.GET)
    public ResponseEntity zoneTripInfo(@RequestParam(name = "zone") Integer zoneId, @RequestParam(name = "date")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)  {
        try {
            return ResponseEntity.ok(taxiTripService.getZoneInfo(zoneId, date));
        } catch (BusinessException ex) {
            StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

}
