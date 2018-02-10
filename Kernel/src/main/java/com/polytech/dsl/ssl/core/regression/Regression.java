package com.polytech.dsl.ssl.core.regression;

import com.polytech.dsl.ssl.core.regression.law.SensorLaw;
import com.polytech.dsl.ssl.source.TimeSeries;

public interface Regression {

    SensorLaw getSensorLaw(TimeSeries timeSeries);
}
