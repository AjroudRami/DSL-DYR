package com.polytech.dsl.ssl.core;

import com.polytech.dsl.ssl.core.regression.Regression;
import com.polytech.dsl.ssl.core.regression.law.SensorLaw;
import com.polytech.dsl.ssl.core.transform.NoiseFunction;
import com.polytech.dsl.ssl.output.Output;
import com.polytech.dsl.ssl.source.Source;
import com.polytech.dsl.ssl.source.TimeSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SimulationBuilder {

    private static Logger LOGGER = Logger.getLogger(SimulationBuilder.class.getSimpleName());

    private Source source;
    private Output output;
    private List<Sensor> sensors;
    private Regression regression;
    private NoiseFunction noiseFunction;

    private long startTime;
    private long endTime;
    private int frequency;

    public SimulationBuilder(long startTime, long endTime, int frequency) {
        LOGGER.info("Init simulation builder, " +
                "startTime = " + startTime + "; endTime = " + endTime + "; frequency = " + frequency);
        this.startTime = startTime;
        this.endTime = endTime;
        this.frequency = frequency;
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

    public SimulationBuilder addNoise(NoiseFunction noiseFunction) {
        this.noiseFunction = noiseFunction;
        return this;
    }

    public void run(long start, long end){
        TimeSeries timeSeries = new TimeSeries();
        if(regression != null) {
            SensorLaw law = this.regression.getSensorLaw(this.source.getTimeSeries());
            for (long current = 0; start < end; current ++) {
                timeSeries.putMeasure(law.getMeasure(current));
            }
        } else {
            //TODO
        }
        //output.write();
    }

}
