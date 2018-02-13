package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.Regression;
import com.polytech.dsl.ssl.core.transform.SensorMeasureTransform;
import com.polytech.dsl.ssl.output.Output;
import com.polytech.dsl.ssl.source.Source;
import com.polytech.dsl.ssl.source.TimeSeries;
import com.polytech.dsl.ssl.util.TimeRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SimulationBuilder {

    private static Logger LOGGER = Logger.getLogger(SimulationBuilder.class.getSimpleName());

    private Source source;
    private Output output;
    private List<Sensor> sensors;
    private Regression regression;
    private SensorMeasureTransform sensorMeasureTransform;

    private TimeRange range;

    public SimulationBuilder(String start, String end, int offset, int unit){
        this();
        this.range = new TimeRange()
                .setStart(start)
                .setEnd(end)
                .setFrequency(unit, offset);

        LOGGER.info("Init simulation builder, " +
                "startTime = " + start + "; endTime = " + end + "; frequency = " + offset);
    }

    public SimulationBuilder() {
        this.sensors = new ArrayList<>();
    }

    public SimulationBuilder setSource(Source src){
        this.source = src;
        return this;
    }

    public SimulationBuilder addSensor(Sensor sensor) {
        this.sensors.add(sensor);
        return this;
    }

    public SimulationBuilder setOutput(Output out) {
        this.output = out;
        return this;
    }

    public SimulationBuilder approximateSource(Regression regression) {
        this.regression = regression;
        return this;
    }

    public SimulationBuilder addNoise(SensorMeasureTransform sensorMeasureTransform) {
        this.sensorMeasureTransform = sensorMeasureTransform;
        return this;
    }

    public void run() {
        for (Sensor sensor : sensors) {
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
                series.putMeasure(sensor.getSensorMeasure(range.getCounter().getTimeInMillis()));
            } else {
                series.putMeasure(sensor.getSensorMeasure(range.getCounter().getTimeInMillis()));
            }
            range.increment();
        }
        output.write(series);
    }
}
