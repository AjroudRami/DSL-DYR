package com.polytech.dsl.ssl.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class SensorMeasure{

    private Long time;
    private Map<String, Object> measures;
    private static Logger LOGGER = Logger.getLogger(SensorMeasure.class.getName());

    public SensorMeasure(Long time) {
        this.time = time;
        this.measures = new HashMap<>();
    }

    public String[] getLabels(){
        String[] res = new String[this.measures.keySet().size()];
        this.measures.keySet().toArray(res);
        return res;
    }

    public Long time(){return this.time;}
    public Optional<Integer> getInt(String key) {return getType(key, Integer.class);}
    public Optional<Double> getDouble(String key) {return getType(key, Double.class);}
    public Optional<Boolean> getBoolean(String key) {return getType(key, Boolean.class);}
    public Optional<String> getString(String key) {return getType(key, String.class);}

    public <T> Optional<T> getType(String key, Class<T> cls) {
        try {
            return Optional.of(cls.cast( this.measures.get(key)));
        } catch (ClassCastException e) {
            LOGGER.warning(e.getMessage());
            return Optional.empty();
        }
    }

    public void putValue(String key, Object o){
        this.measures.putIfAbsent(key, o);
    }

    @Override
    public String toString() {
        String res = "SensorMeasure: time " + time;
        for (String key : this.measures.keySet()) {
            res += " | " + key + " : " + measures.get(key);
        }
        return res;
    }
}