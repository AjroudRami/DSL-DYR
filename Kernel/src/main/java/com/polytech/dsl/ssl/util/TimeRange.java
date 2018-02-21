package com.polytech.dsl.ssl.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeRange {


    private static final Logger LOGGER = Logger.getLogger(TimeRange.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private Calendar start;
    private Calendar end;
    private Calendar counter;

    private int unit;
    private int offset;

    public TimeRange(){
        this.start = Calendar.getInstance();
        this.end = Calendar.getInstance();
        this.counter = Calendar.getInstance();
    }

    public TimeRange setStart(String start){

        if (start.equals("now")) {
            this.start.setTime(new Date());
        } else {
            try {
                this.start.setTime(sdf.parse(start));
            } catch (ParseException e) {
                LOGGER.info("Error parsing start date. Start date should follow this format : dd-MM-yyyy HH:mm:ss");
                LOGGER.info("Program not compiled");
                System.exit(0);
            }
        }
        this.counter.setTime(this.start.getTime());
        return this;
    }

    public TimeRange setEnd(String end){
        try {
            this.end.setTime(sdf.parse(end));
        } catch (ParseException e) {
            LOGGER.info("Error parsing end date. End date should follow this format : dd-MM-yyyy HH:mm:ss" );
            LOGGER.info("Program not compiled");
            System.exit(0);
        }
        return this;
    }

    public TimeRange setFrequency(int field, int amount){
        this.unit = field;
        this.offset = amount;
        return this;
    }

    public boolean canIncrement(){
        return this.counter.before(this.end);
    }

    public void increment(){
        this.counter.add(unit, offset);
    }

    public String startToString(){
        return sdf.format(start.getTime());
    }

    public String endToString(){
        return sdf.format(end.getTime());
    }

    public String counterToString(){
        return sdf.format(counter.getTime());
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public Calendar getCounter() {
        return counter;
    }

    public void setCounter(Calendar counter) {
        this.counter = counter;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void restart() {
        this.counter.setTime(this.start.getTime());
    }
}
