package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eroyas on 31/01/18.
 */
public abstract class RandomLaw implements SensorLaw {
    protected Map<Long, SensorMeasure> generatedMeasures;

    protected RandomLaw() {
        this.generatedMeasures = new HashMap<>();
    }
}
