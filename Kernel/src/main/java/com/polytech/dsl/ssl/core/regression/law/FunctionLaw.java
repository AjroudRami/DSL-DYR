package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;
import org.apache.log4j.Logger;

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

            String timeFunction = function.replace("t", "x");
            Function<Object,Double> f = (Function<Object,Double> )engine.eval(
                    String.format("new java.util.function.Function(%s)", function));
            measure.putValue(DEFAULT_KEY, f.apply(time));
        } catch (ScriptException e) {
            LOGGER.info("Error when parsing function. Function should follow the following example : function(x) x * x + 2");
            System.exit(1);
        }
        return measure;
    }

    @Override
    public SensorLaw cleanCopy() {
        return new FunctionLaw(this.function);
    }
}
