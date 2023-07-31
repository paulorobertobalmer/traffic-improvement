package com.challenge.service;

import com.challenge.common.entity.Location;
import com.challenge.dto.TopZoneDTO;
import com.challenge.dto.ZoneInfoDTO;
import com.challenge.exception.BusinessException;
import com.challenge.repository.TaxiTripRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TaxiTripService {

    private final TaxiTripRepository taxiTripRepository;


    public TaxiTripService(TaxiTripRepository taxiTripRepository) {
        this.taxiTripRepository = taxiTripRepository;
    }

    public ZoneInfoDTO getZoneInfo(Integer zoneId, Date date) throws BusinessException {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.HOUR, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(date);
        endDate.set(Calendar.HOUR, 23);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.SECOND, 59);

        ZoneInfoDTO returnData = taxiTripRepository.getZoneInfoOnDate(zoneId, startDate.getTime(), endDate.getTime());
        returnData.setDate(date);
        return returnData;


    }

}
