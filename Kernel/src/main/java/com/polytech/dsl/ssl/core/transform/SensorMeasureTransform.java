package com.polytech.dsl.ssl.core.transform;

import com.polytech.dsl.ssl.source.SensorMeasure;

public interface SensorMeasureTransform {

    SensorMeasure transform(SensorMeasure measure);
}
