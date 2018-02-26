package com.polytech.dsl.ssl.core.transform;

import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 * This class implements a transform function that will change ALL values
 * in a 1 dimensional numeric dataset.
 * It will apply for each value a gain or a loss of +- an amplitude.
 * The calculated variation follows a pseudo-random uniform law
 */
public class Random1DTransform implements SensorMeasureTransform {

    private double amplitude;

    /**
     * Create a random transformer that will apply to all sensor measure in a set
     *
     * @param amplitude the maximum amplitude of the variation.
     *                  Eg: if amplitude is 10.0 then the max positive variation is 10 / 2
     */
    public Random1DTransform(double amplitude) {
        this.amplitude = amplitude;
    }

    protected Random1DTransform() {
    }

    @Override
    public SensorMeasure transform(SensorMeasure measure) {
        return transform(measure, this.amplitude);
    }

    protected SensorMeasure transform(SensorMeasure measure, double amplitude) {
        SensorMeasure res = new SensorMeasure(measure.time());
        for(int i = 0; i < measure.getLabels().length; i++) {
            String label = measure.getLabels()[i];
                Random random = new Random();
                Optional<Double> opt = measure.getDouble(label);
                if (opt.isPresent()) {
                    double value = opt.get();
                    double rd = random.nextDouble() * amplitude / 2.0;
                    double noiseValue = value + rd;
                    res.putValue(label, noiseValue);
                }
        }
        return res;
    }
}
