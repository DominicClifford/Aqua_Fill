package com.example.aquafill;

//this class stores the object locations displayed in list view
public class Location_Info {

    private String locType;
    private String locName;
    private String locDistance;

    private String locLongitude;
    private String locLatitude;

    //constructor
    public Location_Info(String locType, String locName, String locDistance, String locLongatude, String locLatatude) {
        this.locType = locType;
        this.locName = locName;
        this.locDistance = locDistance;
        this.locLongitude = locLongatude;
        this.locLatitude = locLatatude;
    }

    //getter & setter
    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocDistance() {
        return locDistance;
    }

    public void setLocDistance(String locDistance) {
        this.locDistance = locDistance;
    }

    public String getLocLongatude() {
        return locLongitude;
    }

    public void setLocLongatude(String locLongatude) {
        this.locLongitude = locLongatude;
    }

    public String getLocLatatude() {
        return locLatitude;
    }

    public void setLocLatatude(String locLatatude) {
        locLatitude = locLatatude;
    }
}
