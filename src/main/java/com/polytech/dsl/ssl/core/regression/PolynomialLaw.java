package com.polytech.dsl.ssl.core.regression;

import Jama.Matrix;
import com.polytech.dsl.ssl.function.SensorLaw;
import com.polytech.dsl.ssl.source.SensorMeasure;

public class PolynomialLaw implements SensorLaw {

    private String sensorName;
    private Matrix betas;
    private String key;
    private int degree;

    public PolynomialLaw(String sensorName, Matrix betas, String key, int degree) {
        this.betas = betas;
        this.sensorName = sensorName;
        this.key = key;
        this.degree = degree;
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        SensorMeasure measure = new SensorMeasure(sensorName, time);
        double val = 0;
        for(int i = 0; i < degree; i++) {
            val += beta(i) * Math.pow(time, i);
        }
        measure.putValue(key, val);
        return measure;
    }

    private double beta(int j){
        if (Math.abs(betas.get(j, 0)) < 1E-4) return 0.0;
        return betas.get(j, 0);
    }
}
