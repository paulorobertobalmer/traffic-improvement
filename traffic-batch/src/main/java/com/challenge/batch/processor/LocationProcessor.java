package com.challenge.batch.processor;

import com.challenge.common.entity.Location;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class LocationProcessor implements ItemProcessor<Location, Location>{

    private final JdbcTemplate jdbcTemplate;

    public LocationProcessor(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Location process(Location location) throws Exception {
        if (locationExists(location.getId())) {
            return null;
        }
        return location;
    }

    private boolean locationExists(int locationId) {
        try {
            // Check if the location with the given ID exists in the database.
            jdbcTemplate.queryForObject("SELECT id FROM location WHERE id = ?", Integer.class, locationId);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
    }

}


