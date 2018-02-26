package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.Random;

public class Random1D extends RandomLaw {

    private double low;
    private double high;
    private String label = "RD";

    public Random1D(double low, double high) {
        super();
        this.low = low;
        this.high = high;
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        if (super.generatedMeasures.containsKey(time)) {
            return super.generatedMeasures.get(time);
        } else {
            SensorMeasure measure = new SensorMeasure(time);
            measure.putValue(label, getRandomValue());
            return measure;
        }
    }

    @Override
    public SensorLaw cleanCopy() {
        return new Random1D(this.low, this.high);
    }


    private double getRandomValue() {
        Random random = new Random();
        double randNB = random.nextDouble() * (high - low) + low;

        return randNB;
    }
}
