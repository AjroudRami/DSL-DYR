package com.polytech.dsl.ssl.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class SimpleCSVParserTest {


    @Test
    public void testParseCSV() throws IOException {
        SimpleCSVParser parser = new SimpleCSVParser(new File("src/test/resources/test1.csv"));
        TimeSeries timeSeries = parser.parse();
        Assertions.assertEquals(19,timeSeries.getMeasures().size());
    }
}
