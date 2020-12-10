package com.example.sensorsapplication.ObjectClasses;

public class SensorClass {

    public SensorClass(String name, String vender, int delay, String maximumRange, String power, String resolution, String version) {
        this.name = "Name: "+name;
        this.vender ="Vender: "+ vender;
        this.Delay = "Maximun Delay: "+delay;
        this.maximumRange ="Maximum Range: "+ maximumRange;
        this.power = "Power: "+power;
        this.resolution = "Resolution: "+resolution;
        this.version = "Version: "+version;
    }

    String name="";
    String vender="";
    String Delay="";
    String maximumRange="";
    String power="";
    String resolution="";
    String version="";

    public String getDelay() {
        return Delay;
    }

    public void setDelay(String delay) {
        Delay = delay;
    }


    public String getMaximumRange() {
        return maximumRange;
    }

    public void setMaximumRange(String maximumRange) {
        this.maximumRange = maximumRange;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }
}
