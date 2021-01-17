package com.taziya.software.utils;

import java.io.Serializable;
import java.util.Date;

public class Boat implements Serializable {
    private Integer key;
    private String VesselID;
    private Integer TimeFormat;
    private Date BaseDateTime;
    private String BDT;

    @Override
    public String toString() {
        return "Boat{" +
                "key=" + key +
                ", VesselID='" + VesselID + '\'' +
                ", TimeFormat=" + TimeFormat +
                ", BaseDateTime=" + BaseDateTime +
                ", BDT='" + BDT + '\'' +
                ", SOG=" + SOG +
                ", COG=" + COG +
                ", LAT=" + LAT +
                ", LON=" + LON +
                ", AirTemperature=" + AirTemperature +
                ", Humidity=" + Humidity +
                ", Pressure=" + Pressure +
                ", Visibility=" + Visibility +
                ", WindSpeed=" + WindSpeed +
                ", windDirection=" + windDirection +
                ", WaterTemperature=" + WaterTemperature +
                ", Salinity=" + Salinity +
                ", Voltage=" + Voltage +
                ", Tcase=" + Tcase +
                '}';
    }

    public String getBDT() {
        return BDT;
    }

    public void setBDT(String BDT) {
        this.BDT = BDT;
    }

    private Float SOG;
    private Float COG;
//    纬度
    private Float LAT;
    private Float LON;
    private Float AirTemperature;
    private Float Humidity;
    private Float Pressure;
    private Float Visibility;
    private Float WindSpeed;
    private Float windDirection;
    private Float WaterTemperature;
    private Float Salinity;
    /**
     * 工作电压
     */
    private Float Voltage;
    /**
     * 机箱温度
     */
    private Float Tcase;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setVesselID(String vesselID) {
        VesselID = vesselID;
    }


    public String getVesselID() {
        return VesselID;
    }

    public Integer getTimeFormat() {
        return TimeFormat;
    }

    public void setTimeFormat(Integer timeFormat) {
        TimeFormat = timeFormat;
    }

    public Date getBaseDateTime() {
        return BaseDateTime;
    }

    public void setBaseDateTime(Date baseDateTime) {
        BaseDateTime = baseDateTime;
    }

    public Float getSOG() {
        return SOG;
    }

    public void setSOG(Float SOG) {
        this.SOG = SOG;
    }

    public Float getCOG() {
        return COG;
    }

    public void setCOG(Float COG) {
        this.COG = COG;
    }

    public Float getLAT() {
        return LAT;
    }

    public void setLAT(Float LAT) {
        this.LAT = LAT;
    }

    public Float getLON() {
        return LON;
    }

    public void setLON(Float LON) {
        this.LON = LON;
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

    public Float getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        WindSpeed = windSpeed;
    }

    public Float getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Float windDirection) {
        this.windDirection = windDirection;
    }

    public Float getWaterTemperature() {
        return WaterTemperature;
    }

    public void setWaterTemperature(Float waterTemperature) {
        WaterTemperature = waterTemperature;
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

    public Float getTcase() {
        return Tcase;
    }

    public void setTcase(Float tcase) {
        Tcase = tcase;
    }

}
