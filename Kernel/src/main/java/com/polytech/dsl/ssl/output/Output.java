package com.polytech.dsl.ssl.output;

import com.polytech.dsl.ssl.source.TimeSeries;

public interface Output {

    void write(TimeSeries timeSeries);
}
