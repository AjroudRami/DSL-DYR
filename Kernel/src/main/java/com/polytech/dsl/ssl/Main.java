package com.polytech.dsl.ssl;

import com.polytech.dsl.ssl.core.Sensor;
import com.polytech.dsl.ssl.core.SimulationBuilder;
import com.polytech.dsl.ssl.core.regression.PolynomialRegression;
import com.polytech.dsl.ssl.core.regression.law.IdentityLaw;
import com.polytech.dsl.ssl.core.regression.law.Random1D;
import com.polytech.dsl.ssl.output.CSVWriter;
import com.polytech.dsl.ssl.source.SimpleCSVParser;

import java.io.File;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        SimulationBuilder builder = new SimulationBuilder("now", "13-02-2018 20:30:00", Calendar.MINUTE,2);
        Sensor sensor = new Sensor("sensorName");
        sensor.setLaw(new PolynomialRegression().getSensorLaw(source));
        sensor.setLaw(new IdentityLaw(new SimpleCSVParser(new File(""))));
        sensor.setLaw(new Random1D(0, 20));
        builder.addSensor(sensor);
        builder.setOutput(new CSVWriter());
    }
}
