package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;
import org.junit.Assert;
import org.junit.Test;

public class FunctionLawTest {

    @Test
    public void simpleFunctionTest(){
        FunctionLaw law = new FunctionLaw("x -> x * x");

        SensorMeasure measure = law.getMeasure(2);
        Assert.assertEquals(4.0, measure.getMeasures().get("function"));
    }

    @Test
    public void simpleCosFunctionTest(){
        FunctionLaw law = new FunctionLaw("x -> Math.cos(x) * 3");
        System.out.println();
        SensorMeasure measure = law.getMeasure((long)0.5);
        Assert.assertEquals(3.0, measure.getMeasures().get("function"));
    }

    @Test
    public void complexFunctionTest(){
        FunctionLaw law = new FunctionLaw("x -> { if(x<1){ return 0.0; }else{return (double) x + 1; } }");
        SensorMeasure measure = law.getMeasure((long)0.5);
        Assert.assertEquals(0.0, measure.getMeasures().get("function"));
        measure = law.getMeasure((long) 1.0);
        Assert.assertEquals(2.0, measure.getMeasures().get("function"));
    }
}
