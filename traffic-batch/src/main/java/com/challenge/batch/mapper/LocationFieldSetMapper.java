package com.challenge.batch.mapper;

import com.challenge.common.entity.Location;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

@Component
public class LocationFieldSetMapper implements FieldSetMapper<Location> {

    @Override
    public Location mapFieldSet(FieldSet fieldSet) {
        final Location location = new Location(
                fieldSet.readInt("LocationID"),
                fieldSet.readString("Borough"),
                fieldSet.readString("Zone"),
                fieldSet.readString("service_zone"),
                Timestamp.from(Calendar.getInstance().toInstant())
        );
        return location;
    }
}

