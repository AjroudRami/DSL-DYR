simulation "simulation" timerange "14-02-2018 03:50:00", "14-02-2018 03:59:00" frequency 1, "MINUTE"

addSensor "temp" withLaw laws.function("t ->  Math.cos(t) * 3 * t * t - 2 * t + 1")
addSensor "light" withLaw laws.function("t -> Math.cos(t) * 3 * t * t + 2 * t -6")

outputTo out.CSV("outs")

runSimulation()