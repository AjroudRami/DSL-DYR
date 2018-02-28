package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;
import org.apache.log4j.Logger;
import pl.joegreen.lambdaFromString.LambdaCreationException;
import pl.joegreen.lambdaFromString.LambdaFactory;
import pl.joegreen.lambdaFromString.TypeReference;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.function.Function;

public class FunctionLaw implements SensorLaw{


    private static final Logger LOGGER = Logger.getLogger(FunctionLaw.class);
    private static final String DEFAULT_KEY = "function";

    private ScriptEngine engine ;
    private String function;

    public FunctionLaw(String function){
        this.engine = new ScriptEngineManager().getEngineByName("nashorn");
        this.function = function;
    }

    @Override
    public SensorMeasure getMeasure(long time) {
        SensorMeasure measure = new SensorMeasure(time);
        try {
            LambdaFactory lambdaFactory = LambdaFactory.get();
            Function<Double, Double> lambda = lambdaFactory
                    .createLambda(function, new TypeReference<Function<Double, Double>>() {});
            measure.putValue(DEFAULT_KEY,lambda.apply((double)time));
        } catch (LambdaCreationException e) {
            LOGGER.info("Error when parsing function. Function should follow the following example : t -> t + 1 ");
            e.printStackTrace();
            System.exit(1);
        }
        return measure;
    }

    @Override
    public SensorLaw cleanCopy() {
        return new FunctionLaw(this.function);
    }
}
