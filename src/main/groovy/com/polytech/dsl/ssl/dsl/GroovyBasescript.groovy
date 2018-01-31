package com.polytech.dsl.ssl.dsl

abstract class GroovyBasescript extends Script {

    def sensor(String name) {
        println("New sensor : " + name)
    }

}
