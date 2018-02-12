package com.niufennan.jtodos.websource;

import com.niufennan.jtodos.models.Weather;
import org.springframework.stereotype.Repository;


public interface WeatherWebData {
    String serviceUrl="https://api.seniverse.com/v3/weather/daily.json?key=%s&location=%s&language=zh-Hans&unit=c&start=0&days=1";
    public Weather getWeatherByLocation(String weatherKey, String location);
}
