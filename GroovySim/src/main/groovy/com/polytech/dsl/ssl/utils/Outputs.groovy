package com.polytech.dsl.ssl.utils

import com.polytech.dsl.ssl.output.CSVWriter
import com.polytech.dsl.ssl.output.DatabaseWriter

class Outputs {


    static Database(String name) {
        if (name.equals("")) {
            return new DatabaseWriter()
        } else {
            return new DatabaseWriter(name)
        }
    }

    static CSV(String path) {
        new CSVWriter(path)
    }
}
