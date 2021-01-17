package com.taziya.software.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class Vessel {
    @TableId
    private Integer id;
    private String vesselid;
    private Integer timeformat;
    private Date basedatetime;
    private Float sog;
    private Float cog;
    private Float lat;
    private Float lon;
    private Float airtemperature;
    private Float humidity;
    private Float pressure;
    private Float visibility;
    private Float windspeed;
    private Float winddirection;
    private Float watertemperature;
    private Float salinity;
    private Float voltage;
    private Float tcase;
    @TableLogic
    private Integer deleted;
}
