simulation "simulation" timerange "now", "14-02-2018 23:59:00" frequency 1,"SECOND"

addSensor "replicated" withLaw laws.replayCSV("/home/user/DSL-DYR/GroovySim/src/test/resources/toreplicate.csv")

generateSet "replicated", 3

outputTo out.CSV("replicated.csv")

run()