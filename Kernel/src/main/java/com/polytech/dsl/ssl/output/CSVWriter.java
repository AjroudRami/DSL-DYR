package com.polytech.dsl.ssl.output;

import com.polytech.dsl.ssl.source.SensorMeasure;
import com.polytech.dsl.ssl.source.TimeSeries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class CSVWriter implements Output {

    private static String SEP = ";";


    @Override
    public void write(TimeSeries series) throws IOException {
        //TODO change path
        String path = series.getSensorName();
        File f = new File(path);
        f.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        Collection<SensorMeasure> measures = series.getMeasures();
        String[] labels = series.getMeasures().iterator().next().getLabels();
        writeHeader(pw, labels);
        for (SensorMeasure measure : measures) {
            writeLine(pw, measure, labels);
        }
        pw.close();
    }

    private void writeHeader(PrintWriter pw, String[] labels) {
        String header = "";
        for (int i = 0; i < labels.length; i++) {
            header += labels[i];
            if (i != labels.length - 1) {
                header += SEP;
            }
        }
        header += "\n";
        pw.write(header);
    }

    private void writeLine(PrintWriter pw, SensorMeasure measure, String[] labels) {
        String line = "";
        for (int i = 0; i < labels.length; i++) {
            line += measure.get(labels[i]).toString();
            if (i != labels.length - 1) {
                line += SEP;
            }
        }
        line += "\n";
        pw.write(line);
    }
}
