package com.polytech.dsl.ssl.core.regression.law;

import Jama.Matrix;
import com.polytech.dsl.ssl.source.SensorMeasure;

public class PolynomialLaw implements SensorLaw {

    private Matrix betas;
    private String key;
    private int degree;

    public PolynomialLaw(double[] coefs, String label, int degree) {
        this.betas = new Matrix(coefs, 1);
        this.key = label;
        this.degree = degree;
    }

    public PolynomialLaw(Matrix betas, String key, int degree) {
        this.betas = betas;
        this.key = key;
        this.degree = degree;
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        SensorMeasure measure = new SensorMeasure(time);
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
