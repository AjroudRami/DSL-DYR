package com.polytech.dsl.ssl.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleCSVParserTest {

    private static final Logger LOGGER = Logger.getLogger(SimpleCSVParser.class.getName());
    private static boolean DEBUG = true;
    private TimeSeries timeSeries;
    private String filePath = "src/test/resources/testSensor.csv";

    @BeforeAll
    public void init() throws IOException {
        LOGGER.info("INIT Tests");
        File file = new File(filePath);
        if (!file.exists()) {
            LOGGER.warning("File \"" + filePath + "\" not found, test skipped");
            return;
        }
        SimpleCSVParser parser = new SimpleCSVParser(file);
        timeSeries = parser.parse();
        if (DEBUG) {
            LOGGER.info(timeSeries.toString());
        }
    }

    @Test
    public void testName() {
        check();
        Assertions.assertEquals("testSensor", timeSeries.getSensorName());
    }

    @Test
    public void testTimeSeriesSize() {
        check();
        Assertions.assertEquals(19,timeSeries.getMeasures().size());
    }

    @Test
    public void testMeasure() {
        check();
        SensorMeasure measure = timeSeries.getMeasures(0,0).get(0);
        Optional<Double> value = measure.getDouble("Gy");
        if(value.isPresent()) {
            double val = value.get();
            Assertions.assertEquals(0.0, val);
        }
    }

    private void check() {
        if (timeSeries == null) {
            Assertions.fail("TimeSeries is null, a problem may have occurred during the file importation");
        }
    }
}
