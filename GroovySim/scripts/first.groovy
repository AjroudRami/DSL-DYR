simulation "simulation" timerange "now", "15-02-2018 23:59:00" offset 1, "DAY"

sensor "temp" randomlaw 10, 40
sensor "light" randomlaw 20, 30

sensor "replayed" fromcsv "/home/user/DSL-DYR/GroovySim/src/test/resources/test1.csv"

generateSet "temp", 6
generateSet "light", 3

outputcsv "outputdata.csv"

run "Sensor Simulation Lab (SSL)"