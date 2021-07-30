package com.example.weatherservice.common;


import com.example.weatherservice.service.IWeatherService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceLoader implements ApplicationContextAware {

    private Map<String, IWeatherService> map;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map = applicationContext.getBeansOfType(IWeatherService.class);
    }

    public Map<String, IWeatherService> getMap() {
        return map;
    }
}
