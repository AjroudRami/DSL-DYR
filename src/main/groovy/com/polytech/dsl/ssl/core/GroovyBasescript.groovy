package com.polytech.dsl.ssl.core

abstract class GroovyBasescript extends Script {

    def sensor(String name) {
        println("New sensor : " + name)
    }

}
