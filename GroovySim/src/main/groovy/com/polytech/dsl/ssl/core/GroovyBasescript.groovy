package com.polytech.dsl.ssl.core

import com.polytech.dsl.ssl.core.enums.Offset


abstract class GroovyBasescript extends Script {

    def sensor(String name) {
        println("Create new sensor named : " + name)

        ((GroovyBinding) this.getBinding()).getGroovyModel().createSensor(name)

        [
            randomlaw: { min, max ->
                println ("Apply random law with min : " + min + " and max : " + max)
                ((GroovyBinding) this.getBinding()).getGroovyModel().setRandomLaw(min, max)
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
                    offset: { offset, amount ->
                        println ("Initialisation of the simulation with : "
                            + "\nstart value = " + start
                            + ",\nend value = " + end
                            + ",\nand frequency value = " + offset
                            + ",\nand amount = " + amount)
                        ((GroovyBinding) this.getBinding()).getGroovyModel().initSimulation(
                                (String)start, (String)end,
                                Offset.valueOf(((String)offset).toUpperCase()).getOffset(), (int)amount)
                    }
                ]
            }
        ]
    }

    def generateSet(Object sensor, Integer number) {
        println ("Generate " + number + " set of " + sensor + "sensor")
        ((GroovyBinding) this.getBinding()).getGroovyModel().generateSet(
                sensor instanceof String ? (Sensor)((GroovyBinding)this.getBinding()).getVariable(sensor) : (Sensor)sensor,
                number)
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

    def random1D(int min, int max) {
        println "in random.."
        // TODO ??
        return null
    }


}
