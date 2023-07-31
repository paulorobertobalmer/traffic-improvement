package com.challenge.batch.notification;

import com.challenge.common.entity.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LocationNotificationListener extends JobExecutionListenerSupport{

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationNotificationListener(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT count(*) FROM location",
                    (rs, row) -> Integer.valueOf(
                            rs.getString(1))
            ).forEach(total -> LOGGER.info("Found <" + total + "> zones in the database."));

            jdbcTemplate.query("SELECT count(*) FROM taxi_trip",
                    (rs, row) -> Integer.valueOf(
                            rs.getString(1))
            ).forEach(total -> LOGGER.info("Found <" + total + "> trips in the database."));
        }
    }
}