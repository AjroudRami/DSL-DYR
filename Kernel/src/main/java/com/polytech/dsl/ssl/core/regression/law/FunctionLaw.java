package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.function.Function;

public class FunctionLaw implements SensorLaw{

    private static final String DEFAULT_KEY = "calculate";

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
            @SuppressWarnings("unchecked")
            Function<Object,Object> f = (Function<Object,Object> )engine.eval(
                    String.format("new java.util.function.Function(%s)", function));
            measure.putValue(DEFAULT_KEY, f.apply(time));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return measure;
    }

    @Override
    public SensorLaw cleanCopy() {
        return null;
    }
}
