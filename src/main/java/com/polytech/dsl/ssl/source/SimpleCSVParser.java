package com.polytech.dsl.ssl.source;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SimpleCSVParser {

    private static final String SEP = ",";
    private static final String SENSOR_NAME = "sensor";
    private static final String TIME = "time";

    private Map<Integer, String> keyMap;
    private Map<String, Integer> reversedKeyMap;
    private File file;

    public SimpleCSVParser(File f){
        this.file = f;
        this.keyMap = new HashMap<>();
        this.reversedKeyMap = new HashMap<>();
    }

    public TimeSeries parse() throws IOException {
        TimeSeries series = new TimeSeries();
        Stream<String> stream = Files.lines(Paths.get(file.toURI()));
        stream.limit(1).forEach(l -> setHeaders(l));
        stream.parallel().skip(1).forEach(l -> series.putMeasure(parseLine(l)));
        return series;
    }

    private SensorMeasure parseLine(String line) {
        String[] values = line.split(SEP);
        String name = values[reversedKeyMap.get(SENSOR_NAME)];
        Long time = Long.decode(values[reversedKeyMap.get(TIME)]);

        SensorMeasure measure = new SensorMeasure(name, time);
        for(int i = 0; i < values.length; i++) {
            if (i != reversedKeyMap.get(SENSOR_NAME) && i != reversedKeyMap.get(TIME)) {
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
            return Long.parseLong(value);
        } catch (NumberFormatException e) {}
        if(value.equals("true") || value.equals("false")) {
            return Boolean.getBoolean(value);
        }
        return value;
    }
}