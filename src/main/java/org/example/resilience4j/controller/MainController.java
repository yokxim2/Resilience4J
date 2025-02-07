package org.example.resilience4j.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.resilience4j.component.Rest1Comp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {

    private Rest1Comp rest1Comp;

    @Autowired
    public MainController(Rest1Comp rest1Comp) {

        this.rest1Comp = rest1Comp;
    }

    @CircuitBreaker(name = "MainControllerMethod1", fallbackMethod = "fallBackMethod")
    @GetMapping("/")
    public String mainP() {

        return rest1Comp.restTemplate1().getForObject("/data", String.class);
    }

    private String fallBackMethod(Throwable throwable) {

        return throwable.getMessage();
    }
}
