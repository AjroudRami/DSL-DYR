package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.law.IdentityLaw;
import com.polytech.dsl.ssl.core.regression.law.Random1D;
import com.polytech.dsl.ssl.output.CSVWriter;
import com.polytech.dsl.ssl.source.SimpleCSVParser;

import groovy.lang.Binding;

import java.io.File;

public class GroovyModel {

    private Binding binding;
    private SimulationBuilder builder;
    private Sensor sensor;

    public GroovyModel() {
        this.builder = new SimulationBuilder();
    }

    public GroovyModel(Binding binding) {
        this.binding = binding;
        this.builder = new SimulationBuilder();
    }

    public void initSimulation(long start, long end, int frequency) {
        // Example : SimulationBuilder builder = new SimulationBuilder(0, 100, 1);
        this.builder = new SimulationBuilder(start, end, frequency);
    }

    public void createSensor(String name) {
        // Example : Sensor sensor = new Sensor("sensorName");
        this.sensor = new Sensor(name);
        System.out.println("OKK !");
        System.out.println(this.builder); // OK !!
        System.out.println(sensor); // OK !!
        this.builder.addSensor(sensor); // NOT NUL POINTER..
        System.out.println("OK !");
        this.binding.setVariable(name, sensor);
        System.out.println("OK !!!!");
    }

    public void setIdentityLaw(String file) {
        // Example : sensor.setLaw(new IdentityLaw(new SimpleCSVParser(new File(""))));
        this.sensor.setLaw(new IdentityLaw(new SimpleCSVParser(new File(file))));
    }

    public void setRandomLaw(int min, int max) {
        // Example sensor.setLaw(new Random1D(0, 20));
        this.sensor.setLaw(new Random1D(min, max));
    }

    public void setOutput(String destination) {
        // Example : builder.setOutput(new CSVWriter());
        this.builder.setOutput(new CSVWriter(/*destination*/));
    }

    public void generateSet(Sensor sensor, int number) {
        for (int i = 0; i < number; i++) {
            Sensor toCopyed = (Sensor) this.binding.getVariable(sensor.getName());
            toCopyed.setName(sensor.getName() + "_" + i);
            this.builder.addSensor(toCopyed);
            this.binding.setVariable(toCopyed.getName(), toCopyed);
        }
    }

    public void run() {
        run();
    }

}
