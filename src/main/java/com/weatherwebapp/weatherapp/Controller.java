package com.weatherwebapp.weatherapp;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


public class Controller {
    private WeatherService weatherService;
    String country = "Turkey";
    String city = "Bursa";

    public WeatherE controller(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        weatherService = new WeatherService(restTemplateBuilder);

        WeatherE weather = weatherService.getWeather(country, city);


        return weather;
    }

}
