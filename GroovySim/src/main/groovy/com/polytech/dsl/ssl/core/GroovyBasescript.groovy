package com.polytech.dsl.ssl.core

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
                            frequency: { freq ->
                                println ("Initialisation of the simulation with : "
                                        + "\nstart value = " + start
                                        + ",\nend value = " + end
                                        + ",\nand frequency value = " + freq)
                                ((GroovyBinding) this.getBinding()).getGroovyModel().initSimulation(start, end, freq)
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
        println("Destination and name of the result file : " + name)
        ((GroovyBinding) this.getBinding()).getGroovyModel().setOutput(destination)
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
