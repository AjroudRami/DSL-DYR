package com.polytech.dsl.ssl.core

import com.polytech.dsl.ssl.core.enums.FrequencyUnit
import com.polytech.dsl.ssl.output.Output
import com.polytech.dsl.ssl.utils.Compositions
import com.polytech.dsl.ssl.utils.Laws
import com.polytech.dsl.ssl.utils.Outputs
import com.polytech.dsl.ssl.utils.Sources
import com.polytech.dsl.ssl.utils.Transforms

abstract class GroovyBasescript extends Script {

    /**
     * Start Syntax V2
     */

    def compositions = new Compositions()

    def sources = new Sources()

    def laws = new Laws()

    def transforms = new Transforms()

    def out = new Outputs()

    def addSensor = { name ->
        GroovySensor sensor = GroovySensor.sensor(name)
        ((GroovyBinding) this.getBinding()).getGroovyModel().addSensor(sensor)
        sensor
    }

    def addCompositeSensor = { name ->
        GroovyCompositeSensor sensor = new GroovyCompositeSensor((String) name)
        ((GroovyBinding) this.getBinding()).getGroovyModel().addSensor(sensor)
        sensor
    }

    def sensors = { ArrayList<String> names ->
        ArrayList<Sensor> sensors = new ArrayList<>()
        if (names.size() == 1 && names.get(0).equals("all")) return ((GroovyBinding) this.getBinding()).getGroovyModel().getBuilder().getSensors()
        for (String name : names) {
            sensors.add(((GroovyBinding) this.getBinding()).getGroovyModel().getBuilder().getSensor(name))
        }
        return sensors
    }



    def runSimulation() {
        ((GroovyBinding) this.getBinding()).getGroovyModel().run()
    }

    //TODO rename this method to sensor
    def getSensor(String name) {
        return ((GroovyBinding) this.getBinding()).getGroovyModel().getBuilder().getSensor(name)
    }

    def outputTo(Output out) {
        ((GroovyBinding) this.getBinding()).getGroovyModel().getBuilder().setOutput(out)
    }
    /**
     * End Syntax V2
     */
    def sensor(String name) {
        println("Create new sensor named : " + name)

        ((GroovyBinding) this.getBinding()).getGroovyModel().createSensor(name)

        [
            randomlaw: { min, max ->
                println ("Apply random law with min : " + min + " and max : " + max)
                ((GroovyBinding) this.getBinding()).getGroovyModel().setRandomLaw(min, max)
            },

            polynomiallaw: { poly ->
                println ("Apply polynomial law with law : " + poly)
                ((GroovyBinding) this.getBinding()).getGroovyModel().setPolynomialLaw((double[])poly)
            },

            polynomialregressionlaw: { file ->
                println ("Apply polynomial regression law from : " + file)
                ((GroovyBinding) this.getBinding()).getGroovyModel().setPolynomialRegressionLaw(file)
            },

            functionlaw: { function ->
                ((GroovyBinding) this.getBinding()).getGroovyModel().setFunctionLaw(function)
            },

            fromcsv: { file ->
                println ("Apply replayed data from  : " + file)
                ((GroovyBinding) this.getBinding()).getGroovyModel().setIdentityLaw(file)
            }
        ]
    }

    def simulation(String name) {
        println("Create new simulation named : " + name)
        [
            timerange: { start, end ->
                [
                    frequency: { frequency, unit ->
                        println ("Initialisation of the simulation with : "
                            + "\nstart value = " + start
                            + ",\nend value = " + end
                            + ",\nwith offset = " + frequency
                            + ",\nof unit = " + unit)
                        ((GroovyBinding) this.getBinding()).getGroovyModel().initSimulation(
                                (String)start, (String)end,
                                (int)frequency, FrequencyUnit.valueOf(((String)unit).toUpperCase()).getOffset())
                    }
                ]
            }
        ]
    }

    def generateSet(Object sensor, Integer number) {
        println ("Generate " + number + " set of " + sensor + "sensor")
        ((GroovyBinding) this.getBinding()).getGroovyModel().generateSet(
                sensor instanceof String ?
                        (Sensor)((GroovyBinding)this.getBinding()).getGroovyModel().getBuilder().getSensor(sensor) :
                        (Sensor) sensor,
                number)
    }

    def aggregate(String[] sensors) {
        println ("Agregate sensors")
        [
            to: { name ->
                println ("Agregation will be in : " + name)
                ((GroovyBinding) this.getBinding()).getGroovyModel().aggregate(sensors, name)
            }
        ]
    }

    def noise(double amplitude) {
        println("Noise applied to the simulation with amplitude of : " + amplitude)
        ((GroovyBinding) this.getBinding()).getGroovyModel().addNoise(amplitude)
    }

    def partialnoise(double probability, double amplitude) {
        println("Noise applied to the simulation with amplitude of : " + amplitude + " and probability of " + probability)
        ((GroovyBinding) this.getBinding()).getGroovyModel().addPartialNoise(probability, amplitude)
    }

    def outputcsv(String destination) {
        println("Destination and name of the result file : " + destination)
        ((GroovyBinding) this.getBinding()).getGroovyModel().setOutput(destination)
    }

    def outputDB(String databaseName){
        println("Storing result in database " + databaseName)
        ((GroovyBinding) this.getBinding()).getGroovyModel().storeToDB(databaseName)
    }

    def run(String name) {
        println("Run the simulation : " + name)
        ((GroovyBinding) this.getBinding()).getGroovyModel().run()
    }


}
