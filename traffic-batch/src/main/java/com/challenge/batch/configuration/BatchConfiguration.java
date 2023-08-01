package com.challenge.batch.configuration;

import com.challenge.batch.mapper.LocationFieldSetMapper;
import com.challenge.batch.mapper.TaxiTripGreenFieldSetMapper;
import com.challenge.batch.mapper.TaxiTripYellowFieldSetMapper;
import com.challenge.batch.notification.LocationNotificationListener;
import com.challenge.batch.processor.LocationProcessor;
import com.challenge.batch.processor.TaxiTripProcessor;
import com.challenge.common.entity.Location;
import com.challenge.common.entity.TaxiTrip;
import org.springframework.batch.core.Job;
import java.util.Arrays;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Location> locationReader() {
        return new FlatFileItemReaderBuilder<Location>()
                .name("locationItemReader")
                .resource(new FileSystemResource("/app/zone.csv"))
                .delimited()
                .names(new String[]{"LocationID", "Borough", "Zone", "service_zone"})
                .linesToSkip(1)
                .lineMapper(locationLineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Location>() {{
                    setTargetType(Location.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<Location> locationLineMapper() {
        final DefaultLineMapper<Location> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"LocationID", "Borough", "Zone", "service_zone"});
        final LocationFieldSetMapper fieldSetMapper = new LocationFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public LocationProcessor locationProcessor(final DataSource dataSource) {
        return new LocationProcessor(dataSource);
    }

    @Bean
    public JdbcBatchItemWriter<Location> locationWriter(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Location>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO location " +
                        "(id, borough, zone_name, service_zone, pickup_quantity, drop_off_quantity, created_at) " +
                        "VALUES(:id, :borough, :zoneName, :serviceZone, 0, 0, :createdAt) ")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public FlatFileItemReader<TaxiTrip> taxiTripGreenReader() {
        return new FlatFileItemReaderBuilder<TaxiTrip>()
                .name("taxiTripItemReaderGreen")
                .resource(new FileSystemResource("/app/green.csv"))
                .delimited()
                .names(new String[]{"Id", "VendorID", "lpep_pickup_datetime", "lpep_dropoff_datetime", "store_and_fwd_flag",
                        "RatecodeID", "PULocationID", "DOLocationID", "passenger_count", "trip_distance", "fare_amount", "extra", "mta_tax", "tip_amount",
                        "tolls_amount", "ehail_fee", "improvement_surcharge", "total_amount", "payment_type", "trip_type", "congestion_surcharge"})
                .linesToSkip(1)
                .lineMapper(taxiTripGreenLineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TaxiTrip>() {{
                    setTargetType(TaxiTrip.class);
                }})
                .build();
    }

    @Bean
    public FlatFileItemReader<TaxiTrip> taxiTripYellowReader() {
        return new FlatFileItemReaderBuilder<TaxiTrip>()
                .name("taxiTripItemReaderYellow")
                .resource(new FileSystemResource("/app/yellow.csv"))
                .delimited()
                .names(new String[]{"Id", "VendorID", "tpep_pickup_datetime", "tpep_dropoff_datetime", "passenger_count", "trip_distance", "RatecodeID",
                        "store_and_fwd_flag", "PULocationID", "DOLocationID", "payment_type", "fare_amount", "extra", "mta_tax", "tip_amount", "tolls_amount",
                        "improvement_surcharge", "total_amount", "congestion_surcharge", "airport_fee"})
                .linesToSkip(1)
                .lineMapper(taxiTripYellowLineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TaxiTrip>() {{
                    setTargetType(TaxiTrip.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<TaxiTrip> taxiTripGreenLineMapper() {
        final DefaultLineMapper<TaxiTrip> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"Id", "VendorID", "lpep_pickup_datetime", "lpep_dropoff_datetime", "store_and_fwd_flag",
                "RatecodeID", "PULocationID", "DOLocationID", "passenger_count", "trip_distance", "fare_amount", "extra", "mta_tax", "tip_amount",
                "tolls_amount", "ehail_fee", "improvement_surcharge", "total_amount", "payment_type", "trip_type", "congestion_surcharge"});
        final TaxiTripGreenFieldSetMapper fieldSetMapper = new TaxiTripGreenFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public LineMapper<TaxiTrip> taxiTripYellowLineMapper() {
        final DefaultLineMapper<TaxiTrip> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"Id", "VendorID", "tpep_pickup_datetime", "tpep_dropoff_datetime", "passenger_count", "trip_distance", "RatecodeID",
                "store_and_fwd_flag", "PULocationID", "DOLocationID", "payment_type", "fare_amount", "extra", "mta_tax", "tip_amount", "tolls_amount",
                "improvement_surcharge", "total_amount", "congestion_surcharge", "airport_fee"});
        final TaxiTripYellowFieldSetMapper fieldSetMapper = new TaxiTripYellowFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    //

    @Bean
    public TaxiTripProcessor taxiTripProcessor() {
        return new TaxiTripProcessor();
    }

    @Bean("updateLocationWriter")
    public JdbcBatchItemWriter<TaxiTrip> updateLocationWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TaxiTrip>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("UPDATE location\n" +
                        "SET drop_off_quantity = \n" +
                        "    CASE\n" +
                        "        WHEN id = :dropOffLocationId THEN drop_off_quantity + 1\n" +
                        "        ELSE drop_off_quantity\n" +
                        "    END,\n" +
                        "    pickup_quantity = \n" +
                        "    CASE\n" +
                        "        WHEN id = :pickupLocationId THEN pickup_quantity + 1\n" +
                        "        ELSE pickup_quantity\n" +
                        "    END\n" +
                        "WHERE id IN (:dropOffLocationId, :pickupLocationId)")
                .dataSource(dataSource)
                .build();
    }

    @Bean("taxiTripWriter")
    @Primary
    public JdbcBatchItemWriter<TaxiTrip> taxiTripWriter(final DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<TaxiTrip>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO taxi_trip " +
                        "(pickup_datetime, drop_off_datetime, pickup_location_id, " +
                        "drop_off_location_id, created_at) " +
                        "VALUES(:pickupDatetime, :dropOffDatetime, :pickupLocationId, :dropOffLocationId, :createdAt); ")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step stepLocations(final DataSource dataSource, JdbcBatchItemWriter<Location> writer) {
        return stepBuilderFactory.get("stepLocations")
                .<Location, Location>chunk(2000)
                .reader(locationReader())
                .processor(locationProcessor(dataSource))
                .writer(writer)
                .build();
    }

    @Bean
    public Step stepTripsGreen(@Qualifier("taxiTripWriter") JdbcBatchItemWriter<TaxiTrip> taxiTripWriter,
                               @Qualifier("updateLocationWriter") JdbcBatchItemWriter<TaxiTrip> updateLocationWriter) {

        CompositeItemWriter<TaxiTrip> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(taxiTripWriter, updateLocationWriter));

        return stepBuilderFactory.get("stepTripsGreen")
                .<TaxiTrip, TaxiTrip>chunk(20000000)
                .reader(taxiTripGreenReader())
                .processor(taxiTripProcessor())
                .writer(compositeWriter)
                .build();
    }

    @Bean
    public Step stepTripsYellow(@Qualifier("taxiTripWriter") JdbcBatchItemWriter<TaxiTrip> taxiTripWriter,
                                @Qualifier("updateLocationWriter") JdbcBatchItemWriter<TaxiTrip> updateLocationWriter) {
        CompositeItemWriter<TaxiTrip> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(taxiTripWriter, updateLocationWriter));

        return stepBuilderFactory.get("stepTripsYellow")
                .<TaxiTrip, TaxiTrip>chunk(20000000)
                .reader(taxiTripYellowReader())
                .processor(taxiTripProcessor())
                .writer(compositeWriter)
                .build();
    }

    @Bean
    public Job importData(LocationNotificationListener listener, Step stepLocations, Step stepTripsGreen, Step stepTripsYellow) {
        return jobBuilderFactory.get("importDataJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepLocations)
                .next(stepTripsGreen)
                .next(stepTripsYellow)
                .end()
                .build();
    }


}
