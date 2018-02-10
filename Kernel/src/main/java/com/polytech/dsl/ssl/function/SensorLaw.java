package com.polytech.dsl.ssl.function;

import com.polytech.dsl.ssl.source.SensorMeasure;

public interface SensorLaw {

    SensorMeasure getMeasure(long time);
}
