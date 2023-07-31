package com.challenge.batch.processor;

import com.challenge.common.entity.TaxiTrip;
import org.springframework.batch.item.ItemProcessor;

public class TaxiTripProcessor implements ItemProcessor<TaxiTrip, TaxiTrip> {
    @Override
    public TaxiTrip process(final TaxiTrip taxiTrip) {
        return taxiTrip;
    }
}


