package com.polytech.dsl.ssl.core

abstract class GroovyBasescript extends Script {

    def sensor(String name) {
        println("New sensor : " + name)
        [
                law: { sensorLaw ->
                    // TODO
                    println sensorLaw
                }
        ]
    }

    def simulation(String name) {
        println("New simulation : " + name)
        [
                timerange: { start, end ->
                    [
                            frequency: { freq ->

                                ((GroovyBinding) this.getBinding()).getGroovyModel().initSimulation(start, end, freq)
                                // TODO
                                println start
                                println end
                                println freq
                            }
                    ]
                }
        ]
    }

    def generateSet(Object sensor, Integer number) {
        println sensor
        println number
        // TODO
    }

    def outputcsv(String destination) {
        println destination
        // TODO
    }

    def run(String name) {
        println name
        // TODO
    }

    def random1D(int min, int max) {
        println "in random.."
        // TODO
        return null
    }

}
