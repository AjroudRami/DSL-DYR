package com.polytech.dsl.ssl.core.transform;

import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.Arrays;
import java.util.Random;

public class Random1DTransform implements SensorMeasureTransform {

    private int amplitude;

    public Random1DTransform(int amplitude) {
        this.amplitude = amplitude;
    }

    @Override
    public SensorMeasure transform(SensorMeasure measure) {
        SensorMeasure res = new SensorMeasure(measure.time());
        Arrays.stream(measure.getLabels()).forEach(
                label -> {
                    Random random = new Random();
                    int value = measure.getInt(label).get();
                    int noiseValue = value + random.nextInt() * amplitude;
                    res.putValue(label, noiseValue);
                }
        );
        return res;
    }
}
