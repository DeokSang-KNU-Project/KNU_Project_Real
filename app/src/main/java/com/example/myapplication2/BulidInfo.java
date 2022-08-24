package com.example.myapplication2;

public class BulidInfo {
    private String name;
    private String Info;
    private String latit;
    private String longit;

    public String getName() {
        return name;
    }

    public String getInfo() {
        return Info;
    }

    public double getLatit() {
        double lat = Double.valueOf(latit).doubleValue();
        return lat;
    }

    public double getLongit() {
        double longi = Double.valueOf(longit).doubleValue();
        return longi;
    }

    public void setBulid(String name, String Info) {
        this.name = name;
        this.Info = Info;
    }

    public void setLat(String latit, String longit) {
        this.latit = latit;
        this.longit = longit;
    }
}
