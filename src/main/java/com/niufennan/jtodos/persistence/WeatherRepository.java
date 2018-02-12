package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository  extends JpaRepository<Weather,Integer> {
    public Weather findFirstByWeatherdateAndNameAndIsweb(String date,String name,int isWeb);
}
