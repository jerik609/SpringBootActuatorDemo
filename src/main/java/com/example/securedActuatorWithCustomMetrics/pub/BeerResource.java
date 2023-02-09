package com.example.securedActuatorWithCustomMetrics.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beer")
public class BeerResource {

    private BeerService beerService;

    @Autowired
    BeerResource(BeerService beerService) {
        this.beerService = beerService;
    }

    @PostMapping("/light")
    public ResponseEntity<String> orderLightBeer() {
        final var order = new Order("light");
        beerService.orderBeer(order);
        return new ResponseEntity<>("Light beer ordered", HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/ale")
    public ResponseEntity<String> aleOrdered() {
        final var order = new Order("ale");
        beerService.orderBeer(order);
        return new ResponseEntity<>("Ale ordered", HttpStatus.I_AM_A_TEAPOT);
    }

}
