package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.law.SensorLaw;

public class Sensor {

    private String name;
    private SensorLaw law;

    public Sensor(String name) {
        this.name = name;
    }

    public void setLaw(SensorLaw law) {
        this.law = law;
    }

}
