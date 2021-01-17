package com.taziya.software.utils;

import java.io.Serializable;

public class VesselName implements Serializable {

    private String id;
    private String mmsi;
    private String imo;
    private String type;
    private String name;
    private String owner;
    private float length;
    private float width;
    private float draft;

    @Override
    public String toString() {
        return "VesselName{" +
                "id='" + id + '\'' +
                ", mmsi='" + mmsi + '\'' +
                ", imo='" + imo + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", draft=" + draft +
                '}';
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

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getDraft() {
        return draft;
    }

    public void setDraft(float draft) {
        this.draft = draft;
    }
}
