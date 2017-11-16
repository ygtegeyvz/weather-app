package com.weatherwebapp.weatherapp;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherE getWeather(String country, String city) {
        URI url = new UriTemplate("http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID=fb33ac8c33ef1aa5cb7aeac6db8c4b76").expand(city, country);
        System.out.println(url);
        return invoke(url, WeatherE.class);
    }
    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);
        return exchange.getBody();
    }
}
