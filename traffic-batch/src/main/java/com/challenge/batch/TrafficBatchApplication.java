package com.challenge.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

@SpringBootApplication
public class TrafficBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficBatchApplication.class, args);
	}

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job csvJob;

	public void run(String... args) throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(csvJob, jobParameters);
	}

}
