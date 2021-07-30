package com.example.weatherservice.service.impl;

import com.example.weatherservice.common.ServiceLoader;
import com.example.weatherservice.dto.WeatherResultDTO;
import com.example.weatherservice.service.IWeatherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class WeatherServiceImpl {

    @Autowired
    ServiceLoader serviceLoader;

    public WeatherResultDTO getWeatherInfo(String cityId) {
        String result = null;
        for (IWeatherService service : serviceLoader.getMap().values()) {
            result = service.getWeatherInfo(cityId);
            if (StringUtils.isNotEmpty(result)) break;
        }
        return transform(decode(result));
    }

    public WeatherResultDTO transform(String json) {
        WeatherResultDTO weatherResultDTO = new WeatherResultDTO();

        //TODO 配置文件获取不同数据源的意义相同属性 反射注入
        List<String> wtWindIdList = Arrays.asList("win_speed", "wtWindId");
        List<String> dateList = Arrays.asList("update_time", "week");
        List<String> wtTempList = Arrays.asList("tem1", "wtTemp");
        List<String> wtNmList = Arrays.asList("wea", "wtNm");
        List<String> cityList = Arrays.asList("cityEn", "area_2");

        wtWindIdList.forEach(wtWind -> {
            if (StringUtils.isNotEmpty(substringTarget(json, wtWind))) {
                weatherResultDTO.setWtWindId(substringTarget(json, wtWind));
            }
            ;
        });

        dateList.forEach(dateitem -> {
            if (StringUtils.isNotEmpty(substringTarget(json, dateitem))) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date = simpleDateFormat.parse(substringTarget(json, dateitem));
                    weatherResultDTO.setDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        wtTempList.forEach(tempitem -> {
            if (StringUtils.isNotEmpty(substringTarget(json, tempitem))) {
                weatherResultDTO.setWtTemp(substringTarget(json, tempitem)+"℃");
            }
            ;
        });

        wtNmList.forEach(wtNmitem -> {
            if (StringUtils.isNotEmpty(substringTarget(json, wtNmitem))) {
                weatherResultDTO.setWtNm(substringTarget(json, wtNmitem));
            }
            ;
        });

        cityList.forEach(cityItem -> {
            if (StringUtils.isNotEmpty(substringTarget(json, cityItem))) {
                weatherResultDTO.setCity(substringTarget(json, cityItem));
            }
            ;
        });
        return weatherResultDTO;
    }

    public String substringTarget(String json, String targetName) {
        if (json.indexOf(targetName) > 0) {
            return json.substring(json.indexOf(":", json.indexOf(targetName)) + 2, json.indexOf(",", json.indexOf(targetName)) - 1);
        }
        return null;
    }

    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5)
                        && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
                        .charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(
                                unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }
}
