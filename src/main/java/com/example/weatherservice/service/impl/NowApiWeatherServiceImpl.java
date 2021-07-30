package com.example.weatherservice.service.impl;

import com.example.weatherservice.common.EnvironmentConfig;
import com.example.weatherservice.service.IWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class NowApiWeatherServiceImpl implements IWeatherService {
    private final static Logger log = LoggerFactory.getLogger(NowApiWeatherServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    EnvironmentConfig weatherConfig;

    @Override
    public String getWeatherInfo(String cityId) {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("cityId", cityId);
        paramMap.add("appkey", weatherConfig.getWeatherApiappkey());
        paramMap.add("sign", weatherConfig.getWeatherApisign());
        paramMap.add("format", weatherConfig.getWeatherApiformat());

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, new HttpHeaders());
        ResponseEntity<String> response2 = restTemplate.postForEntity(weatherConfig.getWeatherApiUrl(), httpEntity, String.class);
        log.info("返回结果：" + response2.getBody());
        return response2.getBody();
    }
}
