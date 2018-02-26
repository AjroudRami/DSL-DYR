simulation "simulation" timerange "now", "14-02-2018 23:59:00" frequency 1,"SECOND"

addSensor "regressioned" withLaw laws.polynomialRegression("/home/user/DSL-DYR/GroovySim/src/test/resources/toreplicate.csv")

generateSet "regressioned", 3

outputTo out.CSV("regressioned.csv")

run()