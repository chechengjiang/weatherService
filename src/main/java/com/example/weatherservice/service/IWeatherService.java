package com.example.weatherservice.service;

import com.example.weatherservice.dto.WeatherResultDTO;

public interface IWeatherService {
    String getWeatherInfo(String cityid);
}
