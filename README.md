# **Getting Started**

### **What this is**

This is a system to improve traffic in our city.
This project uses Spring Boot with Rest API's and Spring Batch Application.

The architecture was designed so that the system has a Spring Boot application with REST endpoints available for consumption on port 8080. We provide a Postman collection for testing.

Additionally, to import the data, a Spring Batch application was created to read the green.csv, yellow.csv, and zone.csv files and store the data in the database.

For local testing, the available docker-compose.yml file initializes the PostgreSQL database and also starts the application where the REST APIs are available. Furthermore, it runs the container with the Spring Batch application to import the data into the database.

In the command below, there is also a command to monitor the logs of the batch application.

In a production scenario, the files would be stored in an AWS S3 bucket, and the Batch application would have an additional step to download the files to read them locally. In this environment, the container would be executed using the AWS Batch service.

### **Requirements**

 - Maven
 - Java 17
 - Docker

### **Start-up**

Before run the commands above, copy files *zone.csv*, *green.csv* and *yellow.csv* to _data-processing/files_ directory.

Run the following commands in the command line:
    
    To build images and run database and api application:

    - docker-compose up --build -d && docker logs --follow traffic-batch


### **Instructions**

The postman collection is avaliable to consume Rest endpoints.


