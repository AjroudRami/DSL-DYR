package com.polytech.dsl.ssl.core;

import Jama.Matrix;
import com.polytech.dsl.ssl.core.regression.law.IdentityLaw;
import com.polytech.dsl.ssl.core.regression.law.PolynomialLaw;
import com.polytech.dsl.ssl.core.regression.law.Random1D;
import com.polytech.dsl.ssl.output.CSVWriter;
import com.polytech.dsl.ssl.output.DatabaseWriter;
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

    public void initSimulation(String start, String end, int offset, int amount) {
        this.builder = new SimulationBuilder(start, end, offset, amount);
    }

    public void initSimulation(long start, long end, int frequency) {
        // Example : SimulationBuilder builder = new SimulationBuilder(0, 100, 1);
        // this.builder = new SimulationBuilder(System.currentTimeMillis() + start, System.currentTimeMillis()+end, frequency);
    }

    public void createSensor(String name) {
        // Example : Sensor sensor = new Sensor("sensorName");
        this.sensor = new Sensor(name);
        this.builder.addSensor(sensor);
        this.binding.setVariable(name, sensor);
    }

    public void setIdentityLaw(String file) {
        // Example : sensor.setLaw(new IdentityLaw(new SimpleCSVParser(new File(""))));
        this.sensor.setLaw(new IdentityLaw(new SimpleCSVParser(new File(file))));
    }

    public void setRandomLaw(int min, int max) {
        // Example sensor.setLaw(new Random1D(0, 20));
        this.sensor.setLaw(new Random1D(min, max));
    }

    public void setPolynomialLaw(Matrix betas, String key, int degree) {
        // Example sensor.setLaw(new Random1D(0, 20));
        this.sensor.setLaw(new PolynomialLaw(this.sensor.getName(), betas, key, degree));
    }

    public void setOutput(String destination) {
        // Example : builder.setOutput(new CSVWriter());
        this.builder.setOutput(new CSVWriter(/*destination*/));
    }

    public void storeToDB(String databaseName){
        if (databaseName.equals("")) {
            this.builder.setOutput(new DatabaseWriter());
        }else {
            this.builder.setOutput(new DatabaseWriter(databaseName));
        }
    }

    public void generateSet(Sensor sensor, int number) {
        for (int i = 0; i < number; i++) {
            Sensor toCopyed = ((Sensor) this.binding.getVariable(sensor.getName())).clone();
            toCopyed.setName(sensor.getName() + "_" + i);
            this.builder.addSensor(toCopyed);
            this.binding.setVariable(toCopyed.getName(), toCopyed);
        }
    }

    public void run() {
        builder.run();
    }

}
