package com.polytech.dsl.ssl.utils

import com.polytech.dsl.ssl.core.transform.PartialRandom1DTransform
import com.polytech.dsl.ssl.core.transform.Random1DTransform

class Transforms {

    Transforms() {}

    static random(double v) {
        return new Random1DTransform(v)
    }

    static partialRandom(double amplitude, double coverage) {
        return new PartialRandom1DTransform(coverage, amplitude)
    }
}
