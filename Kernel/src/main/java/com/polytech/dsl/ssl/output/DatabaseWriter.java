package com.polytech.dsl.ssl.output;

import com.polytech.dsl.ssl.source.SensorMeasure;
import com.polytech.dsl.ssl.source.TimeSeries;
import org.apache.log4j.Logger;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DatabaseWriter implements Output{

    private static final Logger LOGGER = Logger.getLogger(DatabaseWriter.class);

    private static final String DB_ADDRESS = "http://localhost:8086";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static final String DB_NAME = "SSL";
    private static final String DB_RETENTION = "default";

    private InfluxDB influxDB;

    public DatabaseWriter(){
        this.influxDB = InfluxDBFactory.connect(DB_ADDRESS, DB_USERNAME, DB_PASSWORD);
        influxDB.createDatabase(DB_NAME);
        influxDB.setDatabase(DB_NAME);
        influxDB.createRetentionPolicy(DB_RETENTION, DB_NAME, "30d", 1, true);
        influxDB.setRetentionPolicy(DB_RETENTION);
    }

    @Override
    public void write(TimeSeries series) throws IOException {
        BatchPoints batchPoints = BatchPoints
                .database(DB_NAME)
                .retentionPolicy(DB_RETENTION)
                .build();

        for (SensorMeasure measure : series.getMeasures()){
            batchPoints.point(
                    createPoint(series.getSensorName(),
                            measure));
        }

        LOGGER.info("Inserting " + series.getSensorName() + " in database " + DB_NAME);
        influxDB.write(batchPoints);
    }

    private Point createPoint(String name, SensorMeasure measure){
        return Point.measurement(name)
                .time(measure.time(),  TimeUnit.MILLISECONDS)
                .fields(measure.getMeasures())
                .build();
    }

}
