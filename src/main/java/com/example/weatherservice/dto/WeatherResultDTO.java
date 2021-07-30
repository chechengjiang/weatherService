package com.example.weatherservice.dto;

import java.io.Serializable;
import java.util.Date;

public class WeatherResultDTO implements Serializable {
    private String wtWindId;
    private String wtTemp;
    private String wtNm;
    private Date date;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWtWindId() {
        return wtWindId;
    }

    public void setWtWindId(String wtWindId) {
        this.wtWindId = wtWindId;
    }

    public String getWtTemp() {
        return wtTemp;
    }

    public void setWtTemp(String wtTemp) {
        this.wtTemp = wtTemp;
    }

    public String getWtNm() {
        return wtNm;
    }

    public void setWtNm(String wtNm) {
        this.wtNm = wtNm;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
