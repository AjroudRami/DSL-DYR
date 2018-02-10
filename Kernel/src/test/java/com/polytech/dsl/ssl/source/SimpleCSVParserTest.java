package com.polytech.dsl.ssl.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class SimpleCSVParserTest {

    private static final Logger LOGGER = Logger.getLogger(SimpleCSVParser.class.getName());
    private static boolean DEBUG;

    @Test
    public void testParseCSV() throws IOException {
        File file = new File("src/test/resources/testSensor.csv");
        if (!file.exists()) {
            LOGGER.warning("File \"src/test/resources/testSensor.csv\" not found, test skipped");
            return;
        }
        SimpleCSVParser parser = new SimpleCSVParser(file);
        TimeSeries timeSeries = parser.parse();
        if (DEBUG) {
            LOGGER.info(timeSeries.toString());
        }
        Assertions.assertEquals(19,timeSeries.getMeasures().size());
        SensorMeasure measure = timeSeries.getMeasures(0,0).get(0);
        Optional<Double> value = measure.getDouble("Gy");
        if(value.isPresent()) {
            double val = value.get();
            Assertions.assertEquals(0.0, val);
        }
    }
}
