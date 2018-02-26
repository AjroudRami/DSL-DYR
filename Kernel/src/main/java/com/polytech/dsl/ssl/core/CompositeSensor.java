package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.law.composition.CompositionLaw;

import java.util.ArrayList;
import java.util.List;

public class CompositeSensor extends Sensor {

    private List<Sensor> sensors;

    public CompositeSensor(String name) {
        super(name);
        this.sensors = new ArrayList<>();
    }

    public CompositeSensor setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
        return this;
    }

    public CompositeSensor setLaw(CompositionLaw law) {
        law.setSensors(this.sensors);
        super.setLaw(law);
        return this;
    }

}
