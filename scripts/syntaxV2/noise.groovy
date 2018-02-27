simulation "simulation" timerange "14-02-2018 03:50:00", "14-02-2018 03:59:00" frequency 1,"SECOND"

addSensor "randomisedWithNoise" withLaw laws.random1D(10, 15) applyNoise transforms.random(10)

generateSet "randomisedWithNoise", 3

outputTo out.CSV("outs")

runSimulation()