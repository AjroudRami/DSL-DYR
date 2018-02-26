package com.polytech.dsl.ssl.core.regression.law.composition;

import com.polytech.dsl.ssl.core.Sensor;
import com.polytech.dsl.ssl.core.regression.law.SensorLaw;
import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.ArrayList;
import java.util.List;

public class MeanComposition implements CompositionLaw {

    private List<SensorLaw> laws;

    public MeanComposition() {
        this.laws = new ArrayList<>();
    }

    public void setSensors(List<Sensor> sensors) {
        for(Sensor sensor : sensors) {
            this.laws.add(sensor.getLaw());
        }
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        Double sum = new Double(0);
        SensorMeasure result = new SensorMeasure(time);
        for (SensorLaw law : laws) {
            SensorMeasure measure = law.getMeasure(time);
            sum += measure.getDouble(measure.getLabels()[0]).get();
        }
        sum /= laws.size();
        result.putValue("mean", sum);
        return result;
    }

    @Override
    public SensorLaw cleanCopy() {
        //TODO copy
        return null;
    }
}
