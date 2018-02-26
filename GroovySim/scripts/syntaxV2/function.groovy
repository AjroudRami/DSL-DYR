simulation "simulation" timerange "now", "14-02-2018 03:59:00" frequency 1, "SECOND"

addSensor "temp" withLaw laws.function("function(x) 3 * x * x - 2 * x + 1")
addSensor "light" withLaw laws.function("function(x) 3 * x * x + 2 * x -6")

outputTo out.Database("SSL")

run()