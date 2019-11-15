# Octo Events

Octo Events is an application that listens to Github Events via webhooks and expose by an api for later use.

### Run Application without Docker:

Pre-Requisites:
* JDK 12 or  more;
* Maven.

##### Execute to run:
```shell script
mvn clean package && java -jar target/octo-events.jar
```

### Run Application with Docker:

Pre-Requisites:
* Docker
* Docker Compose

#### Execute to run:
``` shell script
docker-compose up
```