**Spring Boot Project to develop a REST Interface for storage and retrieval of charging station data**

**To Configure MongoDB locally**

Follow the instructions:

https://docs.mongodb.com/manual/administration/install-community/

https://treehouse.github.io/installation-guides/mac/mongo-mac.html


**To Run the Spring Boot application**

Using Maven plugin: 

1. mvn clean spring-boot:run

Using Jar file as a packaged applicationv: 

1. mvn clean package

2. java -jar ./target/spring-boot-charging-station-0.0.1-SNAPSHOT.jar


**REST Endpoints**

1.**GET** request to return all charging stations - /api/v1/chargingstations/

http://localhost:8080/api/v1/chargingstations/

2.**GET** request to return charging stations based on Id - /api/v1/chargingstations/{chargingStationId}
  
http://localhost:8080/api/v1/chargingstations/5c6474c6416326ddf8f10221

3.**GET** request to return charging stations based on zip code - /api/v1/chargingstations/lookup?zipcode={zipcode}

http://localhost:8080/api/v1/chargingstations/lookup?zipcode=10589

4.**GET** request to return charging stations based a perimeter around a given geolocation - /api/v1/chargingstations?longitude={longitude}&latitude={latitude}&distance={distance}

http://localhost:8080/api/v1/chargingstations?longitude=13.400954&latitude=52.520008&distance=10.0

5.**POST** request to add new charging stations - /api/v1/chargingstations

http://localhost:8080/api/v1/chargingstations

Content-Type = application/json

Body:

    {
        "zipCode":"15366",
	    "longitude": "13.700954",
        "latitude": "52.544008"
    }

**Port Number**

localhost default port number : 8080

mongodb local port number : 27017
