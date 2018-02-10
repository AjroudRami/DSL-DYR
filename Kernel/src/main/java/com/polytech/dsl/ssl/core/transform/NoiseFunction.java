package com.polytech.dsl.ssl.core.transform;

import com.polytech.dsl.ssl.source.SensorMeasure;

public interface NoiseFunction {

    SensorMeasure makeNoise(SensorMeasure measure);
}
