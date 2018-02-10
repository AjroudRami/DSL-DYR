package com.polytech.dsl.ssl.function;

import com.polytech.dsl.ssl.source.SensorMeasure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Eroyas on 31/01/18.
 */
public class RandomLaw implements SensorLaw {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // private static final AtomicInteger count = new AtomicInteger(0);

    // private int id;
    private String name; // s
    private Map<String, Integer> measures; // t & v

    public RandomLaw(String name, int iteration) {
        this.measures = new HashMap<>();
        this.name = name;

        // setId(count.incrementAndGet());
        // setName("Sensor" + getId());

        while (iteration > 0) {
            Date date = new Date();
            int random = randomise(30, 50);

            measures.put(sdf.format(date), random);

            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            iteration--;
        }

    }

    @Override
    public SensorMeasure getMeasure(long time) {

        // SensorMeasure sm = new SensorMeasure("Sensor" + count.incrementAndGet(), time);
        return null;
    }

/* EXAMPLE :
while :;
do
    VALUE=$RANDOM;
    STAMP=`date +%s000000000`;
    echo "sensor: $VALUE @$STAMP";
    curl -i -XPOST 'http://localhost:8086/write?db=my_database' \
             --data-binary "a_given_sensor value=$VALUE $STAMP";
    T=$(( ( RANDOM % 10 )  + 1 ));
    sleep $T;
done
*/

    private int randomise(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        return randomNum;
    }

/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getMeasures() {
        return measures;
    }

    public void setMeasures(Map<String, Integer> measures) {
        this.measures = measures;
    }

}
