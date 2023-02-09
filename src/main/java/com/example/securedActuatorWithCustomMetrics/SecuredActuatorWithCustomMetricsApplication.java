package com.example.securedActuatorWithCustomMetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://autsoft.net/defining-custom-metrics-in-a-spring-boot-application-using-micrometer/
// https://blog.davidvassallo.me/2018/03/13/drill-down-into-spring-boot-actuator-metrics/

@SpringBootApplication
public class SecuredActuatorWithCustomMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuredActuatorWithCustomMetricsApplication.class, args);
	}

}
