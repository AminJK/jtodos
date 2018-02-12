package com.niufennan.jtodos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "weather")
public class Weather {
    private Integer id;
    private String name;
    private String path;
    private String weatherdate;
    private String text_day;
    private Integer code_day;
    private Integer temp_high;
    private Integer temp_low;
    private String  precip;
    private String wind_direction;
    private String wind_direction_degree;
    private String wind_speed;
    private String wind_scale;
    private Integer isweb;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWeatherdate() {
        return weatherdate;
    }

    public void setWeatherdate(String weatherdate) {
        this.weatherdate = weatherdate;
    }

    public String getText_day() {
        return text_day;
    }

    public void setText_day(String text_day) {
        this.text_day = text_day;
    }

    public Integer getCode_day() {
        return code_day;
    }

    public void setCode_day(Integer code_day) {
        this.code_day = code_day;
    }

    public Integer getTemp_high() {
        return temp_high;
    }

    public void setTemp_high(Integer temp_high) {
        this.temp_high = temp_high;
    }

    public Integer getTemp_low() {
        return temp_low;
    }

    public void setTemp_low(Integer temp_low) {
        this.temp_low = temp_low;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_direction_degree() {
        return wind_direction_degree;
    }

    public void setWind_direction_degree(String wind_direction_degree) {
        this.wind_direction_degree = wind_direction_degree;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(String wind_scale) {
        this.wind_scale = wind_scale;
    }

    public Integer getIsweb() {
        return isweb;
    }

    public void setIsweb(Integer isweb) {
        this.isweb = isweb;
    }
}
