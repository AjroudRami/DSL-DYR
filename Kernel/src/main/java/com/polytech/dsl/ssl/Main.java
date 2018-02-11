package com.polytech.dsl.ssl;

import com.polytech.dsl.ssl.core.Sensor;
import com.polytech.dsl.ssl.core.SimulationBuilder;
import com.polytech.dsl.ssl.core.regression.law.IdentityLaw;
import com.polytech.dsl.ssl.output.CSVWriter;
import com.polytech.dsl.ssl.source.SimpleCSVParser;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        SimulationBuilder builder = new SimulationBuilder(0, 100, 1);
        Sensor sensor = new Sensor("sensorName");
        sensor.setLaw(new IdentityLaw(new SimpleCSVParser(new File(""))));
        builder.addSensor(sensor);
        builder.setOutput(new CSVWriter());
    }
}
