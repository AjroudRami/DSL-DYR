package com.polytech.dsl.ssl.core.regression.law;

import com.polytech.dsl.ssl.source.SensorMeasure;
import org.junit.Assert;
import org.junit.Test;

public class FunctionLawTest {

    @Test
    public void simpleFunctionTest(){
        FunctionLaw law = new FunctionLaw("function(x) x*x");

        SensorMeasure measure = law.getMeasure(2);

        Assert.assertEquals(4.0, measure.getMeasures().get("function"));
    }
}
