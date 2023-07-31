package com.challenge.batch.mapper;

import com.challenge.common.entity.TaxiTrip;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

@Component
public class TaxiTripYellowFieldSetMapper implements FieldSetMapper<TaxiTrip> {

    @Override
    public TaxiTrip mapFieldSet(FieldSet fieldSet) {
        final TaxiTrip taxiTrip = new TaxiTrip(
                null,
                Timestamp.from(fieldSet.readDate("tpep_pickup_datetime").toInstant()),
                Timestamp.from(fieldSet.readDate("tpep_dropoff_datetime").toInstant()),
                fieldSet.readInt("PULocationID"),
                fieldSet.readInt("DOLocationID"),
                Timestamp.from(Calendar.getInstance().toInstant())
        );
        return taxiTrip;
    }
}
