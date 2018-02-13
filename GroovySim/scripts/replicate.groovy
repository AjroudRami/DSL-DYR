simulation "simulation" timerange "now", "13-02-2018 23:59:00" offset 1,"SECOND"

sensor "replicated" fromcsv "/home/user/DSL-DYR/GroovySim/src/test/resources/toreplicate.csv"

generateSet "replicated", 3

outputcsv "replicated.csv"

run "Sensor Simulation Lab (SSL) - Replicated mode"