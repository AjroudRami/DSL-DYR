package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.Random;

public class Random1D extends RandomLaw {

    private int low;
    private int high;
    private String label = "RD";

    public Random1D(int low, int high) {
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


    private int getRandomValue() {
        Random random = new Random();
        int randInt = random.nextInt(high - low);

        return randInt + low;
    }
}
