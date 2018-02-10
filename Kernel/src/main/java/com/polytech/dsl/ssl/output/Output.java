package com.polytech.dsl.ssl.output;

import com.polytech.dsl.ssl.core.Sensor;

public interface Output {

    void write(Sensor sensor);
}
