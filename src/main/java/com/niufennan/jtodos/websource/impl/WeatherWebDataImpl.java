package com.niufennan.jtodos.websource.impl;

import com.alibaba.fastjson.JSON;
import com.niufennan.jtodos.models.Weather;
import com.niufennan.jtodos.utils.HttpUtil;
import com.niufennan.jtodos.websource.WeatherWebData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class WeatherWebDataImpl implements WeatherWebData {
    public Weather getWeatherByLocation(String weatherKey, String location) {
        String url=String.format(this.serviceUrl,weatherKey,location);
        String result= HttpUtil.get(url);
        return jsonToWeather(result);
    }

    private Weather jsonToWeather(String json){
        Weather weather=new Weather();
        Map<String,Object> map = JSON.parseObject(json);
        //判断失败
        if(!map.containsKey("status")) {
            //正常情况
            //weather是result节点的第一项
            Map<String,Object> weatherMap= ((List<Map<String,Object>>)map.get("results")).get(0);

            Map<String, Object> locationJson = (Map<String, Object>) weatherMap.get("location");
            weather.setName(locationJson.get("name").toString());
            weather.setPath(locationJson.get("path").toString());
            Map<String, Object> dailyJson = ((List<Map<String, Object>>) weatherMap.get("daily")).get(0);
            weather.setWeatherdate(dailyJson.get("date").toString());
            weather.setCode_day(Integer.parseInt(dailyJson.get("code_day").toString()));
            weather.setText_day(dailyJson.get("text_day").toString());
            weather.setTemp_high(Integer.parseInt(dailyJson.get("high").toString()));
            weather.setTemp_low(Integer.parseInt(dailyJson.get("low").toString()));
            weather.setWind_direction(dailyJson.get("wind_direction").toString());
            weather.setWind_direction_degree(dailyJson.get("wind_direction_degree").toString());
            weather.setWind_scale(dailyJson.get("wind_scale").toString());
            weather.setWind_speed(dailyJson.get("wind_speed").toString());
            weather.setPrecip(dailyJson.get("precip").toString());
            weather.setIsweb(1);
            return weather;
        }
        return null;
    }
}
