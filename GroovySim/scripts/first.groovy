simulation "simulation" timerange 0, 2344 frequency 10

sensor "temp" randomlaw 10, 40
sensor "light" randomlaw 20, 30

sensor "replayed" fromcsv "/home/user/DSL-DYR/GroovySim/src/test/resources/test1.csv"

generateSet "temp", 6
generateSet "light", 3

outputcsv "outputdata.csv"

run "Sensor Simulation Lab (SSL)"