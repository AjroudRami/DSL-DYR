package com.polytech.dsl.ssl.core.transform;

import com.polytech.dsl.ssl.source.SensorMeasure;

import java.util.Random;

/**
 * This class implements a variant of the Random1DTransformer which will transform
 * or not, sensor values, following a uniform law.
 * Only an average of "probability" % of the dataset will be modified.
 */
public class PartialRandom1DTransform extends Random1DTransform {

    private double amplitude;
    private double probability;

    public PartialRandom1DTransform(double probability, double amplitude) {
        this.amplitude = amplitude;
        this.probability = probability;
    }

    @Override
    public SensorMeasure transform(SensorMeasure measure) {
        if (mustApplyTransform()) {
            return super.transform(measure, this.amplitude);
        }
        return measure;
    }

    private boolean mustApplyTransform() {
        Random random = new Random();
        boolean condition = random.nextDouble() < probability;
        return condition;
    }
}
