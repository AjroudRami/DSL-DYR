package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.PolynomialRegression;
import com.polytech.dsl.ssl.core.regression.law.FunctionLaw;
import com.polytech.dsl.ssl.core.regression.law.IdentityLaw;
import com.polytech.dsl.ssl.core.regression.law.PolynomialLaw;
import com.polytech.dsl.ssl.core.regression.law.Random1D;
import com.polytech.dsl.ssl.core.transform.PartialRandom1DTransform;
import com.polytech.dsl.ssl.core.transform.Random1DTransform;
import com.polytech.dsl.ssl.output.CSVWriter;
import com.polytech.dsl.ssl.output.DatabaseWriter;
import com.polytech.dsl.ssl.source.SimpleCSVParser;
import com.polytech.dsl.ssl.source.Source;
import groovy.lang.Binding;
import org.apache.log4j.Logger;

import java.io.File;

public class GroovyModel {

    private static final Logger LOGGER = Logger.getLogger(GroovyModel.class);

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

    public void initSimulation(String start, String end, int frequency, int unit){
        this.builder = new SimulationBuilder(start,end,frequency,unit);
    }
    public void initSimulation(long start, long end, int frequency) {
        // Example : SimulationBuilder builder = new SimulationBuilder(0, 100, 1);
//        this.builder = new SimulationBuilder(System.currentTimeMillis() + start, System.currentTimeMillis()+end, frequency);
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
        if (min >max){
            LOGGER.info("Random law input error. Should follow the following format  : randomlaw min, max (where min < max)");
            System.exit(0);
        }
        this.sensor.setLaw(new Random1D(min, max));
    }

    public void setPolynomialLaw(double[] poly) {
        // Example sensor.setLaw(new PolynomialLaw([4, -3, 2]));
        this.sensor.setLaw(new PolynomialLaw(poly));
    }

    public void setPolynomialRegressionLaw(String file) {
        // Example : sensor.setLaw(new PolynomialRegression().getSensorLaw((Source) new File(file)));
        this.sensor.setLaw(new PolynomialRegression().getSensorLaw((Source) new File(file)));
    }

    public void setFunctionLaw(String function){
        this.sensor.setLaw(new FunctionLaw(function));
    }

    public void setOutput(String destination) {
        // Example : builder.setOutput(new CSVWriter());
        this.builder.setOutput(new CSVWriter(/*destination*/));
    }

    public void addNoise(double amplitude) {
        // Example : builder.addNoise(new Random1DTransform(10));
        this.builder.addNoise(new Random1DTransform(amplitude));
    }

    public void addPartialNoise(double probability, double amplitude) {
        // Example : builder.addNoise(new PartialRandom1DTransform(probability, amplitude));
        this.builder.addNoise(new PartialRandom1DTransform(probability, amplitude));
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
            Sensor toCopyed = sensor.clone();
            toCopyed.setName(sensor.getName() + "_" + i);
            this.builder.addSensor(toCopyed);
            this.binding.setVariable(toCopyed.getName(), toCopyed);
        }
    }

    public void run() {
        builder.run();
    }

}
