package com.taziya.software.entity;


public class StaticData {

    private String id;
    private String mmsi;
    private String imo;
    private String type;
    private String name;
    private String owner;
    private Float length;
    private Float width;
    private Float draft;

    public StaticData(String id, String mmsi, String imo, String type, String name, String owner, Float length, Float width, Float draft) {
        this.id = id;
        this.mmsi = mmsi;
        this.imo = imo;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.length = length;
        this.width = width;
        this.draft = draft;
    }

    public StaticData(Object id, Object mmsi, Object imo, Object type, Object name, Object owner, Object length, Object width, Object draft) {
        this.id = String.valueOf(id);
        this.mmsi = String.valueOf(mmsi);
        this.imo = String.valueOf(imo);
        this.type = String.valueOf(type);
        this.name = String.valueOf(name);
        this.owner = String.valueOf(owner);

        if(String.valueOf(length).equals("")){
            this.length = null;
        }else{
            this.length = Float.parseFloat(String.valueOf(length));
        }
        if(String.valueOf(width).equals("")){
            this.width = null;
        }else{
            this.width = Float.parseFloat(String.valueOf(width));
        }
        if(String.valueOf(draft).equals("")){
            this.draft = null;
        }else{
            this.draft = Float.parseFloat(String.valueOf(draft));
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getDraft() {
        return draft;
    }

    public void setDraft(Float draft) {
        this.draft = draft;
    }
}
