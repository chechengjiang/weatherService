package com.example.weatherservice.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfig {

    @Value("${k780.url.api.weather}")
    private String weatherApiUrl;

    @Value("${k780.appkey}")
    private String weatherApiappkey;

    @Value("${k780.sign}")
    private String weatherApisign;

    @Value("${k780.format}")
    private String weatherApiformat;

    @Value("${baidu.url.api.weather}")
    private String baiduWeatherApiUrl;

    @Value("${baidu.accesskey}")
    private String baiduAccessKey;

    @Value("${baidu.serectkey}")
    private String baiduSerectkey;

    public String getBaiduWeatherApiUrl() {
        return baiduWeatherApiUrl;
    }

    public String getBaiduAccessKey() {
        return baiduAccessKey;
    }

    public String getBaiduSerectkey() {
        return baiduSerectkey;
    }

    public String getWeatherApiUrl() {
        return weatherApiUrl;
    }

    public String getWeatherApiappkey() {
        return weatherApiappkey;
    }

    public String getWeatherApisign() {
        return weatherApisign;
    }

    public String getWeatherApiformat() {
        return weatherApiformat;
    }
}
