package com.polytech.dsl.ssl.core

import com.polytech.dsl.ssl.core.regression.law.composition.CompositionLaw
import com.polytech.dsl.ssl.core.transform.SensorMeasureTransform

class GroovyCompositeSensor extends CompositeSensor {


    GroovyCompositeSensor(String name) {
        super(name)
    }

    def fromSensors(List<Sensor> sensors) {
        super.setSensors(sensors)
        return this
    }

    def composeUsing(CompositionLaw law) {
        super.setLaw(law)
        return this
    }

    def applyNoise(SensorMeasureTransform transform) {
        super.addTransform(transform)
        return this
    }
}
