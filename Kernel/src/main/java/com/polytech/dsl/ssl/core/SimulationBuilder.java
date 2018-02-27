package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.transform.SensorMeasureTransform;
import com.polytech.dsl.ssl.output.Output;
import com.polytech.dsl.ssl.source.TimeSeries;
import com.polytech.dsl.ssl.util.TimeRange;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationBuilder {

    private static Logger LOGGER = Logger.getLogger(SimulationBuilder.class.getSimpleName());

    private Output output;
    private Map<String, Sensor> sensors;
    private SensorMeasureTransform sensorMeasureTransform;

    private TimeRange range;

    public SimulationBuilder(String start, String end, int frequency, int unit){
        this();
        this.range = new TimeRange()
                .setStart(start)
                .setEnd(end)
                .setFrequency(unit, frequency);
        if (this.range.getStart().getTimeInMillis() > this.range.getEnd().getTimeInMillis()){
            LOGGER.log(Level.ERROR,
                    "[Error] start time is bigger than end time.");
            LOGGER.log(Level.ERROR,"Program not compiled");
            System.exit(0);
        }
        LOGGER.info("Init simulation: " +
                "startTime = " + start + "; endTime = " + end + "; frequency = " + frequency);
    }

    public SimulationBuilder() {
        this.sensors = new HashMap<>();
    }

    public SimulationBuilder addSensor(Sensor sensor) {
        this.sensors.put(sensor.getName(), sensor);
        return this;
    }

    public List<Sensor> getSensors(){
        ArrayList<Sensor> res = new ArrayList<>();
        for(Sensor s : this.sensors.values()) {
            res.add(s);
        }
        return res;
    }

    public Sensor getSensor(String name) {
        return this.sensors.get(name);
    }

    public SimulationBuilder setOutput(Output out) {
        this.output = out;
        return this;
    }

    public SimulationBuilder addNoise(SensorMeasureTransform sensorMeasureTransform) {
        this.sensorMeasureTransform = sensorMeasureTransform;
        return this;
    }

    public void run() {
        for (Sensor sensor : sensors.values()) {
            try {
                runSensorSimulation(sensor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runSensorSimulation(Sensor sensor) throws IOException {
        TimeSeries series = new TimeSeries(sensor.getName());
        range.restart();
        while (range.canIncrement()){
            if (sensorMeasureTransform != null) {
                series.putMeasure(sensorMeasureTransform.transform(sensor.getSensorMeasure(range.getCounter().getTimeInMillis())));
            } else {
                series.putMeasure(sensor.getSensorMeasure(range.getCounter().getTimeInMillis()));
            }
            range.increment();
        }
        output.write(series);
    }
}
