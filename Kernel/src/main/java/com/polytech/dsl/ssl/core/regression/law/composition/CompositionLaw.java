package com.polytech.dsl.ssl.core.regression.law.composition;

import com.polytech.dsl.ssl.core.Sensor;
import com.polytech.dsl.ssl.core.regression.law.SensorLaw;

import java.util.List;


public interface CompositionLaw extends SensorLaw {
    void setSensors(List<Sensor> sensors);
}
