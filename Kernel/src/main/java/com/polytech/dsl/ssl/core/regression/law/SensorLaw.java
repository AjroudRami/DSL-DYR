package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;

public interface SensorLaw {

    SensorMeasure getMeasure(long time);
}
