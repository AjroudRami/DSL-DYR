package com.polytech.dsl.ssl.output;

import com.polytech.dsl.ssl.source.TimeSeries;

import java.io.IOException;

public interface Output {

    void write(TimeSeries series) throws IOException;
}
