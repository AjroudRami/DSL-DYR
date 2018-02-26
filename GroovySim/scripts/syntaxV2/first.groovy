simulation "simulation" timerange "15-02-2018 23:58:00", "15-02-2018 23:59:00" frequency 1, "DAY"

addSensor "temp" withLaw law.random1D(10, 40)
addSensor "light" withLaw law.random1D(10, 40)

sensor "replay" withLaw law.replayCSV("/home/user/DSL-DYR/GroovySim/src/test/resources/toreplicate.csv")

generateSet "temp", 6
generateSet "light", 3

outputTo out.CSV("outputdata.csv")

run()