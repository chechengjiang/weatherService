package com.example.weatherservice.service.impl;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.example.weatherservice.common.EnvironmentConfig;
import com.example.weatherservice.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BaiduApiWeatherServiceImpl implements IWeatherService {

    @Autowired
    EnvironmentConfig weatherConfig;

    @Override
    public String getWeatherInfo(String cityId) {
        String path = weatherConfig.getBaiduWeatherApiUrl();
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.GET, path);
        request.setCredentials(weatherConfig.getBaiduAccessKey(), weatherConfig.getBaiduSerectkey());
        request.addHeaderParameter("Content-Type", "application/json; charset=utf-8");
        request.addQueryParameter("cityid", cityId);
        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());
        ApiExplorerResponse response = null;
        try {
            response = client.sendRequest(request);
            System.out.println(response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(response).orElse(new ApiExplorerResponse()).getResult();
    }
}
