package com.polytech.dsl.ssl.function;

import com.polytech.dsl.ssl.source.SensorMeasure;

public interface NoiseFunction {

    SensorMeasure makeNoise(SensorMeasure measure);
}
