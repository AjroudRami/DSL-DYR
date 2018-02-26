simulation "simulation" timerange "14-02-2018 23:58:00", "14-02-2018 23:59:00" frequency 1, "SECOND"

addSensor "temp" withLaw laws.random1D(10, 40)

outputTo out.Database("SSL")

runSimulation()