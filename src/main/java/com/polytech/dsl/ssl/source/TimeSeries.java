package com.polytech.dsl.ssl.source;

import java.util.*;

public class TimeSeries {

    private Map<Long, SensorMeasure> data;

    public TimeSeries(){
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

    /**
     * Put a new measure on the time serie
     * Duplicate measure (same timestamp) will not be added
     * @param measure
     */
    protected void putMeasure(SensorMeasure measure){
        this.data.putIfAbsent(measure.time(), measure);
    }
}
