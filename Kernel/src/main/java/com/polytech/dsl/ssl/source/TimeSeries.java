package com.polytech.dsl.ssl.source;

import java.util.*;

public class TimeSeries {

    private Map<Long, SensorMeasure> data;
    private static int INTERPOLATION_TYPE;
    private String sensorName;

    public TimeSeries(String sensorName) {
        this.sensorName = sensorName;
        this.data = new HashMap<>();
    }

    public TimeSeries(){
        this.sensorName = "not defined";
        this.data = new HashMap<>();
    }

    /**
     * Returns all the sensor measure within the given time range (inclusive)
     * @param startTime
     * @param endTime
     * @return
     */
    public List<SensorMeasure> getMeasures(long startTime, long endTime){
        List<SensorMeasure> measures = new ArrayList<>();
        Iterator<Long> iterator = data.keySet().iterator();
        while(iterator.hasNext()) {
            long time = iterator.next();
            if (time >= startTime && time <= endTime) {
                measures.add(data.get(time));
            }
        }
        return measures;
    }

    public SensorMeasure getMeasure(long time) {
        Iterator<Long> iterator = data.keySet().iterator();
        long lowBound = Long.MIN_VALUE;
        long highBound = Long.MAX_VALUE;
        if (data.containsKey(time)) {
            return data.get(time);
        }
        while (iterator.hasNext()) {
            long keyTime = iterator.next();
            if (keyTime > lowBound && keyTime < time) {
                lowBound = keyTime;
            }
            if (keyTime < highBound && keyTime > time) {
                highBound = keyTime;
            }
        }
        return interpolate(lowBound, highBound);
    }

    private SensorMeasure interpolate(long lowbound, long highbound) {
        SensorMeasure low = data.get(lowbound);
        SensorMeasure high = data.get(highbound);
        switch (INTERPOLATION_TYPE) {
            default:
                return low;
        }
    }

    public Collection<SensorMeasure> getMeasures(){
        return this.data.values();
    }

    /**
     * Put a new measure on the time serie
     * Duplicate measure (same timestamp) will not be added
     * @param measure
     */
    public void putMeasure(SensorMeasure measure){
        if (measure != null) {
            this.data.putIfAbsent(measure.time(), measure);
        }
    }

    public String getSensorName() {
        return this.sensorName;
    }


    @Override
    public String toString() {
        String res = "TimeSeries: " + this.sensorName + "\n";
        for (Long time : this.data.keySet()) {
            res += "\t " + this.data.get(time) + "\n";
        }
        return res;
    }
}
