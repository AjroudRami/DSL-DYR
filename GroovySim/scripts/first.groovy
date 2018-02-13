simulation "simulation" timerange "now", "13-02-2018 23:59:00" offset "SECOND", 1

sensor "temp" randomlaw 10, 40
sensor "light" randomlaw 20, 30

sensor "replayed" fromcsv "/home/user/DSL-DYR/GroovySim/src/test/resources/toreplicate.csv"

generateSet "temp", 6
generateSet "light", 3

outputcsv "outputdata.csv"

run "Sensor Simulation Lab (SSL)"