simulation "simulation" timerange "15-02-2018 23:50:00", "15-02-2018 23:59:00" frequency 1, "SECOND"

addSensor "temp" withLaw laws.random1D(10, 40)
addSensor "light" withLaw laws.random1D(10, 40)

generateSet "temp", 6
generateSet "light", 3

outputTo out.CSV("outs")

runSimulation()