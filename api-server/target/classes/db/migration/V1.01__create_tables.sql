CREATE TABLE location (
	id integer NOT NULL,
    borough varchar(200) NOT NULL,
    zone_name varchar(200) NOT NULL,
    service_zone varchar(200) NOT NULL,
    pickup_quantity integer NOT NULL,
    drop_off_quantity integer NOT NULL,
    created_at timestamp NOT NULL,
	CONSTRAINT location_pkey PRIMARY KEY (id)
);

CREATE TABLE taxi_trip (
	id serial PRIMARY KEY,
    pickup_datetime timestamp NOT NULL,
    drop_off_datetime  timestamp NOT NULL,
	pickup_location_id integer NOT NULL,
	drop_off_location_id integer NOT NULL,
    created_at timestamp NOT NULL
);

ALTER TABLE ONLY taxi_trip
    ADD CONSTRAINT fk_taxi_trip_pickup_location_id FOREIGN KEY (pickup_location_id) REFERENCES location(id);

ALTER TABLE ONLY taxi_trip
    ADD CONSTRAINT fk_taxi_trip_drop_off_location_id FOREIGN KEY (drop_off_location_id) REFERENCES location(id);
