package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.law.SensorLaw;
import com.polytech.dsl.ssl.core.transform.SensorMeasureTransform;
import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.ArrayList;
import java.util.List;

public class Sensor {

    private String name;
    private SensorLaw law;
    private List<SensorMeasureTransform> transformers;

    public Sensor(String name) {
        transformers = new ArrayList<>();
        this.name = name;
    }

    public void setLaw(SensorLaw law) {
        this.law = law;
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
        if (i > 0) {
            return this.transformers.get(i).transform(getSensorMeasure(time, i - 1));
        } else {
            return this.law.getMeasure(time);
        }
    }
}
