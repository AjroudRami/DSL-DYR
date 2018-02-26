package com.polytech.dsl.ssl.utils

import com.polytech.dsl.ssl.core.regression.PolynomialRegression
import com.polytech.dsl.ssl.core.regression.law.FunctionLaw
import com.polytech.dsl.ssl.core.regression.law.IdentityLaw
import com.polytech.dsl.ssl.core.regression.law.PolynomialLaw
import com.polytech.dsl.ssl.core.regression.law.Random1D
import com.polytech.dsl.ssl.core.regression.law.SensorLaw
import com.polytech.dsl.ssl.source.SimpleCSVParser
import com.polytech.dsl.ssl.source.Source

class Laws {

    Laws() {}

    static SensorLaw random1D(int min, int max) {
        return new Random1D((int) min, (int) max)
    }

    static polynomial1D(ArrayList<Double> coefs) {
        double[] doubleCoefs = coefs.toArray()
        return new PolynomialLaw(doubleCoefs)
    }

    static polynomialRegression(Source src, int degree) {
        return new PolynomialRegression(degree).getSensorLaw(src)
    }

    static function(String function) {
        return new FunctionLaw(function)
    }

    static replayCSV(String filename) {
        new IdentityLaw(new SimpleCSVParser(new File(filename)))
    }
}
