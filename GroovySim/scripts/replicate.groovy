simulation "ReplicatedSimulation" timerange 0, 2344 frequency 10

sensor "replicated" fromcsv "/home/user/DSL-DYR/GroovySim/src/test/resources/toreplicate.csv"

generateSet "replicated", 3

outputcsv "replicated.csv"

run "Sensor Simulation Lab (SSL) - Replicated mode"