package com.taziya.software.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class vesselname {
    private String id;
    private String mmsi;
    private String imo;
    private String type;
    private String name;
    private String owner;
    private Float length;
    private Float width;
    private Float draft;
    @TableLogic
    private Integer deleted;

//    public vesselname(Object id, Object mmsi, Object imo, Object type, Object name, Object owner, Object length, Object width, Object draft) {
//        this.id = String.valueOf(id);
//        this.mmsi = String.valueOf(mmsi);
//        this.imo = String.valueOf(imo);
//        this.type = String.valueOf(type);
//        this.name = String.valueOf(name);
//        this.owner = String.valueOf(owner);
//        this.length = Float.parseFloat(String.valueOf(length));
//        this.width = Float.parseFloat(String.valueOf(width));
//        this.draft = Float.parseFloat(String.valueOf(draft));
//    }
}
