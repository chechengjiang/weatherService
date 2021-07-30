package com.example.weatherservice.common;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;

@Configuration
public class ApplicationConfig {

    final int DEFAULT_CONNECT_TIMEOUT = 30;

    final int DEFAULT_READ_TIMEOUT = 30;

    @Bean
    public RestTemplate customRestTemplate(RestTemplateBuilder builder) {

        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {

            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) {
            }
        };

        ClientHttpRequestInterceptor requestInterceptor = (httpRequest, bytes, clientHttpRequestExecution) -> {
            ClientHttpResponse execute;
            try {
                execute = clientHttpRequestExecution.execute(httpRequest, bytes);
            } catch (IOException e) {
                throw e;
            }
            return execute;
        };
        return builder.setConnectTimeout(Duration.ofSeconds(DEFAULT_CONNECT_TIMEOUT))
                .setReadTimeout(Duration.ofSeconds(DEFAULT_READ_TIMEOUT))
                // 自定义拦截器
                .interceptors(requestInterceptor)
                // 自定义异常处理
                .errorHandler(responseErrorHandler)
                .build();
    }
}
