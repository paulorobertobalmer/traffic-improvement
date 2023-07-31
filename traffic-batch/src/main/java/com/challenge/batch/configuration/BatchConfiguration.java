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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .resource(new FileSystemResource("/home/paulo/manual-da-programacao/projetos/traffic-data/zone.csv"))
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
                        "(id, borough, zone_name, service_zone, created_at) " +
                        "VALUES(:id, :borough, :zoneName, :serviceZone, :createdAt) ")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public FlatFileItemReader<TaxiTrip> taxiTripGreenReader() {
        return new FlatFileItemReaderBuilder<TaxiTrip>()
                .name("taxiTripItemReaderGreen")
                .resource(new FileSystemResource("/home/paulo/manual-da-programacao/projetos/traffic-data/green.csv"))
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
                .resource(new FileSystemResource("/home/paulo/manual-da-programacao/projetos/traffic-data/yellow.csv"))
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

    @Bean
    public JdbcBatchItemWriter<TaxiTrip> taxiTripWriter(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TaxiTrip>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO taxi_trip " +
                        "(pickup_datetime, drop_off_datetime, pickup_location_id, " +
                        "drop_off_location_id, created_at) " +
                        "VALUES(:pickupDatetime, :dropOffDatetime, :pickupLocationId, :dropOffLocationId, :createdAt);\n ")
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
    public Step stepTripsGreen(JdbcBatchItemWriter<TaxiTrip> writer) {
        return stepBuilderFactory.get("stepTripsGreen")
                .<TaxiTrip, TaxiTrip>chunk(2000)
                .reader(taxiTripGreenReader())
                .processor(taxiTripProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step stepTripsYellow(JdbcBatchItemWriter<TaxiTrip> writer) {
        return stepBuilderFactory.get("stepTripsYellow")
                .<TaxiTrip, TaxiTrip>chunk(2000)
                .reader(taxiTripYellowReader())
                .processor(taxiTripProcessor())
                .writer(writer)
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
