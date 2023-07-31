package com.challenge.service;

import com.challenge.common.entity.Location;
import com.challenge.dto.TopZoneDTO;
import com.challenge.exception.BusinessException;
import com.challenge.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<TopZoneDTO> getTopZonesWithTotalPickupsAndDropOffs(String orderBy) throws BusinessException {
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<Location> records = null;
        if (orderBy != null && orderBy.equals("pickups")) {
            records = locationRepository.findTopZonesWithTotalPickupsAndDropOffsOrderByPickups(pageRequest);
        } else if (orderBy != null && orderBy.equals("dropoffs")) {
            records = locationRepository.findTopZonesWithTotalPickupsAndDropOffsOrderByDropoffs(pageRequest);
        } else {
            throw new BusinessException("Parameter order need to be informed. Options: [pickups,dropoffs]");
        }
        List<TopZoneDTO> returnData = new ArrayList<>();
        if (records != null) {
            for (Location location : records) {
                returnData.add(new TopZoneDTO(location.getZoneName(), location.getPickupQuantity(), location.getDropOffQuantity()));
            }
        }
        return returnData;

    }


}
