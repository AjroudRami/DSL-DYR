package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;

/**
 * A SensorLaw is an object which output a SensorMeasure given a time.
 * All object implementing this interface MUST ensure that the law will give the same SensorMeasure
 * (eg: same values not reference) if the getMeasure method is called twice with the same parameter.
 */
public interface SensorLaw {

    SensorMeasure getMeasure(long time);

    SensorLaw cleanCopy();
}
