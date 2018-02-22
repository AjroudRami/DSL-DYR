package com.polytech.dsl.ssl.core

import com.polytech.dsl.ssl.core.regression.law.composition.CompositionLaw
import com.polytech.dsl.ssl.core.transform.SensorMeasureTransform

class GroovySensorSet extends Sensor {


    GroovySensorSet(String name) {
        super(name)
    }

    def composeUsing(CompositionLaw law) {
        this.setLaw(law)
        return this
    }

    def applyNoise(SensorMeasureTransform transform) {
        this.addTransform(transform)
        return this
    }
}
