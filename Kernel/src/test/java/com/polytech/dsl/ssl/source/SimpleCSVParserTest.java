package com.polytech.dsl.ssl.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public class SimpleCSVParserTest {


    @Test
    public void testParseCSV() throws IOException {
        SimpleCSVParser parser = new SimpleCSVParser(new File("src/test/resources/test1.csv"));
        TimeSeries timeSeries = parser.parse();
        Assertions.assertEquals(19,timeSeries.getMeasures().size());
        SensorMeasure measure = timeSeries.getMeasures(0,0).get(0);
        Optional<Double> value = measure.getDouble("Gy");
        if(value.isPresent()) {
            double val = value.get();
            Assertions.assertEquals(0.0, val);
        }
    }
}
