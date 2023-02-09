# SpringBootActuatorDemo

Spring Boot Actuator examples - secured custom metrics :-)

## Updating counters and querying them:

- POST: http://localhost:8080/beer/light
- GET: http://localhost:8080/actuator/metrics/beer.orders?tag=type:light

## Links

- https://autsoft.net/defining-custom-metrics-in-a-spring-boot-application-using-micrometer/
- https://blog.davidvassallo.me/2018/03/13/drill-down-into-spring-boot-actuator-metrics/
