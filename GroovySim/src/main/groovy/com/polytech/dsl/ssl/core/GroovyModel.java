package com.polytech.dsl.ssl.core;

import groovy.lang.Binding;


public class GroovyModel {

    private SimulationBuilder builder;
    private Binding binding;

    public GroovyModel() {
    }

    public GroovyModel(Binding binding) {
        this.binding = binding;
    }

    public void initSimulation(long start, long end, int frequency) {
        this.builder = new SimulationBuilder(start, end, frequency);
    }

}
