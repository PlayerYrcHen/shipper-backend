package com.taziya.software.vo;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

@Data
public class GeoJsonVO {
    private String vesselId;
    private Integer timeFormat;
    private String basedateTime;
    private Float sog;
    private Float cog;
    private Float lat;
    private Float lon;
    private Float airTemperature;
    private Float humidity;
    private Float pressure;
    private Float visibility;
    private Float windSpeed;
    private Float windDirection;
    private Float waterTemperature;
    private Float salinity;
    private Float voltage;
    private Float tCase;
}
