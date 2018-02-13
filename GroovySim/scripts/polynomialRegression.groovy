simulation "simulation" timerange "now", "13-02-2018 23:59:00" offset "SECOND", 1

sensor "regressioned" polynomialregressionlaw "/home/user/DSL-DYR/GroovySim/src/test/resources/toreplicate.csv"

generateSet "regressioned", 3

outputcsv "regressioned.csv"

run "Sensor Simulation Lab (SSL) - Regressioned mode"