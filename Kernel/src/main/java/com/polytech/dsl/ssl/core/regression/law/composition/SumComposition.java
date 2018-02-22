package com.polytech.dsl.ssl.core.regression.law.composition;

import com.polytech.dsl.ssl.core.Sensor;
import com.polytech.dsl.ssl.core.regression.law.SensorLaw;
import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.ArrayList;
import java.util.List;

public class SumComposition implements CompositionLaw {

    private List<SensorLaw> laws;

    private SumComposition() {
        this.laws = new ArrayList<>();
    }

    public SumComposition(List<Sensor> sensors) {
        this.laws = new ArrayList<>();
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        Double sum = new Double(0);
        SensorMeasure result = new SensorMeasure(time);
        for (SensorLaw law : laws) {
            SensorMeasure measure = law.getMeasure(time);
            sum += measure.getDouble(measure.getLabels()[0]).get();
        }
        result.putValue("sum", sum);
        return result;
    }

    @Override
    public SensorLaw cleanCopy() {
        SumComposition copy = new SumComposition();
        //TODO copy
        return copy;
    }
}
