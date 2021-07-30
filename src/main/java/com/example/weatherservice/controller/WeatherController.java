package com.example.weatherservice.controller;

import com.example.weatherservice.dto.WeatherResultDTO;
import com.example.weatherservice.service.impl.WeatherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("weather")
@RestController
public class WeatherController {

    @Autowired
    WeatherServiceImpl weatherServiceImpl;

    @RequestMapping("getInfo")
    public WeatherResultDTO getWeather(String cityId) {
        return weatherServiceImpl.getWeatherInfo(cityId);
    }
}
