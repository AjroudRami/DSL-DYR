package com.polytech.dsl.ssl.core.regression;

import com.polytech.dsl.ssl.core.regression.law.SensorLaw;
import com.polytech.dsl.ssl.source.Source;

public interface Regression {

    SensorLaw getSensorLaw(Source source);
}
