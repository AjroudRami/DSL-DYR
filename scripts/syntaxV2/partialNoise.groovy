simulation "simulation" timerange "14-02-2018 03:50:00", "14-02-2018 03:59:00" frequency 1,"SECOND"

addSensor "randomisedWithNoise" withLaw laws.function("function(t) 10.0") applyNoise transforms.partialRandom(10.0, 0.5)

generateSet "randomisedWithNoise", 3

outputTo out.CSV("out")

runSimulation()