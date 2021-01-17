package com.taziya.software.vo;

import lombok.Data;

@Data
public class chartsVo {
    private Float Tcase;
    private String BaseDateTime;
    private Float SOG;
    private Float AirTemperature;
    private Float Humidity;
    private Float Pressure;
    private Float Visibility;
    private Float Windspeed;
    private Float Watertemperature;
    private Float Salinity;
    private Float Voltage;

    public Float getSOG() {
        return SOG;
    }

    public void setSOG(Float SOG) {
        this.SOG = SOG;
    }

    public Float getAirTemperature() {
        return AirTemperature;
    }

    public void setAirTemperature(Float airTemperature) {
        AirTemperature = airTemperature;
    }

    public Float getHumidity() {
        return Humidity;
    }

    public void setHumidity(Float humidity) {
        Humidity = humidity;
    }

    public Float getPressure() {
        return Pressure;
    }

    public void setPressure(Float pressure) {
        Pressure = pressure;
    }

    public Float getVisibility() {
        return Visibility;
    }

    public void setVisibility(Float visibility) {
        Visibility = visibility;
    }

    public Float getWindspeed() {
        return Windspeed;
    }

    public void setWindspeed(Float windspeed) {
        Windspeed = windspeed;
    }

    public Float getWatertemperature() {
        return Watertemperature;
    }

    public void setWatertemperature(Float watertemperature) {
        Watertemperature = watertemperature;
    }

    public Float getSalinity() {
        return Salinity;
    }

    public void setSalinity(Float salinity) {
        Salinity = salinity;
    }

    public Float getVoltage() {
        return Voltage;
    }

    public void setVoltage(Float voltage) {
        Voltage = voltage;
    }

    /**
     * 工作电压
     */


    public Float getTcase() {
        return Tcase;
    }

    public void setTcase(Float tcase) {
        Tcase = tcase;
    }

    public String getBaseDateTime() {
        return BaseDateTime;
    }

    public void setBaseDateTime(String baseDateTime) {
        BaseDateTime = baseDateTime;
    }
}
