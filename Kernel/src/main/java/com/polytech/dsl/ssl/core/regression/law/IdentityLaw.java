package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;
import com.polytech.dsl.ssl.source.Source;
import com.polytech.dsl.ssl.source.TimeSeries;

public class IdentityLaw implements SensorLaw {

    private TimeSeries timeSeries;

    public IdentityLaw(Source source) {
        this.timeSeries = source.getTimeSeries();
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        return timeSeries.getMeasure(time);
    }
}
