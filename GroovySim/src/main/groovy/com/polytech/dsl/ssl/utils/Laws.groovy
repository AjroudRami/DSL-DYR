package com.polytech.dsl.ssl.utils

import com.polytech.dsl.ssl.core.regression.PolynomialRegression
import com.polytech.dsl.ssl.core.regression.law.FunctionLaw
import com.polytech.dsl.ssl.core.regression.law.IdentityLaw
import com.polytech.dsl.ssl.core.regression.law.PolynomialLaw
import com.polytech.dsl.ssl.core.regression.law.Random1D
import com.polytech.dsl.ssl.source.SimpleCSVParser
import com.polytech.dsl.ssl.source.Source

class Laws {

    Laws() {}

    static random1D(int min, int max) {
        return new Random1D((int) min, (int) max)
    }

    static polynomial1D(double[] coefs) {
        return new PolynomialLaw(coefs)
    }

    static polynomialRegression(String filename) {
        return new PolynomialRegression().getSensorLaw((Source) new File(filename))
    }

    static function(String function) {
        return new FunctionLaw(function)
    }

    static replayCSV(String filename) {
        new IdentityLaw(new SimpleCSVParser(new File(filename)))
    }
}
