package com.polytech.dsl.ssl.core

import com.polytech.dsl.ssl.core.regression.law.SensorLaw
import com.polytech.dsl.ssl.core.transform.SensorMeasureTransform

class GroovySensor extends Sensor {

    GroovySensor(String name) {
        super(name)
    }

    static def sensor(String name) {
        return new GroovySensor(name)
    }

    def withLaw(SensorLaw law) {
        this.setLaw(law)
        return this
    }

    def applyNoise(SensorMeasureTransform transform) {
        this.addTransform(transform)
        return this
    }
}
