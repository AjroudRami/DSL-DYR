package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.law.SensorLaw;
import com.polytech.dsl.ssl.core.transform.SensorMeasureTransform;
import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.ArrayList;
import java.util.List;

public class Sensor implements Cloneable {

    private String name;
    private SensorLaw law;
    private List<SensorMeasureTransform> transformers;

    public Sensor() {
        transformers = new ArrayList<>();
    }

    public Sensor(String name) {
        this();
        this.name = name;
    }

    public void setLaw(SensorLaw law) {
        this.law = law;
    }

    public SensorLaw getLaw() {
        return law;
    }

    public SensorMeasure getSensorMeasure(long time) {
        return this.getSensorMeasure(time, transformers.size() - 1);
    }

    public void addTransform(SensorMeasureTransform transform) {
        this.transformers.add(transform);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private SensorMeasure getSensorMeasure(long time, int i) {
        if (i >= 0) {
            return this.transformers.get(i).transform(getSensorMeasure(time, i - 1));
        } else {
            return this.law.getMeasure(time);
        }
    }

    @Override
    public Sensor clone() {
        Sensor sensor = new Sensor(this.name);
        sensor.transformers = this.transformers;
        sensor.law = this.law.cleanCopy();
        return sensor;
    }
}
