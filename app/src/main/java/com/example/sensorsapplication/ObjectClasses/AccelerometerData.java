package com.example.sensorsapplication.ObjectClasses;

public class AccelerometerData {
    String a_TimeStamp="";
    String b_StopWatch="";
    String c_name="";
    String d_vender="";
    String e_Delay="";
    String h_maximumRange="";
    String i_power="";
    String j_resolution="";
    String k_version="";
    float l_xValue=0f;
    float m_yValue=0f;
    float n_zValue=0f;


    public AccelerometerData(String a_TimeStamp, String b_StopWatch, String c_name, String d_vender, String e_Delay, String h_maximumRange, String i_power, String j_resolution, String k_version, float l_xValue, float m_yValue, float n_zValue) {
        this.a_TimeStamp = a_TimeStamp;
        this.b_StopWatch = b_StopWatch;
        this.c_name = c_name;
        this.d_vender = d_vender;
        this.e_Delay = e_Delay;
        this.h_maximumRange = h_maximumRange;
        this.i_power = i_power;
        this.j_resolution = j_resolution;
        this.k_version = k_version;
        this.l_xValue = l_xValue;
        this.m_yValue = m_yValue;
        this.n_zValue = n_zValue;
    }
}
