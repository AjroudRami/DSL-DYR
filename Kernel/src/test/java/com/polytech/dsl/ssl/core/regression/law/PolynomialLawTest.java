package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PolynomialLawTest {

    @Test
    void getMeasureConstant() {
        // f(t) = 4.5
        PolynomialLaw law = new PolynomialLaw(new double[]{4.5});
        SensorMeasure testMeasure1 = law.getMeasure(1);
        //It should always return 4.5
        SensorMeasure testMeasure10 = law.getMeasure(10);

        Assert.assertEquals(4.5, testMeasure1.get(PolynomialLaw.DEFAULT_LABEL));
        Assert.assertEquals(4.5, testMeasure10.get(PolynomialLaw.DEFAULT_LABEL));

    }

    @Test
    void getMeasureLinear() {
        // f(t) = 3.5 * t + 2
        PolynomialLaw law = new PolynomialLaw(new double[]{2, 3.5});
        SensorMeasure testMeasure0 = law.getMeasure(0);
        SensorMeasure testMeasure1 = law.getMeasure(1);
        SensorMeasure testMeasure4 = law.getMeasure(4);

        // f(0) = 3.5 * 0 + 2 = 2
        Assert.assertEquals(2.0, testMeasure0.get(PolynomialLaw.DEFAULT_LABEL));

        // f(t) = 3.5 * 1 + 2 = 5.5
        Assert.assertEquals(5.5, testMeasure1.get(PolynomialLaw.DEFAULT_LABEL));

        // f(t) = 3.5 * 4 + 2 = 16
        Assert.assertEquals(16.0, testMeasure4.get(PolynomialLaw.DEFAULT_LABEL));
    }

    @Test
    void getMeasurePolynomial() {
        // f(t) = 0.5 * t^2 + 2.5 * t + 1
        PolynomialLaw law = new PolynomialLaw(new double[]{1, 2.5, 0.5});
        SensorMeasure testMeasure0 = law.getMeasure(0);
        SensorMeasure testMeasure1 = law.getMeasure(1);
        SensorMeasure testMeasure4 = law.getMeasure(4);

        // f(t) = 0.5 * 0^2 + 2.5 * 0 + 1 = 1
        Assert.assertEquals(1.0, testMeasure0.get(PolynomialLaw.DEFAULT_LABEL));

        // f(t) = 0.5 * 1^2 + 2.5 * 1 + 1 = 4
        Assert.assertEquals(4.0, testMeasure1.get(PolynomialLaw.DEFAULT_LABEL));

        // f(t) = 0.5 * 4^2 + 2.5 * 4 + 1 = 19
        Assert.assertEquals(19.0, testMeasure4.get(PolynomialLaw.DEFAULT_LABEL));
    }
}