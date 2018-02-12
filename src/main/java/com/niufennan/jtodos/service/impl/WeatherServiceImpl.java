package com.niufennan.jtodos.service.impl;

import com.niufennan.jtodos.models.DictionaryItem;
import com.niufennan.jtodos.models.Weather;
import com.niufennan.jtodos.persistence.DictionaryItemRepository;
import com.niufennan.jtodos.persistence.WeatherRepository;
import com.niufennan.jtodos.service.WeatherService;
import com.niufennan.jtodos.websource.WeatherWebData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherWebData weatherWebData;

    @Autowired
    private DictionaryItemRepository dictionaryItemRepository;

    private static String weatherKey="";

    private String getWeatherKey(){
        if(WeatherServiceImpl.weatherKey.equals("")){
            //查询字典表
            List<DictionaryItem> dicList=dictionaryItemRepository.findByDicname("weatherKey");
            if(dicList.size()>0)
                weatherKey= dicList.get(0).getDicvalue();
        }
        return weatherKey;
    }

    public Weather weather(String address) {
        //查询db中是否有此日此地天气
        Weather weather=getWeatherByDb(address,(new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
        if(weather==null){
            //如果没有，则查询，并存储到db 返回新内容
            weather= weatherWebData.getWeatherByLocation(getWeatherKey(),address);
            weather =  saveWeather(weather);
        }
        return weather;

    }
    private Weather saveWeather(Weather weather){
        return weatherRepository.saveAndFlush(weather);
    }
    private Weather getWeatherByDb(String address, String date){
        return  weatherRepository.findFirstByWeatherdateAndNameAndIsweb(date,address,1);
    }
}
