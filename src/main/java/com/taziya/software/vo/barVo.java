package com.taziya.software.vo;

import lombok.Data;

import java.util.List;

@Data
public class barVo {
    private List<String> BaseDateTime;
    private List<Float> Tcase;
    private List<Float> SOG;
    private List<Float> AirTemperature;
    private List<Float> Humidity;
    private List<Float> Pressure;
    private List<Float> Visibility;
    private List<Float> Windspeed;
    private List<Float> Watertemperature;
    private List<Float>  Salinity;
    private List<Float> Voltage;

    public List<Float> getSOG() {
        return SOG;
    }

    public void setSOG(List<Float> SOG) {
        this.SOG = SOG;
    }

    public List<Float> getAirTemperature() {
        return AirTemperature;
    }

    public void setAirTemperature(List<Float> airTemperature) {
        AirTemperature = airTemperature;
    }

    public List<Float> getHumidity() {
        return Humidity;
    }

    public void setHumidity(List<Float> humidity) {
        Humidity = humidity;
    }

    public List<Float> getPressure() {
        return Pressure;
    }

    public void setPressure(List<Float> pressure) {
        Pressure = pressure;
    }

    public List<Float> getVisibility() {
        return Visibility;
    }

    public void setVisibility(List<Float> visibility) {
        Visibility = visibility;
    }

    public List<Float> getWindspeed() {
        return Windspeed;
    }

    public void setWindspeed(List<Float> windspeed) {
        Windspeed = windspeed;
    }

    public List<Float> getWatertemperature() {
        return Watertemperature;
    }

    public void setWatertemperature(List<Float> watertemperature) {
        Watertemperature = watertemperature;
    }

    public List<Float> getSalinity() {
        return Salinity;
    }

    public void setSalinity(List<Float> salinity) {
        Salinity = salinity;
    }

    public List<Float> getVoltage() {
        return Voltage;
    }

    public void setVoltage(List<Float> voltage) {
        Voltage = voltage;
    }

    public List<Float> getTcase() {
        return Tcase;
    }

    public void setTcase(List<Float> tcase) {
        Tcase = tcase;
    }

    public List<String> getBaseDateTime() {
        return BaseDateTime;
    }

    public void setBaseDateTime(List<String> baseDateTime) {
        BaseDateTime = baseDateTime;
    }
}
