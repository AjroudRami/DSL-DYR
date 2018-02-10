package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;
import com.polytech.dsl.ssl.source.TimeSeries;

public class IdentityLaw implements SensorLaw {

    private TimeSeries timeSeries;

    public IdentityLaw(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        return timeSeries.getMeasure(time);
    }
}
