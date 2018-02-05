package com.polytech.dsl.ssl.core.regression;

import com.polytech.dsl.ssl.function.NoiseFunction;
import com.polytech.dsl.ssl.function.SensorLaw;
import com.polytech.dsl.ssl.output.Output;
import com.polytech.dsl.ssl.source.Source;
import com.polytech.dsl.ssl.source.TimeSeries;

public class SimulationBuilder {

    private Source source;
    private Output output;
    private Regression regression;
    private NoiseFunction noiseFunction;

    public SimulationBuilder(){}

    public SimulationBuilder setSource(Source src){
        this.source = src;
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
        output.write(timeSeries);
    }

}
