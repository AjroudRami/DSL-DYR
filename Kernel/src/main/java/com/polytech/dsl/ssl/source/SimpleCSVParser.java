package com.polytech.dsl.ssl.source;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SimpleCSVParser implements Source{


    private static final Logger LOGGER = Logger.getLogger(SimpleCSVParser.class);

    private static final String SEP = ";";
    private static final String TIME = "time";

    private Map<Integer, String> keyMap;
    private Map<String, Integer> reversedKeyMap;
    private File file;

    public SimpleCSVParser(String filename) {
        this.file = new File(filename);
        this.keyMap = new HashMap<>();
        this.reversedKeyMap = new HashMap<>();
    }

    public SimpleCSVParser(File f){
        this.file = f;
        this.keyMap = new HashMap<>();
        this.reversedKeyMap = new HashMap<>();
    }

    public TimeSeries parse() throws IOException {
        TimeSeries series = new TimeSeries(file.getName().split("\\.")[0]);
        Stream<String> stream = Files.lines(Paths.get(file.toURI()));
        stream.limit(1).forEach(l -> setHeaders(l));
        Files.lines(Paths.get(file.toURI())).skip(1).forEach(l -> series.putMeasure(parseLine(l)));
        return series;
    }

    private SensorMeasure parseLine(String line) {
        String[] values = line.split(SEP);
        Long time = Long.decode(values[reversedKeyMap.get(TIME)]);

        SensorMeasure measure = new SensorMeasure(time);
        for(int i = 0; i < values.length; i++) {
            if (i != reversedKeyMap.get(TIME)) {
                measure.putValue(keyMap.get(i), parseValue(values[i]));
            }
        }
        return measure;
    }

    private void setHeaders(String line) {
        String[] keys = line.split(SEP);
        for(int i = 0; i < keys.length; i++) {
            keyMap.put(i, keys[i]);
            reversedKeyMap.put(keys[i], i);
        }
    }

    private Object parseValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) { }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {}
        if(value.equals("true") || value.equals("false")) {
            return Boolean.getBoolean(value);
        }
        return value;
    }

    @Override
    public TimeSeries getTimeSeries() {
        try {
            return this.parse();
        } catch (IOException e) {
            LOGGER.info("Error when parsing file. Source not found at " + file.getPath());
            LOGGER.info("Program not compiled");
            System.exit(1);
            return new TimeSeries();
        }
    }
}
