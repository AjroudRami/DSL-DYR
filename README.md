# DSL-DYR
author :
- Danial BIN AHMAD
- Rami AJROUD
- Yasin Eroglu

## Introduction
This project is a part of our Domain Language Specific (DSL) course at Polytech Nice Sophia Antipolis. The goal of the project is to create a language specific to the IoT (Internet of Things) domain. 

## Usage Context
Developing a middleware dedicated to IoT networks is a non-trivial task. Testing the system in real condition does not make sense, as one should be able to work without being connected to the 12,345 sensors deployed in a Smart City (or a Smart Campus [3]). But the test infrastructure must be as close to the real system as possible. It is then necessary to simulate the sensor network in a proper way.

Therefore, having a system capable of simulating a set of data retrieve by IoT sensor, or generating this data base on different law is the simplest and cost effective way.

## The language
Below is a simple usage of the language

```groovy
// Declaring a simulation named "MySimulation"
// with time range starting at "now" (current time) and stop at "21-02-2018 23:59:00"
// with the frequency of 1 "MINUTE" (The simulation will generate a value for each minute)
simulation "MySimulation" timerange "now", "21-02-2018 23:59:00" frequency 1, "MINUTE"

// Declaring a sensor named "temp"
// that contains random values between 10 and 40
sensor "temp" randomlaw 10, 40

// The sensor "temp" is generated 6 times (copy)
generateSet "temp", 6

// The result of the simulation will be send to the file outputdata.csv
outputcsv "outputdata.csv"

// Run the simulation named "Sensor Simulation Lab (SSL)"
run "Sensor Simulation Lab (SSL)"
```
## Features

Input output features :
- CSV format input
- CSV format output
- Output to influxDB

Law available :
- Random 
- Function
- Polynomial Regression

Extension :
- Composing multiple IoT sensor

Plus :
- Apply general noise
