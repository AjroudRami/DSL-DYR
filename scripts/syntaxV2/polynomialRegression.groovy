simulation "simulation" timerange "14-02-2018 03:50:00", "14-02-2018 03:59:00" frequency 1,"SECOND"

addSensor "regressioned" withLaw laws.polynomialRegression(sources.CSV("./testScripts/poly.csv"), 3)

generateSet "regressioned", 3

outputTo out.CSV("out")

runSimulation()