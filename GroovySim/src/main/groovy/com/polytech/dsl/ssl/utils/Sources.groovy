package com.polytech.dsl.ssl.utils

import com.polytech.dsl.ssl.output.DatabaseWriter
import com.polytech.dsl.ssl.source.SimpleCSVParser

class Sources {

    static CSV(String filename) {
        new SimpleCSVParser(filename)
    }

    static Database(String DatabaseName) {
        new DatabaseWriter(DatabaseName)
    }
}
